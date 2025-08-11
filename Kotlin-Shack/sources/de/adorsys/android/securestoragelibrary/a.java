package de.adorsys.android.securestoragelibrary;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.security.auth.x500.X500Principal;
import org.jasypt.digest.StandardStringDigester;

/* loaded from: classes.dex */
class a {
    a() {
    }

    @RequiresApi(14)
    @Nullable
    static String a(@NonNull Context context, @NonNull String str) {
        String str2;
        String str3;
        try {
            if (Build.VERSION.SDK_INT >= 18 && Build.VERSION.SDK_INT < 23) {
                str2 = "RSA/ECB/PKCS1Padding";
                str3 = "AndroidOpenSSL";
            } else {
                if (Build.VERSION.SDK_INT < 23) {
                    Log.e(a.class.getName(), context.getString(R.string.message_supported_api));
                    throw new CryptoException(context.getString(R.string.message_supported_api), null);
                }
                str2 = "RSA/ECB/PKCS1Padding";
                str3 = "AndroidKeyStoreBCWorkaround";
            }
            Cipher cipher = Cipher.getInstance(str2, str3);
            cipher.init(1, c(context));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            CipherOutputStream cipherOutputStream = new CipherOutputStream(byteArrayOutputStream, cipher);
            cipherOutputStream.write(str.getBytes(StandardStringDigester.MESSAGE_CHARSET));
            cipherOutputStream.close();
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
            throw new CryptoException(e.getMessage(), e);
        }
    }

    @RequiresApi(18)
    static void a(@NonNull Context context) {
        if (a()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            f(context);
        } else {
            Log.e(a.class.getName(), context.getString(R.string.message_supported_api));
            throw new CryptoException(context.getString(R.string.message_supported_api), null);
        }
    }

    static boolean a() {
        try {
            return b().getKey("adorsysKeyPair", null) != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Nullable
    static String b(@NonNull Context context, @NonNull String str) {
        String str2;
        String str3;
        try {
            if (Build.VERSION.SDK_INT >= 18 && Build.VERSION.SDK_INT < 23) {
                str2 = "RSA/ECB/PKCS1Padding";
                str3 = "AndroidOpenSSL";
            } else {
                if (Build.VERSION.SDK_INT < 23) {
                    Log.e(a.class.getName(), context.getString(R.string.message_supported_api));
                    throw new CryptoException(context.getString(R.string.message_supported_api), null);
                }
                str2 = "RSA/ECB/PKCS1Padding";
                str3 = "AndroidKeyStoreBCWorkaround";
            }
            Cipher cipher = Cipher.getInstance(str2, str3);
            cipher.init(2, d(context));
            CipherInputStream cipherInputStream = new CipherInputStream(new ByteArrayInputStream(Base64.decode(str, 0)), cipher);
            ArrayList arrayList = new ArrayList();
            while (true) {
                int read = cipherInputStream.read();
                if (read == -1) {
                    break;
                }
                arrayList.add(Byte.valueOf((byte) read));
            }
            byte[] bArr = new byte[arrayList.size()];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = ((Byte) arrayList.get(i)).byteValue();
            }
            return new String(bArr, 0, bArr.length, StandardStringDigester.MESSAGE_CHARSET);
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
            throw new CryptoException(e.getMessage(), e);
        }
    }

    @NonNull
    private static KeyStore b() {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore;
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            throw new CryptoException(e.getMessage(), e);
        }
    }

    static void b(@NonNull Context context) {
        if (!a()) {
            Log.e(a.class.getName(), context.getString(R.string.message_keypair_does_not_exist));
            return;
        }
        try {
            b().deleteEntry("adorsysKeyPair");
        } catch (KeyStoreException e) {
            throw new CryptoException(e.getMessage(), e);
        }
    }

    @Nullable
    private static PublicKey c(@NonNull Context context) {
        try {
            if (a()) {
                return ((KeyStore.PrivateKeyEntry) b().getEntry("adorsysKeyPair", null)).getCertificate().getPublicKey();
            }
            throw new CryptoException(context.getString(R.string.message_keypair_does_not_exist), null);
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException e) {
            throw new CryptoException(e.getMessage(), e);
        }
    }

    @Nullable
    private static PrivateKey d(@NonNull Context context) {
        try {
            if (a()) {
                return ((KeyStore.PrivateKeyEntry) b().getEntry("adorsysKeyPair", null)).getPrivateKey();
            }
            throw new CryptoException(context.getString(R.string.message_keypair_does_not_exist), null);
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException e) {
            throw new CryptoException(e.getMessage(), e);
        }
    }

    @RequiresApi(api = 17)
    private static boolean e(@NonNull Context context) {
        return context.getResources().getConfiguration().getLayoutDirection() == 1;
    }

    @RequiresApi(18)
    private static void f(@NonNull Context context) {
        try {
            if (e(context)) {
                Locale.setDefault(Locale.US);
            }
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(1, 99);
            KeyPairGeneratorSpec build = new KeyPairGeneratorSpec.Builder(context).setAlias("adorsysKeyPair").setSubject(new X500Principal("CN=SecureDeviceStorage, O=Adorsys, C=Germany")).setSerialNumber(BigInteger.TEN).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).build();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            keyPairGenerator.initialize(build);
            keyPairGenerator.generateKeyPair();
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new CryptoException(e.getMessage(), e);
        }
    }
}
