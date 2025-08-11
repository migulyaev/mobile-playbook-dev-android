package org.jasypt.encryption.pbe;

import java.security.InvalidKeyException;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEParameterSpec;
import org.jasypt.commons.CommonUtils;
import org.jasypt.encryption.pbe.config.PBECleanablePasswordConfig;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.exceptions.AlreadyInitializedException;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.salt.SaltGenerator;

/* loaded from: classes.dex */
public final class StandardPBEByteEncryptor implements PBEByteCleanablePasswordEncryptor {
    public static final String DEFAULT_ALGORITHM = "PBEWithMD5AndDES";
    public static final int DEFAULT_KEY_OBTENTION_ITERATIONS = 1000;
    public static final int DEFAULT_SALT_SIZE_BYTES = 8;
    private String algorithm = DEFAULT_ALGORITHM;
    private String providerName = null;
    private Provider provider = null;
    private char[] password = null;
    private int keyObtentionIterations = 1000;
    private SaltGenerator saltGenerator = null;
    private int saltSizeBytes = 8;
    private PBEConfig config = null;
    private boolean algorithmSet = false;
    private boolean passwordSet = false;
    private boolean iterationsSet = false;
    private boolean saltGeneratorSet = false;
    private boolean providerNameSet = false;
    private boolean providerSet = false;
    private boolean initialized = false;
    private SecretKey key = null;
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;
    private boolean usingFixedSalt = false;
    private byte[] fixedSaltInUse = null;

    public synchronized void setConfig(PBEConfig config) {
        CommonUtils.validateNotNull(config, "Config cannot be set null");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.config = config;
    }

    public synchronized void setAlgorithm(String algorithm) {
        CommonUtils.validateNotEmpty(algorithm, "Algorithm cannot be set empty");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.algorithm = algorithm;
        this.algorithmSet = true;
    }

    @Override // org.jasypt.encryption.pbe.PasswordBased
    public synchronized void setPassword(String password) {
        CommonUtils.validateNotEmpty(password, "Password cannot be set empty");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        if (this.password != null) {
            cleanPassword(this.password);
        }
        this.password = password.toCharArray();
        this.passwordSet = true;
    }

    @Override // org.jasypt.encryption.pbe.CleanablePasswordBased
    public synchronized void setPasswordCharArray(char[] password) {
        CommonUtils.validateNotNull(password, "Password cannot be set null");
        CommonUtils.validateIsTrue(password.length > 0, "Password cannot be set empty");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        if (this.password != null) {
            cleanPassword(this.password);
        }
        this.password = new char[password.length];
        System.arraycopy(password, 0, this.password, 0, password.length);
        this.passwordSet = true;
    }

    public synchronized void setKeyObtentionIterations(int keyObtentionIterations) {
        CommonUtils.validateIsTrue(keyObtentionIterations > 0, "Number of iterations for key obtention must be greater than zero");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.keyObtentionIterations = keyObtentionIterations;
        this.iterationsSet = true;
    }

    public synchronized void setSaltGenerator(SaltGenerator saltGenerator) {
        CommonUtils.validateNotNull(saltGenerator, "Salt generator cannot be set null");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.saltGenerator = saltGenerator;
        this.saltGeneratorSet = true;
    }

    public synchronized void setProviderName(String providerName) {
        CommonUtils.validateNotNull(providerName, "Provider name cannot be set null");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.providerName = providerName;
        this.providerNameSet = true;
    }

    public synchronized void setProvider(Provider provider) {
        CommonUtils.validateNotNull(provider, "Provider cannot be set null");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.provider = provider;
        this.providerSet = true;
    }

