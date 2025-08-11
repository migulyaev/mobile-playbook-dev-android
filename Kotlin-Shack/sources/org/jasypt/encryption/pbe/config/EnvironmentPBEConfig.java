package org.jasypt.encryption.pbe.config;

import java.security.Provider;
import org.jasypt.salt.SaltGenerator;

/* loaded from: classes.dex */
public class EnvironmentPBEConfig extends SimplePBEConfig {
    private String algorithmEnvName = null;
    private String keyObtentionIterationsEnvName = null;
    private String passwordEnvName = null;
    private String saltGeneratorClassNameEnvName = null;
    private String providerNameEnvName = null;
    private String providerClassNameEnvName = null;
    private String poolSizeEnvName = null;
    private String algorithmSysPropertyName = null;
    private String keyObtentionIterationsSysPropertyName = null;
    private String passwordSysPropertyName = null;
    private String saltGeneratorClassNameSysPropertyName = null;
    private String providerNameSysPropertyName = null;
    private String providerClassNameSysPropertyName = null;
    private String poolSizeSysPropertyName = null;

    public String getAlgorithmEnvName() {
        return this.algorithmEnvName;
    }

    public void setAlgorithmEnvName(String algorithmEnvName) {
        this.algorithmEnvName = algorithmEnvName;
        if (algorithmEnvName == null) {
            super.setAlgorithm(null);
        } else {
            this.algorithmSysPropertyName = null;
            super.setAlgorithm(System.getenv(algorithmEnvName));
        }
    }

    public String getAlgorithmSysPropertyName() {
        return this.algorithmSysPropertyName;
    }

    public void setAlgorithmSysPropertyName(String algorithmSysPropertyName) {
        this.algorithmSysPropertyName = algorithmSysPropertyName;
        if (algorithmSysPropertyName == null) {
            super.setAlgorithm(null);
        } else {
            this.algorithmEnvName = null;
            super.setAlgorithm(System.getProperty(algorithmSysPropertyName));
        }
    }

    public String getKeyObtentionIterationsEnvName() {
        return this.keyObtentionIterationsEnvName;
    }

    public void setKeyObtentionIterationsEnvName(String keyObtentionIterationsEnvName) {
        this.keyObtentionIterationsEnvName = keyObtentionIterationsEnvName;
        if (keyObtentionIterationsEnvName == null) {
            super.setKeyObtentionIterations((Integer) null);
        } else {
            this.keyObtentionIterationsSysPropertyName = null;
            super.setKeyObtentionIterations(System.getenv(keyObtentionIterationsEnvName));
        }
    }

    public String getKeyObtentionIterationsSysPropertyName() {
        return this.keyObtentionIterationsSysPropertyName;
    }

    public void setKeyObtentionIterationsSysPropertyName(String keyObtentionIterationsSysPropertyName) {
        this.keyObtentionIterationsSysPropertyName = keyObtentionIterationsSysPropertyName;
        if (keyObtentionIterationsSysPropertyName == null) {
            super.setKeyObtentionIterations((Integer) null);
        } else {
            this.keyObtentionIterationsEnvName = null;
            super.setKeyObtentionIterations(System.getProperty(keyObtentionIterationsSysPropertyName));
        }
    }

    public String getPasswordEnvName() {
        return this.passwordEnvName;
    }

    public void setPasswordEnvName(String passwordEnvName) {
        this.passwordEnvName = passwordEnvName;
        if (passwordEnvName == null) {
            super.setPassword(null);
        } else {
            this.passwordSysPropertyName = null;
            super.setPassword(System.getenv(passwordEnvName));
        }
    }

    public String getPasswordSysPropertyName() {
        return this.passwordSysPropertyName;
    }

    public void setPasswordSysPropertyName(String passwordSysPropertyName) {
        this.passwordSysPropertyName = passwordSysPropertyName;
        if (passwordSysPropertyName == null) {
            super.setPassword(null);
        } else {
            this.passwordEnvName = null;
            super.setPassword(System.getProperty(passwordSysPropertyName));
        }
    }

