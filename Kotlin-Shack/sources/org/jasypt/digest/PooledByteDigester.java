package org.jasypt.digest;

import java.security.Provider;
import org.jasypt.commons.CommonUtils;
import org.jasypt.digest.config.DigesterConfig;
import org.jasypt.exceptions.AlreadyInitializedException;
import org.jasypt.salt.SaltGenerator;

/* loaded from: classes.dex */
public class PooledByteDigester implements ByteDigester {
    private StandardByteDigester[] pool;
    private DigesterConfig config = null;
    private int poolSize = 0;
    private boolean poolSizeSet = false;
    private int roundRobin = 0;
    private boolean initialized = false;
    private final StandardByteDigester firstDigester = new StandardByteDigester();

    public synchronized void setConfig(DigesterConfig config) {
        this.firstDigester.setConfig(config);
        this.config = config;
    }

    public synchronized void setAlgorithm(String algorithm) {
        this.firstDigester.setAlgorithm(algorithm);
    }

    public synchronized void setSaltSizeBytes(int saltSizeBytes) {
        this.firstDigester.setSaltSizeBytes(saltSizeBytes);
    }

    public synchronized void setIterations(int iterations) {
        this.firstDigester.setIterations(iterations);
    }

    public synchronized void setSaltGenerator(SaltGenerator saltGenerator) {
        this.firstDigester.setSaltGenerator(saltGenerator);
    }

    public synchronized void setProviderName(String providerName) {
        this.firstDigester.setProviderName(providerName);
    }

    public synchronized void setProvider(Provider provider) {
        this.firstDigester.setProvider(provider);
    }

    public synchronized void setInvertPositionOfSaltInMessageBeforeDigesting(boolean invertPositionOfSaltInMessageBeforeDigesting) {
        this.firstDigester.setInvertPositionOfSaltInMessageBeforeDigesting(invertPositionOfSaltInMessageBeforeDigesting);
    }

    public synchronized void setInvertPositionOfPlainSaltInEncryptionResults(boolean invertPositionOfPlainSaltInEncryptionResults) {
        this.firstDigester.setInvertPositionOfPlainSaltInEncryptionResults(invertPositionOfPlainSaltInEncryptionResults);
    }

    public synchronized void setUseLenientSaltSizeCheck(boolean useLenientSaltSizeCheck) {
        this.firstDigester.setUseLenientSaltSizeCheck(useLenientSaltSizeCheck);
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
            this.pool = new StandardByteDigester[this.poolSize];
            this.pool[0] = this.firstDigester;
            for (int i2 = 1; i2 < this.poolSize; i2++) {
                this.pool[i2] = this.pool[i2 - 1].cloneDigester();
            }
            this.initialized = true;
        }
    }

    @Override // org.jasypt.digest.ByteDigester
    public byte[] digest(byte[] message) {
        if (!isInitialized()) {
            initialize();
        }
        synchronized (this) {
            try {
                try {
                    int poolPosition = this.roundRobin;
                    this.roundRobin = (this.roundRobin + 1) % this.poolSize;
                    return this.pool[poolPosition].digest(message);
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    @Override // org.jasypt.digest.ByteDigester
    public boolean matches(byte[] message, byte[] digest) {
        if (!isInitialized()) {
            initialize();
        }
        synchronized (this) {
            try {
                try {
                    int poolPosition = this.roundRobin;
                    this.roundRobin = (this.roundRobin + 1) % this.poolSize;
                    return this.pool[poolPosition].matches(message, digest);
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
