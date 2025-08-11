package com.tozny.crypto.android;

import android.os.Build;
import android.os.Process;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.jasypt.digest.StandardStringDigester;

/* loaded from: classes.dex */
public class AesCbcWithIntegrity {
    private static final int AES_KEY_LENGTH_BITS = 128;
    private static final boolean ALLOW_BROKEN_PRNG = false;
    public static final int BASE64_FLAGS = 2;
    private static final String CIPHER = "AES";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String HMAC_ALGORITHM = "HmacSHA256";
    private static final int HMAC_KEY_LENGTH_BITS = 256;
    private static final int IV_LENGTH_BYTES = 16;
    private static final String PBE_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int PBE_ITERATION_COUNT = 10000;
    private static final int PBE_SALT_LENGTH_BITS = 128;
    static final AtomicBoolean prngFixed = new AtomicBoolean(false);

    public static String keyString(SecretKeys keys) {
        return keys.toString();
    }

    public static SecretKeys keys(String keysStr) throws InvalidKeyException {
        String[] keysArr = keysStr.split(":");
        if (keysArr.length == 2) {
            byte[] confidentialityKey = Base64.decode(keysArr[0], 2);
            if (confidentialityKey.length == 16) {
                byte[] integrityKey = Base64.decode(keysArr[1], 2);
                if (integrityKey.length != 32) {
                    throw new InvalidKeyException("Base64 decoded key is not 256 bytes");
                }
                return new SecretKeys(new SecretKeySpec(confidentialityKey, 0, confidentialityKey.length, CIPHER), new SecretKeySpec(integrityKey, HMAC_ALGORITHM));
            }
            throw new InvalidKeyException("Base64 decoded key is not 128 bytes");
        }
        throw new IllegalArgumentException("Cannot parse aesKey:hmacKey");
    }

    public static SecretKeys generateKey() throws GeneralSecurityException {
        fixPrng();
        KeyGenerator keyGen = KeyGenerator.getInstance(CIPHER);
        keyGen.init(128);
        SecretKey confidentialityKey = keyGen.generateKey();
        byte[] integrityKeyBytes = randomBytes(32);
        SecretKey integrityKey = new SecretKeySpec(integrityKeyBytes, HMAC_ALGORITHM);
        return new SecretKeys(confidentialityKey, integrityKey);
    }

    public static SecretKeys generateKeyFromPassword(String password, byte[] salt) throws GeneralSecurityException {
        return generateKeyFromPassword(password, salt, PBE_ITERATION_COUNT);
    }

