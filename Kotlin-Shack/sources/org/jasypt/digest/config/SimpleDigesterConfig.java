package org.jasypt.digest.config;

import java.security.Provider;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.salt.SaltGenerator;

/* loaded from: classes.dex */
public class SimpleDigesterConfig implements DigesterConfig {
    private String algorithm = null;
    private Integer iterations = null;
    private Integer saltSizeBytes = null;
    private SaltGenerator saltGenerator = null;
    private String providerName = null;
    private Provider provider = null;
    private Boolean invertPositionOfSaltInMessageBeforeDigesting = null;
    private Boolean invertPositionOfPlainSaltInEncryptionResults = null;
    private Boolean useLenientSaltSizeCheck = null;
    private Integer poolSize = null;

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setIterations(Integer iterations) {
        this.iterations = iterations;
    }

    public void setIterations(String iterations) {
        if (iterations != null) {
            try {
                this.iterations = new Integer(iterations);
                return;
            } catch (NumberFormatException e) {
                throw new EncryptionInitializationException(e);
            }
        }
        this.iterations = null;
    }

    public void setSaltSizeBytes(Integer saltSizeBytes) {
        this.saltSizeBytes = saltSizeBytes;
    }

    public void setSaltSizeBytes(String saltSizeBytes) {
        if (saltSizeBytes != null) {
            try {
                this.saltSizeBytes = new Integer(saltSizeBytes);
                return;
            } catch (NumberFormatException e) {
                throw new EncryptionInitializationException(e);
            }
        }
        this.saltSizeBytes = null;
    }

    public void setSaltGenerator(SaltGenerator saltGenerator) {
        this.saltGenerator = saltGenerator;
    }

    public void setSaltGeneratorClassName(String saltGeneratorClassName) {
        if (saltGeneratorClassName != null) {
            try {
                Class saltGeneratorClass = Thread.currentThread().getContextClassLoader().loadClass(saltGeneratorClassName);
                this.saltGenerator = (SaltGenerator) saltGeneratorClass.newInstance();
                return;
            } catch (Exception e) {
                throw new EncryptionInitializationException(e);
            }
        }
        this.saltGenerator = null;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setProviderClassName(String providerClassName) {
        if (providerClassName != null) {
            try {
                Class providerClass = Thread.currentThread().getContextClassLoader().loadClass(providerClassName);
                this.provider = (Provider) providerClass.newInstance();
                return;
            } catch (Exception e) {
                throw new EncryptionInitializationException(e);
            }
        }
        this.provider = null;
    }

    public void setInvertPositionOfSaltInMessageBeforeDigesting(Boolean invertPositionOfSaltInMessageBeforeDigesting) {
        this.invertPositionOfSaltInMessageBeforeDigesting = invertPositionOfSaltInMessageBeforeDigesting;
    }

    public void setInvertPositionOfPlainSaltInEncryptionResults(Boolean invertPositionOfPlainSaltInEncryptionResults) {
        this.invertPositionOfPlainSaltInEncryptionResults = invertPositionOfPlainSaltInEncryptionResults;
    }

    public void setUseLenientSaltSizeCheck(Boolean useLenientSaltSizeCheck) {
        this.useLenientSaltSizeCheck = useLenientSaltSizeCheck;
    }

    public void setPoolSize(Integer poolSize) {
        this.poolSize = poolSize;
    }

    public void setPoolSize(String poolSize) {
        if (poolSize != null) {
            try {
                this.poolSize = new Integer(poolSize);
                return;
            } catch (NumberFormatException e) {
                throw new EncryptionInitializationException(e);
            }
        }
        this.poolSize = null;
    }

    @Override // org.jasypt.digest.config.DigesterConfig
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override // org.jasypt.digest.config.DigesterConfig
    public Integer getIterations() {
        return this.iterations;
    }

    @Override // org.jasypt.digest.config.DigesterConfig
    public Integer getSaltSizeBytes() {
        return this.saltSizeBytes;
    }

    @Override // org.jasypt.digest.config.DigesterConfig
    public SaltGenerator getSaltGenerator() {
        return this.saltGenerator;
    }

    @Override // org.jasypt.digest.config.DigesterConfig
    public String getProviderName() {
        return this.providerName;
    }

    @Override // org.jasypt.digest.config.DigesterConfig
    public Provider getProvider() {
        return this.provider;
    }

    @Override // org.jasypt.digest.config.DigesterConfig
    public Boolean getInvertPositionOfSaltInMessageBeforeDigesting() {
        return this.invertPositionOfSaltInMessageBeforeDigesting;
    }

    @Override // org.jasypt.digest.config.DigesterConfig
    public Boolean getInvertPositionOfPlainSaltInEncryptionResults() {
        return this.invertPositionOfPlainSaltInEncryptionResults;
    }

    @Override // org.jasypt.digest.config.DigesterConfig
    public Boolean getUseLenientSaltSizeCheck() {
        return this.useLenientSaltSizeCheck;
    }

    @Override // org.jasypt.digest.config.DigesterConfig
    public Integer getPoolSize() {
        return this.poolSize;
    }
}