    public String getSaltGeneratorClassNameEnvName() {
        return this.saltGeneratorClassNameEnvName;
    }

    public void setSaltGeneratorClassNameEnvName(String saltGeneratorClassNameEnvName) {
        this.saltGeneratorClassNameEnvName = saltGeneratorClassNameEnvName;
        if (saltGeneratorClassNameEnvName == null) {
            super.setSaltGenerator(null);
            return;
        }
        this.saltGeneratorClassNameSysPropertyName = null;
        String saltGeneratorClassName = System.getenv(saltGeneratorClassNameEnvName);
        super.setSaltGeneratorClassName(saltGeneratorClassName);
    }

    public String getSaltGeneratorClassNameSysPropertyName() {
        return this.saltGeneratorClassNameSysPropertyName;
    }

    public void setSaltGeneratorClassNameSysPropertyName(String saltGeneratorClassNameSysPropertyName) {
        this.saltGeneratorClassNameSysPropertyName = saltGeneratorClassNameSysPropertyName;
        if (saltGeneratorClassNameSysPropertyName == null) {
            super.setSaltGenerator(null);
            return;
        }
        this.saltGeneratorClassNameEnvName = null;
        String saltGeneratorClassName = System.getProperty(saltGeneratorClassNameSysPropertyName);
        super.setSaltGeneratorClassName(saltGeneratorClassName);
    }

    public String getProviderNameEnvName() {
        return this.providerNameEnvName;
    }

    public void setProviderNameEnvName(String providerNameEnvName) {
        this.providerNameEnvName = providerNameEnvName;
        if (providerNameEnvName == null) {
            super.setProviderName(null);
        } else {
            this.providerNameSysPropertyName = null;
            super.setProviderName(System.getenv(providerNameEnvName));
        }
    }

    public String getProviderNameSysPropertyName() {
        return this.providerNameSysPropertyName;
    }

    public void setProviderNameSysPropertyName(String providerNameSysPropertyName) {
        this.providerNameSysPropertyName = providerNameSysPropertyName;
        if (providerNameSysPropertyName == null) {
            super.setProviderName(null);
        } else {
            this.providerNameEnvName = null;
            super.setProviderName(System.getProperty(providerNameSysPropertyName));
        }
    }

    public String getProviderClassNameEnvName() {
        return this.providerClassNameEnvName;
    }

    public void setProviderClassNameEnvName(String providerClassNameEnvName) {
        this.providerClassNameEnvName = providerClassNameEnvName;
        if (providerClassNameEnvName == null) {
            super.setProvider(null);
            return;
        }
        this.providerClassNameSysPropertyName = null;
        String providerClassName = System.getenv(providerClassNameEnvName);
        super.setProviderClassName(providerClassName);
    }

    public String getProviderClassNameSysPropertyName() {
        return this.providerClassNameSysPropertyName;
    }

    public void setProviderClassNameSysPropertyName(String providerClassNameSysPropertyName) {
        this.providerClassNameSysPropertyName = providerClassNameSysPropertyName;
        if (providerClassNameSysPropertyName == null) {
            super.setProvider(null);
            return;
        }
        this.providerClassNameEnvName = null;
        String providerClassName = System.getProperty(providerClassNameSysPropertyName);
        super.setProviderClassName(providerClassName);
    }

    public String getPoolSizeEnvName() {
        return this.poolSizeEnvName;
    }

    public void setPoolSizeEnvName(String poolSizeEnvName) {
        this.poolSizeEnvName = poolSizeEnvName;
        if (poolSizeEnvName == null) {
            super.setPoolSize((String) null);
        } else {
            this.poolSizeSysPropertyName = null;
            super.setPoolSize(System.getenv(poolSizeEnvName));
        }
    }