    public static SecretKeys generateKeyFromPassword(String password, byte[] salt, int iterationCount) throws GeneralSecurityException {
        fixPrng();
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, 384);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBE_ALGORITHM);
        byte[] keyBytes = keyFactory.generateSecret(keySpec).getEncoded();
        byte[] confidentialityKeyBytes = copyOfRange(keyBytes, 0, 16);
        byte[] integrityKeyBytes = copyOfRange(keyBytes, 16, 48);
        SecretKey confidentialityKey = new SecretKeySpec(confidentialityKeyBytes, CIPHER);
        SecretKey integrityKey = new SecretKeySpec(integrityKeyBytes, HMAC_ALGORITHM);
        return new SecretKeys(confidentialityKey, integrityKey);
    }

    public static SecretKeys generateKeyFromPassword(String password, String salt) throws GeneralSecurityException {
        return generateKeyFromPassword(password, salt, PBE_ITERATION_COUNT);
    }

    public static SecretKeys generateKeyFromPassword(String password, String salt, int iterationCount) throws GeneralSecurityException {
        return generateKeyFromPassword(password, Base64.decode(salt, 2), iterationCount);
    }

    public static byte[] generateSalt() throws GeneralSecurityException {
        return randomBytes(128);
    }

    public static String saltString(byte[] salt) {
        return Base64.encodeToString(salt, 2);
    }

    public static byte[] generateIv() throws GeneralSecurityException {
        return randomBytes(16);
    }

    private static byte[] randomBytes(int length) throws GeneralSecurityException {
        fixPrng();
        SecureRandom random = new SecureRandom();
        byte[] b = new byte[length];
        random.nextBytes(b);
        return b;
    }

    public static CipherTextIvMac encrypt(String plaintext, SecretKeys secretKeys) throws UnsupportedEncodingException, GeneralSecurityException {
        return encrypt(plaintext, secretKeys, StandardStringDigester.MESSAGE_CHARSET);
    }

    public static CipherTextIvMac encrypt(String plaintext, SecretKeys secretKeys, String encoding) throws UnsupportedEncodingException, GeneralSecurityException {
        return encrypt(plaintext.getBytes(encoding), secretKeys);
    }

    public static CipherTextIvMac encrypt(byte[] plaintext, SecretKeys secretKeys) throws GeneralSecurityException {
        byte[] iv = generateIv();
        Cipher aesCipherForEncryption = Cipher.getInstance(CIPHER_TRANSFORMATION);
        aesCipherForEncryption.init(1, secretKeys.getConfidentialityKey(), new IvParameterSpec(iv));
        byte[] iv2 = aesCipherForEncryption.getIV();
        byte[] byteCipherText = aesCipherForEncryption.doFinal(plaintext);
        byte[] ivCipherConcat = CipherTextIvMac.ivCipherConcat(iv2, byteCipherText);
        byte[] integrityMac = generateMac(ivCipherConcat, secretKeys.getIntegrityKey());
        return new CipherTextIvMac(byteCipherText, iv2, integrityMac);
    }

    private static void fixPrng() {
        if (!prngFixed.get()) {
            synchronized (PrngFixes.class) {
                if (!prngFixed.get()) {
                    PrngFixes.apply();
                    prngFixed.set(true);
                }
            }
        }
    }

    public static String decryptString(CipherTextIvMac civ, SecretKeys secretKeys, String encoding) throws UnsupportedEncodingException, GeneralSecurityException {
        return new String(decrypt(civ, secretKeys), encoding);
    }

    public static String decryptString(CipherTextIvMac civ, SecretKeys secretKeys) throws UnsupportedEncodingException, GeneralSecurityException {
        return decryptString(civ, secretKeys, StandardStringDigester.MESSAGE_CHARSET);
    }

    public static byte[] decrypt(CipherTextIvMac civ, SecretKeys secretKeys) throws GeneralSecurityException {
        byte[] ivCipherConcat = CipherTextIvMac.ivCipherConcat(civ.getIv(), civ.getCipherText());
        byte[] computedMac = generateMac(ivCipherConcat, secretKeys.getIntegrityKey());
        if (constantTimeEq(computedMac, civ.getMac())) {
            Cipher aesCipherForDecryption = Cipher.getInstance(CIPHER_TRANSFORMATION);
            aesCipherForDecryption.init(2, secretKeys.getConfidentialityKey(), new IvParameterSpec(civ.getIv()));
            return aesCipherForDecryption.doFinal(civ.getCipherText());
        }
        throw new GeneralSecurityException("MAC stored in civ does not match computed MAC.");
    }

    public static byte[] generateMac(byte[] byteCipherText, SecretKey integrityKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance(HMAC_ALGORITHM);
        sha256_HMAC.init(integrityKey);
        return sha256_HMAC.doFinal(byteCipherText);
    }

    public static class SecretKeys {
        private SecretKey confidentialityKey;
        private SecretKey integrityKey;

        public SecretKeys(SecretKey confidentialityKeyIn, SecretKey integrityKeyIn) {
            setConfidentialityKey(confidentialityKeyIn);
            setIntegrityKey(integrityKeyIn);
        }

        public SecretKey getConfidentialityKey() {
            return this.confidentialityKey;
        }

        public void setConfidentialityKey(SecretKey confidentialityKey) {
            this.confidentialityKey = confidentialityKey;
        }

        public SecretKey getIntegrityKey() {
            return this.integrityKey;
        }

        public void setIntegrityKey(SecretKey integrityKey) {
            this.integrityKey = integrityKey;
        }

        public String toString() {
            return Base64.encodeToString(getConfidentialityKey().getEncoded(), 2) + ":" + Base64.encodeToString(getIntegrityKey().getEncoded(), 2);
        }

        public int hashCode() {
            int result = (31 * 1) + this.confidentialityKey.hashCode();
            return (31 * result) + this.integrityKey.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SecretKeys other = (SecretKeys) obj;
            if (this.integrityKey.equals(other.integrityKey) && this.confidentialityKey.equals(other.confidentialityKey)) {
                return true;
            }
            return false;
        }
    }

    public static boolean constantTimeEq(byte[] a, byte[] b) {
        if (a.length != b.length) {
            return false;
        }
        int result = 0;
        for (int result2 = 0; result2 < a.length; result2++) {
            result |= a[result2] ^ b[result2];
        }
        return result == 0;
    }

    public static class CipherTextIvMac {
        private final byte[] cipherText;
        private final byte[] iv;
        private final byte[] mac;

        public byte[] getCipherText() {
            return this.cipherText;
        }

        public byte[] getIv() {
            return this.iv;
        }

        public byte[] getMac() {
            return this.mac;
        }

        public CipherTextIvMac(byte[] c, byte[] i, byte[] h) {
            this.cipherText = new byte[c.length];
            System.arraycopy(c, 0, this.cipherText, 0, c.length);
            this.iv = new byte[i.length];
            System.arraycopy(i, 0, this.iv, 0, i.length);
            this.mac = new byte[h.length];
            System.arraycopy(h, 0, this.mac, 0, h.length);
        }

        public CipherTextIvMac(String base64IvAndCiphertext) {
            String[] civArray = base64IvAndCiphertext.split(":");
            if (civArray.length != 3) {
                throw new IllegalArgumentException("Cannot parse iv:ciphertext:mac");
            }
            this.iv = Base64.decode(civArray[0], 2);
            this.mac = Base64.decode(civArray[1], 2);
            this.cipherText = Base64.decode(civArray[2], 2);
        }

        public static byte[] ivCipherConcat(byte[] iv, byte[] cipherText) {
            byte[] combined = new byte[iv.length + cipherText.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(cipherText, 0, combined, iv.length, cipherText.length);
            return combined;
        }

        public String toString() {
            String ivString = Base64.encodeToString(this.iv, 2);
            String cipherTextString = Base64.encodeToString(this.cipherText, 2);
            String macString = Base64.encodeToString(this.mac, 2);
            return String.format(ivString + ":" + macString + ":" + cipherTextString, new Object[0]);
        }

        public int hashCode() {
            int result = (31 * 1) + Arrays.hashCode(this.cipherText);
            return (31 * ((31 * result) + Arrays.hashCode(this.iv))) + Arrays.hashCode(this.mac);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            CipherTextIvMac other = (CipherTextIvMac) obj;
            if (Arrays.equals(this.cipherText, other.cipherText) && Arrays.equals(this.iv, other.iv) && Arrays.equals(this.mac, other.mac)) {
                return true;
            }
            return false;
        }
    }

    private static byte[] copyOfRange(byte[] from, int start, int end) {
        int length = end - start;
        byte[] result = new byte[length];
        System.arraycopy(from, start, result, 0, length);
        return result;
    }

    public static final class PrngFixes {
        private static final byte[] BUILD_FINGERPRINT_AND_DEVICE_SERIAL = getBuildFingerprintAndDeviceSerial();
        private static final int VERSION_CODE_JELLY_BEAN = 16;
        private static final int VERSION_CODE_JELLY_BEAN_MR2 = 18;

        private PrngFixes() {
        }

        public static void apply() {
            applyOpenSSLFix();
            installLinuxPRNGSecureRandom();
        }

        private static void applyOpenSSLFix() throws SecurityException {
            if (Build.VERSION.SDK_INT < 16 || Build.VERSION.SDK_INT > 18) {
                return;
            }
            try {
                Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto").getMethod("RAND_seed", byte[].class).invoke(null, generateSeed());
                int bytesRead = ((Integer) Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto").getMethod("RAND_load_file", String.class, Long.TYPE).invoke(null, "/dev/urandom", 1024)).intValue();
                if (bytesRead != 1024) {
                    throw new IOException("Unexpected number of bytes read from Linux PRNG: " + bytesRead);
                }
            } catch (Exception e) {
                throw new SecurityException("Failed to seed OpenSSL PRNG", e);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0056 A[Catch: all -> 0x002e, TryCatch #0 {all -> 0x002e, blocks: (B:32:0x0013, B:34:0x0016, B:10:0x0039, B:12:0x0056, B:13:0x0074, B:16:0x0076, B:18:0x0086, B:20:0x009e, B:21:0x00bc, B:22:0x00bd, B:26:0x007f, B:9:0x0031), top: B:31:0x0013, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0075  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static void installLinuxPRNGSecureRandom() throws java.lang.SecurityException {
            /*
                int r0 = android.os.Build.VERSION.SDK_INT
                r1 = 18
                if (r0 <= r1) goto L7
                return
            L7:
                java.lang.String r0 = "SecureRandom.SHA1PRNG"
                java.security.Provider[] r0 = java.security.Security.getProviders(r0)
                java.lang.Class<java.security.Security> r1 = java.security.Security.class
                monitor-enter(r1)
                r2 = 1
                if (r0 == 0) goto L31
                int r3 = r0.length     // Catch: java.lang.Throwable -> L2e
                if (r3 < r2) goto L31
                r3 = 0
                r3 = r0[r3]     // Catch: java.lang.Throwable -> L2e
                java.lang.Class r3 = r3.getClass()     // Catch: java.lang.Throwable -> L2e
                java.lang.String r3 = r3.getSimpleName()     // Catch: java.lang.Throwable -> L2e
                java.lang.Class<com.tozny.crypto.android.AesCbcWithIntegrity$PrngFixes$LinuxPRNGSecureRandomProvider> r4 = com.tozny.crypto.android.AesCbcWithIntegrity.PrngFixes.LinuxPRNGSecureRandomProvider.class
                java.lang.String r4 = r4.getSimpleName()     // Catch: java.lang.Throwable -> L2e
                boolean r3 = r3.equals(r4)     // Catch: java.lang.Throwable -> L2e
                if (r3 != 0) goto L39
                goto L31
            L2e:
                r2 = move-exception
                goto Lbf
            L31:
                com.tozny.crypto.android.AesCbcWithIntegrity$PrngFixes$LinuxPRNGSecureRandomProvider r3 = new com.tozny.crypto.android.AesCbcWithIntegrity$PrngFixes$LinuxPRNGSecureRandomProvider     // Catch: java.lang.Throwable -> L2e
                r3.<init>()     // Catch: java.lang.Throwable -> L2e
                java.security.Security.insertProviderAt(r3, r2)     // Catch: java.lang.Throwable -> L2e
            L39:
                java.security.SecureRandom r2 = new java.security.SecureRandom     // Catch: java.lang.Throwable -> L2e
                r2.<init>()     // Catch: java.lang.Throwable -> L2e
                java.security.Provider r3 = r2.getProvider()     // Catch: java.lang.Throwable -> L2e
                java.lang.Class r3 = r3.getClass()     // Catch: java.lang.Throwable -> L2e
                java.lang.String r3 = r3.getSimpleName()     // Catch: java.lang.Throwable -> L2e
                java.lang.Class<com.tozny.crypto.android.AesCbcWithIntegrity$PrngFixes$LinuxPRNGSecureRandomProvider> r4 = com.tozny.crypto.android.AesCbcWithIntegrity.PrngFixes.LinuxPRNGSecureRandomProvider.class
                java.lang.String r4 = r4.getSimpleName()     // Catch: java.lang.Throwable -> L2e
                boolean r3 = r3.equals(r4)     // Catch: java.lang.Throwable -> L2e
                if (r3 != 0) goto L75
                java.lang.SecurityException r3 = new java.lang.SecurityException     // Catch: java.lang.Throwable -> L2e
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2e
                r4.<init>()     // Catch: java.lang.Throwable -> L2e
                java.lang.String r5 = "new SecureRandom() backed by wrong Provider: "
                r4.append(r5)     // Catch: java.lang.Throwable -> L2e
                java.security.Provider r5 = r2.getProvider()     // Catch: java.lang.Throwable -> L2e
                java.lang.Class r5 = r5.getClass()     // Catch: java.lang.Throwable -> L2e
                r4.append(r5)     // Catch: java.lang.Throwable -> L2e
                java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L2e
                r3.<init>(r4)     // Catch: java.lang.Throwable -> L2e
                throw r3     // Catch: java.lang.Throwable -> L2e
            L75:
                r3 = 0
                java.lang.String r4 = "SHA1PRNG"
                java.security.SecureRandom r4 = java.security.SecureRandom.getInstance(r4)     // Catch: java.lang.Throwable -> L2e java.security.NoSuchAlgorithmException -> L7e
                r3 = r4
                goto L86
            L7e:
                r4 = move-exception
                java.lang.SecurityException r5 = new java.lang.SecurityException     // Catch: java.lang.Throwable -> L2e
                java.lang.String r6 = "SHA1PRNG not available"
                r5.<init>(r6, r4)     // Catch: java.lang.Throwable -> L2e
            L86:
                java.security.Provider r4 = r3.getProvider()     // Catch: java.lang.Throwable -> L2e
                java.lang.Class r4 = r4.getClass()     // Catch: java.lang.Throwable -> L2e
                java.lang.String r4 = r4.getSimpleName()     // Catch: java.lang.Throwable -> L2e
                java.lang.Class<com.tozny.crypto.android.AesCbcWithIntegrity$PrngFixes$LinuxPRNGSecureRandomProvider> r5 = com.tozny.crypto.android.AesCbcWithIntegrity.PrngFixes.LinuxPRNGSecureRandomProvider.class
                java.lang.String r5 = r5.getSimpleName()     // Catch: java.lang.Throwable -> L2e
                boolean r4 = r4.equals(r5)     // Catch: java.lang.Throwable -> L2e
                if (r4 != 0) goto Lbd
                java.lang.SecurityException r4 = new java.lang.SecurityException     // Catch: java.lang.Throwable -> L2e
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2e
                r5.<init>()     // Catch: java.lang.Throwable -> L2e
                java.lang.String r6 = "SecureRandom.getInstance(\"SHA1PRNG\") backed by wrong Provider: "
                r5.append(r6)     // Catch: java.lang.Throwable -> L2e
                java.security.Provider r6 = r3.getProvider()     // Catch: java.lang.Throwable -> L2e
                java.lang.Class r6 = r6.getClass()     // Catch: java.lang.Throwable -> L2e
                r5.append(r6)     // Catch: java.lang.Throwable -> L2e
                java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L2e
                r4.<init>(r5)     // Catch: java.lang.Throwable -> L2e
                throw r4     // Catch: java.lang.Throwable -> L2e
            Lbd:
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L2e
                return
            Lbf:
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L2e
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tozny.crypto.android.AesCbcWithIntegrity.PrngFixes.installLinuxPRNGSecureRandom():void");
        }

        private static class LinuxPRNGSecureRandomProvider extends Provider {
            public LinuxPRNGSecureRandomProvider() {
                super("LinuxPRNG", 1.0d, "A Linux-specific random number provider that uses /dev/urandom");
                put("SecureRandom.SHA1PRNG", LinuxPRNGSecureRandom.class.getName());
                put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
            }
        }

        public static class LinuxPRNGSecureRandom extends SecureRandomSpi {
            private static final File URANDOM_FILE = new File("/dev/urandom");
            private static final Object sLock = new Object();
            private static DataInputStream sUrandomIn;
            private static OutputStream sUrandomOut;
            private boolean mSeeded;

            @Override // java.security.SecureRandomSpi
            protected void engineSetSeed(byte[] bytes) {
                OutputStream out;
                try {
                    try {
                        synchronized (sLock) {
                            out = getUrandomOutputStream();
                        }
                        out.write(bytes);
                        out.flush();
                    } catch (IOException e) {
                        Log.w(PrngFixes.class.getSimpleName(), "Failed to mix seed into " + URANDOM_FILE);
                    }
                } finally {
                    this.mSeeded = true;
                }
            }

            @Override // java.security.SecureRandomSpi
            protected void engineNextBytes(byte[] bytes) {
                DataInputStream in;
                if (!this.mSeeded) {
                    engineSetSeed(PrngFixes.generateSeed());
                }
                try {
                    synchronized (sLock) {
                        in = getUrandomInputStream();
                    }
                    synchronized (in) {
                        in.readFully(bytes);
                    }
                } catch (IOException e) {
                    throw new SecurityException("Failed to read from " + URANDOM_FILE, e);
                }
            }

            @Override // java.security.SecureRandomSpi
            protected byte[] engineGenerateSeed(int size) {
                byte[] seed = new byte[size];
                engineNextBytes(seed);
                return seed;
            }

            private DataInputStream getUrandomInputStream() {
                DataInputStream dataInputStream;
                synchronized (sLock) {
                    if (sUrandomIn == null) {
                        try {
                            sUrandomIn = new DataInputStream(new FileInputStream(URANDOM_FILE));
                        } catch (IOException e) {
                            throw new SecurityException("Failed to open " + URANDOM_FILE + " for reading", e);
                        }
                    }
                    dataInputStream = sUrandomIn;
                }
                return dataInputStream;
            }

            private OutputStream getUrandomOutputStream() throws IOException {
                OutputStream outputStream;
                synchronized (sLock) {
                    if (sUrandomOut == null) {
                        sUrandomOut = new FileOutputStream(URANDOM_FILE);
                    }
                    outputStream = sUrandomOut;
                }
                return outputStream;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] generateSeed() {
            try {
                ByteArrayOutputStream seedBuffer = new ByteArrayOutputStream();
                DataOutputStream seedBufferOut = new DataOutputStream(seedBuffer);
                seedBufferOut.writeLong(System.currentTimeMillis());
                seedBufferOut.writeLong(System.nanoTime());
                seedBufferOut.writeInt(Process.myPid());
                seedBufferOut.writeInt(Process.myUid());
                seedBufferOut.write(BUILD_FINGERPRINT_AND_DEVICE_SERIAL);
                seedBufferOut.close();
                return seedBuffer.toByteArray();
            } catch (IOException e) {
                throw new SecurityException("Failed to generate seed", e);
            }
        }

        private static String getDeviceSerialNumber() {
            try {
                return (String) Build.class.getField("SERIAL").get(null);
            } catch (Exception e) {
                return null;
            }
        }

        private static byte[] getBuildFingerprintAndDeviceSerial() {
            StringBuilder result = new StringBuilder();
            String fingerprint = Build.FINGERPRINT;
            if (fingerprint != null) {
                result.append(fingerprint);
            }
            String serial = getDeviceSerialNumber();
            if (serial != null) {
                result.append(serial);
            }
            try {
                return result.toString().getBytes(StandardStringDigester.MESSAGE_CHARSET);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UTF-8 encoding not supported");
            }
        }
    }
}