    synchronized StandardPBEByteEncryptor[] cloneAndInitializeEncryptor(int size) {
        StandardPBEByteEncryptor[] clones;
        if (isInitialized()) {
            throw new EncryptionInitializationException("Cannot clone encryptor if it has been already initialized");
        }
        resolveConfigurationPassword();
        char[] copiedPassword = new char[this.password.length];
        System.arraycopy(this.password, 0, copiedPassword, 0, this.password.length);
        initialize();
        clones = new StandardPBEByteEncryptor[size];
        clones[0] = this;
        for (int i = 1; i < size; i++) {
            StandardPBEByteEncryptor clone = new StandardPBEByteEncryptor();
            clone.setPasswordCharArray(copiedPassword);
            if (CommonUtils.isNotEmpty(this.algorithm)) {
                clone.setAlgorithm(this.algorithm);
            }
            clone.setKeyObtentionIterations(this.keyObtentionIterations);
            if (this.provider != null) {
                clone.setProvider(this.provider);
            }
            if (this.providerName != null) {
                clone.setProviderName(this.providerName);
            }
            if (this.saltGenerator != null) {
                clone.setSaltGenerator(this.saltGenerator);
            }
            clones[i] = clone;
        }
        cleanPassword(copiedPassword);
        return clones;
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x006a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0077 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0084 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void initialize() {
        /*
            Method dump skipped, instructions count: 369
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jasypt.encryption.pbe.StandardPBEByteEncryptor.initialize():void");
    }

    private synchronized void resolveConfigurationPassword() {
        if (!this.initialized && this.config != null && !this.passwordSet) {
            char[] configPassword = null;
            if (this.config instanceof PBECleanablePasswordConfig) {
                configPassword = ((PBECleanablePasswordConfig) this.config).getPasswordCharArray();
            } else {
                String configPwd = this.config.getPassword();
                if (configPwd != null) {
                    configPassword = configPwd.toCharArray();
                }
            }
            if (configPassword != null) {
                CommonUtils.validateIsTrue(configPassword.length > 0, "Password cannot be set empty");
            }
            if (configPassword != null) {
                this.password = new char[configPassword.length];
                System.arraycopy(configPassword, 0, this.password, 0, configPassword.length);
                this.passwordSet = true;
                cleanPassword(configPassword);
            }
            if (this.config instanceof PBECleanablePasswordConfig) {
                ((PBECleanablePasswordConfig) this.config).cleanPassword();
            }
        }
    }

    private static void cleanPassword(char[] password) {
        if (password != null) {
            synchronized (password) {
                int pwdLength = password.length;
                for (int i = 0; i < pwdLength; i++) {
                    password[i] = 0;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004c A[Catch: Exception -> 0x0055, InvalidKeyException -> 0x005c, TRY_LEAVE, TryCatch #4 {InvalidKeyException -> 0x005c, Exception -> 0x0055, blocks: (B:9:0x000d, B:11:0x0011, B:12:0x0015, B:17:0x0044, B:19:0x004c, B:25:0x0021, B:26:0x0022, B:27:0x0033, B:34:0x0054, B:29:0x0034, B:30:0x0043, B:14:0x0016, B:15:0x001d), top: B:8:0x000d, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0051 A[RETURN] */
    @Override // org.jasypt.encryption.ByteEncryptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] encrypt(byte[] r8) throws org.jasypt.exceptions.EncryptionOperationNotPossibleException {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            boolean r1 = r7.isInitialized()
            if (r1 != 0) goto Ld
            r7.initialize()
        Ld:
            boolean r1 = r7.usingFixedSalt     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            if (r1 == 0) goto L22
            byte[] r1 = r7.fixedSaltInUse     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            javax.crypto.Cipher r2 = r7.encryptCipher     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            monitor-enter(r2)     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            javax.crypto.Cipher r3 = r7.encryptCipher     // Catch: java.lang.Throwable -> L1f
            byte[] r3 = r3.doFinal(r8)     // Catch: java.lang.Throwable -> L1f
            r0 = r3
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L1f
            goto L44
        L1f:
            r3 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L1f
            throw r3     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
        L22:
            org.jasypt.salt.SaltGenerator r1 = r7.saltGenerator     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            int r2 = r7.saltSizeBytes     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            byte[] r1 = r1.generateSalt(r2)     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            javax.crypto.spec.PBEParameterSpec r2 = new javax.crypto.spec.PBEParameterSpec     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            int r3 = r7.keyObtentionIterations     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            r2.<init>(r1, r3)     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            javax.crypto.Cipher r3 = r7.encryptCipher     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            monitor-enter(r3)     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            javax.crypto.Cipher r4 = r7.encryptCipher     // Catch: java.lang.Throwable -> L52
            r5 = 1
            javax.crypto.SecretKey r6 = r7.key     // Catch: java.lang.Throwable -> L52
            r4.init(r5, r6, r2)     // Catch: java.lang.Throwable -> L52
            javax.crypto.Cipher r4 = r7.encryptCipher     // Catch: java.lang.Throwable -> L52
            byte[] r4 = r4.doFinal(r8)     // Catch: java.lang.Throwable -> L52
            r0 = r4
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L52
        L44:
            org.jasypt.salt.SaltGenerator r2 = r7.saltGenerator     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            boolean r2 = r2.includePlainSaltInEncryptionResults()     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            if (r2 == 0) goto L51
            byte[] r2 = org.jasypt.commons.CommonUtils.appendArrays(r1, r0)     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
            return r2
        L51:
            return r0
        L52:
            r4 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L52
            throw r4     // Catch: java.lang.Exception -> L55 java.security.InvalidKeyException -> L5c
        L55:
            r0 = move-exception
            org.jasypt.exceptions.EncryptionOperationNotPossibleException r1 = new org.jasypt.exceptions.EncryptionOperationNotPossibleException
            r1.<init>()
            throw r1
        L5c:
            r0 = move-exception
            r7.handleInvalidKeyException(r0)
            org.jasypt.exceptions.EncryptionOperationNotPossibleException r1 = new org.jasypt.exceptions.EncryptionOperationNotPossibleException
            r1.<init>()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jasypt.encryption.pbe.StandardPBEByteEncryptor.encrypt(byte[]):byte[]");
    }

    @Override // org.jasypt.encryption.ByteEncryptor
    public byte[] decrypt(byte[] encryptedMessage) throws EncryptionOperationNotPossibleException {
        byte[] salt;
        byte[] encryptedMessageKernel;
        byte[] decryptedMessage;
        if (encryptedMessage == null) {
            return null;
        }
        if (!isInitialized()) {
            initialize();
        }
        if (this.saltGenerator.includePlainSaltInEncryptionResults() && encryptedMessage.length <= this.saltSizeBytes) {
            throw new EncryptionOperationNotPossibleException();
        }
        try {
            if (this.saltGenerator.includePlainSaltInEncryptionResults()) {
                int saltSize = this.saltSizeBytes < encryptedMessage.length ? this.saltSizeBytes : encryptedMessage.length;
                int encMesKernelStart = this.saltSizeBytes < encryptedMessage.length ? this.saltSizeBytes : encryptedMessage.length;
                int encMesKernelSize = this.saltSizeBytes < encryptedMessage.length ? encryptedMessage.length - this.saltSizeBytes : 0;
                salt = new byte[saltSize];
                encryptedMessageKernel = new byte[encMesKernelSize];
                System.arraycopy(encryptedMessage, 0, salt, 0, saltSize);
                System.arraycopy(encryptedMessage, encMesKernelStart, encryptedMessageKernel, 0, encMesKernelSize);
            } else if (!this.usingFixedSalt) {
                salt = this.saltGenerator.generateSalt(this.saltSizeBytes);
                encryptedMessageKernel = encryptedMessage;
            } else {
                salt = this.fixedSaltInUse;
                encryptedMessageKernel = encryptedMessage;
            }
            if (this.usingFixedSalt) {
                synchronized (this.decryptCipher) {
                    decryptedMessage = this.decryptCipher.doFinal(encryptedMessageKernel);
                }
                return decryptedMessage;
            }
            PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, this.keyObtentionIterations);
            synchronized (this.decryptCipher) {
                this.decryptCipher.init(2, this.key, parameterSpec);
                decryptedMessage = this.decryptCipher.doFinal(encryptedMessageKernel);
            }
            return decryptedMessage;
        } catch (InvalidKeyException e) {
            handleInvalidKeyException(e);
            throw new EncryptionOperationNotPossibleException();
        } catch (Exception e2) {
            throw new EncryptionOperationNotPossibleException();
        }
    }

    private void handleInvalidKeyException(InvalidKeyException e) {
        if (e.getMessage() != null && e.getMessage().toUpperCase().indexOf("KEY SIZE") != -1) {
            throw new EncryptionOperationNotPossibleException("Encryption raised an exception. A possible cause is you are using strong encryption algorithms and you have not installed the Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files in this Java Virtual Machine");
        }
    }
}