    public String getPoolSizeSysPropertyName() {
        return this.poolSizeSysPropertyName;
    }

    public void setPoolSizeSysPropertyName(String poolSizeSysPropertyName) {
        this.poolSizeSysPropertyName = poolSizeSysPropertyName;
        if (poolSizeSysPropertyName == null) {
            super.setPoolSize((String) null);
        } else {
            this.poolSizeEnvName = null;
            super.setPoolSize(System.getProperty(poolSizeSysPropertyName));
        }
    }

    @Override // org.jasypt.encryption.pbe.config.SimplePBEConfig
    public void setAlgorithm(String algorithm) {
        this.algorithmEnvName = null;
        this.algorithmSysPropertyName = null;
        super.setAlgorithm(algorithm);
    }

    @Override // org.jasypt.encryption.pbe.config.SimplePBEConfig
    public void setKeyObtentionIterations(Integer keyObtentionIterations) {
        this.keyObtentionIterationsEnvName = null;
        this.keyObtentionIterationsSysPropertyName = null;
        super.setKeyObtentionIterations(keyObtentionIterations);
    }

    @Override // org.jasypt.encryption.pbe.config.SimplePBEConfig
    public void setKeyObtentionIterations(String keyObtentionIterations) {
        this.keyObtentionIterationsEnvName = null;
        this.keyObtentionIterationsSysPropertyName = null;
        super.setKeyObtentionIterations(keyObtentionIterations);
    }

    @Override // org.jasypt.encryption.pbe.config.SimplePBEConfig
    public void setPassword(String password) {
        this.passwordEnvName = null;
        this.passwordSysPropertyName = null;
        super.setPassword(password);
    }

    @Override // org.jasypt.encryption.pbe.config.SimplePBEConfig
    public void setPasswordCharArray(char[] password) {
        this.passwordEnvName = null;
        this.passwordSysPropertyName = null;
        super.setPasswordCharArray(password);
    }

    @Override // org.jasypt.encryption.pbe.config.SimplePBEConfig
    public void setSaltGenerator(SaltGenerator saltGenerator) {
        this.saltGeneratorClassNameEnvName = null;
        this.saltGeneratorClassNameSysPropertyName = null;
        super.setSaltGenerator(saltGenerator);
    }

    @Override // org.jasypt.encryption.pbe.config.SimplePBEConfig
    public void setSaltGeneratorClassName(String saltGeneratorClassName) {
        this.saltGeneratorClassNameEnvName = null;
        this.saltGeneratorClassNameSysPropertyName = null;
        super.setSaltGeneratorClassName(saltGeneratorClassName);
    }

    @Override // org.jasypt.encryption.pbe.config.SimplePBEConfig
    public void setProviderName(String providerName) {
        this.providerNameEnvName = null;
        this.providerNameSysPropertyName = null;
        super.setProviderName(providerName);
    }

    @Override // org.jasypt.encryption.pbe.config.SimplePBEConfig
    public void setProvider(Provider provider) {
        this.providerClassNameEnvName = null;
        this.providerClassNameSysPropertyName = null;
        super.setProvider(provider);
    }

    @Override // org.jasypt.encryption.pbe.config.SimplePBEConfig
    public void setProviderClassName(String providerClassName) {
        this.providerClassNameEnvName = null;
        this.providerClassNameSysPropertyName = null;
        super.setProviderClassName(providerClassName);
    }

    @Override // org.jasypt.encryption.pbe.config.SimplePBEConfig
    public void setPoolSize(Integer poolSize) {
        this.poolSizeEnvName = null;
        this.poolSizeSysPropertyName = null;
        super.setPoolSize(poolSize);
    }

    @Override // org.jasypt.encryption.pbe.config.SimplePBEConfig
    public void setPoolSize(String poolSize) {
        this.poolSizeEnvName = null;
        this.poolSizeSysPropertyName = null;
        super.setPoolSize(poolSize);
    }
}
