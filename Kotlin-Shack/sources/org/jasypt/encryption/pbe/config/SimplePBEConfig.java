package org.jasypt.encryption.pbe.config;

import java.security.Provider;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.PasswordAlreadyCleanedException;
import org.jasypt.salt.SaltGenerator;

/* loaded from: classes.dex */
public class SimplePBEConfig implements PBEConfig, PBECleanablePasswordConfig {
    private String algorithm = null;
    private char[] password = null;
    private Integer keyObtentionIterations = null;
    private SaltGenerator saltGenerator = null;
    private String providerName = null;
    private Provider provider = null;
    private Integer poolSize = null;
    private boolean passwordCleaned = false;

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setPassword(String password) {
        if (this.password != null) {
            cleanPassword();
        }
        if (password == null) {
            this.password = null;
        } else {
            this.password = password.toCharArray();
        }
    }

    public void setPasswordCharArray(char[] password) {
        if (this.password != null) {
            cleanPassword();
        }
        if (password == null) {
            this.password = null;
        } else {
            this.password = new char[password.length];
            System.arraycopy(password, 0, this.password, 0, password.length);
        }
    }

    public void setKeyObtentionIterations(Integer keyObtentionIterations) {
        this.keyObtentionIterations = keyObtentionIterations;
    }

    public void setKeyObtentionIterations(String keyObtentionIterations) {
        if (keyObtentionIterations != null) {
            try {
                this.keyObtentionIterations = new Integer(keyObtentionIterations);
                return;
            } catch (NumberFormatException e) {
                throw new EncryptionInitializationException(e);
            }
        }
        this.keyObtentionIterations = null;
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

    @Override // org.jasypt.encryption.pbe.config.PBEConfig
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override // org.jasypt.encryption.pbe.config.PBEConfig
    public String getPassword() {
        if (this.passwordCleaned) {
            throw new PasswordAlreadyCleanedException();
        }
        return new String(this.password);
    }

    @Override // org.jasypt.encryption.pbe.config.PBECleanablePasswordConfig
    public char[] getPasswordCharArray() {
        if (this.passwordCleaned) {
            throw new PasswordAlreadyCleanedException();
        }
        char[] result = new char[this.password.length];
        System.arraycopy(this.password, 0, result, 0, this.password.length);
        return result;
    }

    @Override // org.jasypt.encryption.pbe.config.PBEConfig
    public Integer getKeyObtentionIterations() {
        return this.keyObtentionIterations;
    }

    @Override // org.jasypt.encryption.pbe.config.PBEConfig
    public SaltGenerator getSaltGenerator() {
        return this.saltGenerator;
    }

    @Override // org.jasypt.encryption.pbe.config.PBEConfig
    public String getProviderName() {
        return this.providerName;
    }

    @Override // org.jasypt.encryption.pbe.config.PBEConfig
    public Provider getProvider() {
        return this.provider;
    }

    @Override // org.jasypt.encryption.pbe.config.PBEConfig
    public Integer getPoolSize() {
        return this.poolSize;
    }

    @Override // org.jasypt.encryption.pbe.config.PBECleanablePasswordConfig
    public void cleanPassword() {
        if (this.password != null) {
            int pwdLength = this.password.length;
            for (int i = 0; i < pwdLength; i++) {
                this.password[i] = 0;
            }
            this.password = new char[0];
        }
        this.passwordCleaned = true;
    }
}
