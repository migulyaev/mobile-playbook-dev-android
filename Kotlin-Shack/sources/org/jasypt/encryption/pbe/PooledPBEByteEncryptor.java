package org.jasypt.encryption.pbe;

import java.security.Provider;
import org.jasypt.commons.CommonUtils;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.exceptions.AlreadyInitializedException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.salt.SaltGenerator;

/* loaded from: classes.dex */
public final class PooledPBEByteEncryptor implements PBEByteCleanablePasswordEncryptor {
    private StandardPBEByteEncryptor[] pool;
    private PBEConfig config = null;
    private int poolSize = 0;
    private boolean poolSizeSet = false;
    private int roundRobin = 0;
    private boolean initialized = false;
    private final StandardPBEByteEncryptor firstEncryptor = new StandardPBEByteEncryptor();

    public synchronized void setConfig(PBEConfig config) {
        this.firstEncryptor.setConfig(config);
        this.config = config;
    }

    public synchronized void setAlgorithm(String algorithm) {
        this.firstEncryptor.setAlgorithm(algorithm);
    }

    @Override // org.jasypt.encryption.pbe.PasswordBased
    public synchronized void setPassword(String password) {
        this.firstEncryptor.setPassword(password);
    }

    @Override // org.jasypt.encryption.pbe.CleanablePasswordBased
    public synchronized void setPasswordCharArray(char[] password) {
        this.firstEncryptor.setPasswordCharArray(password);
    }

    public synchronized void setKeyObtentionIterations(int keyObtentionIterations) {
        this.firstEncryptor.setKeyObtentionIterations(keyObtentionIterations);
    }

    public synchronized void setSaltGenerator(SaltGenerator saltGenerator) {
        this.firstEncryptor.setSaltGenerator(saltGenerator);
    }

    public synchronized void setProviderName(String providerName) {
        this.firstEncryptor.setProviderName(providerName);
    }

    public synchronized void setProvider(Provider provider) {
        this.firstEncryptor.setProvider(provider);
    }

    public synchronized void setPoolSize(int poolSize) {
        CommonUtils.validateIsTrue(poolSize > 0, "Pool size be > 0");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.poolSize = poolSize;
        this.poolSizeSet = true;
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public synchronized void initialize() {
        int i;
        if (!this.initialized) {
            if (this.config != null) {
                Integer configPoolSize = this.config.getPoolSize();
                if (!this.poolSizeSet && configPoolSize != null) {
                    i = configPoolSize.intValue();
                    this.poolSize = i;
                }
                i = this.poolSize;
                this.poolSize = i;
            }
            if (this.poolSize <= 0) {
                throw new IllegalArgumentException("Pool size must be set and > 0");
            }
            this.pool = this.firstEncryptor.cloneAndInitializeEncryptor(this.poolSize);
            this.initialized = true;
        }
    }

    @Override // org.jasypt.encryption.ByteEncryptor
    public byte[] encrypt(byte[] message) throws EncryptionOperationNotPossibleException {
        if (!isInitialized()) {
            initialize();
        }
        synchronized (this) {
            try {
                try {
                    int poolPosition = this.roundRobin;
                    this.roundRobin = (this.roundRobin + 1) % this.poolSize;
                    return this.pool[poolPosition].encrypt(message);
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    @Override // org.jasypt.encryption.ByteEncryptor
    public byte[] decrypt(byte[] encryptedMessage) throws EncryptionOperationNotPossibleException {
        if (!isInitialized()) {
            initialize();
        }
        synchronized (this) {
            try {
                try {
                    int poolPosition = this.roundRobin;
                    this.roundRobin = (this.roundRobin + 1) % this.poolSize;
                    return this.pool[poolPosition].decrypt(encryptedMessage);
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }
}
