package org.jasypt.encryption.pbe;

import java.security.Provider;
import org.jasypt.commons.CommonUtils;
import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.encryption.pbe.config.StringPBEConfig;
import org.jasypt.exceptions.AlreadyInitializedException;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.salt.SaltGenerator;

/* loaded from: classes.dex */
public final class StandardPBEStringEncryptor implements PBEStringCleanablePasswordEncryptor {
    public static final String DEFAULT_STRING_OUTPUT_TYPE = "base64";
    private static final String ENCRYPTED_MESSAGE_CHARSET = "US-ASCII";
    private static final String MESSAGE_CHARSET = "UTF-8";
    private final Base64 base64;
    private final StandardPBEByteEncryptor byteEncryptor;
    private String stringOutputType;
    private boolean stringOutputTypeBase64;
    private boolean stringOutputTypeSet;
    private StringPBEConfig stringPBEConfig;

    public StandardPBEStringEncryptor() {
        this.stringPBEConfig = null;
        this.stringOutputType = "base64";
        this.stringOutputTypeBase64 = true;
        this.stringOutputTypeSet = false;
        this.byteEncryptor = new StandardPBEByteEncryptor();
        this.base64 = new Base64();
    }

    private StandardPBEStringEncryptor(StandardPBEByteEncryptor standardPBEByteEncryptor) {
        this.stringPBEConfig = null;
        this.stringOutputType = "base64";
        this.stringOutputTypeBase64 = true;
        this.stringOutputTypeSet = false;
        this.byteEncryptor = standardPBEByteEncryptor;
        this.base64 = new Base64();
    }

    public synchronized void setConfig(PBEConfig config) {
        this.byteEncryptor.setConfig(config);
        if (config != null && (config instanceof StringPBEConfig)) {
            this.stringPBEConfig = (StringPBEConfig) config;
        }
    }

    public void setAlgorithm(String algorithm) {
        this.byteEncryptor.setAlgorithm(algorithm);
    }

    @Override // org.jasypt.encryption.pbe.PasswordBased
    public void setPassword(String password) {
        this.byteEncryptor.setPassword(password);
    }

    @Override // org.jasypt.encryption.pbe.CleanablePasswordBased
    public void setPasswordCharArray(char[] password) {
        this.byteEncryptor.setPasswordCharArray(password);
    }

    public void setKeyObtentionIterations(int keyObtentionIterations) {
        this.byteEncryptor.setKeyObtentionIterations(keyObtentionIterations);
    }

    public void setSaltGenerator(SaltGenerator saltGenerator) {
        this.byteEncryptor.setSaltGenerator(saltGenerator);
    }

    public void setProviderName(String providerName) {
        this.byteEncryptor.setProviderName(providerName);
    }

    public void setProvider(Provider provider) {
        this.byteEncryptor.setProvider(provider);
    }

    public synchronized void setStringOutputType(String stringOutputType) {
        CommonUtils.validateNotEmpty(stringOutputType, "String output type cannot be set empty");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
        this.stringOutputTypeSet = true;
    }

    synchronized StandardPBEStringEncryptor[] cloneAndInitializeEncryptor(int size) {
        StandardPBEStringEncryptor[] clones;
        StandardPBEByteEncryptor[] byteEncryptorClones = this.byteEncryptor.cloneAndInitializeEncryptor(size);
        initializeSpecifics();
        clones = new StandardPBEStringEncryptor[size];
        clones[0] = this;
        for (int i = 1; i < size; i++) {
            clones[i] = new StandardPBEStringEncryptor(byteEncryptorClones[i]);
            if (CommonUtils.isNotEmpty(this.stringOutputType)) {
                clones[i].setStringOutputType(this.stringOutputType);
            }
        }
        return clones;
    }

    public boolean isInitialized() {
        return this.byteEncryptor.isInitialized();
    }

    public synchronized void initialize() {
        if (!isInitialized()) {
            initializeSpecifics();
            this.byteEncryptor.initialize();
        }
    }

    private void initializeSpecifics() {
        if (this.stringPBEConfig != null) {
            String configStringOutputType = this.stringPBEConfig.getStringOutputType();
            this.stringOutputType = (this.stringOutputTypeSet || configStringOutputType == null) ? this.stringOutputType : configStringOutputType;
        }
        this.stringOutputTypeBase64 = "base64".equalsIgnoreCase(this.stringOutputType);
    }

    @Override // org.jasypt.encryption.StringEncryptor
    public String encrypt(String message) {
        if (message == null) {
            return null;
        }
        if (!isInitialized()) {
            initialize();
        }
        try {
            byte[] messageBytes = message.getBytes("UTF-8");
            byte[] encryptedMessage = this.byteEncryptor.encrypt(messageBytes);
            if (this.stringOutputTypeBase64) {
                String result = new String(this.base64.encode(encryptedMessage), "US-ASCII");
                return result;
            }
            String result2 = CommonUtils.toHexadecimal(encryptedMessage);
            return result2;
        } catch (EncryptionInitializationException e) {
            throw e;
        } catch (EncryptionOperationNotPossibleException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new EncryptionOperationNotPossibleException();
        }
    }

    @Override // org.jasypt.encryption.StringEncryptor
    public String decrypt(String encryptedMessage) {
        byte[] encryptedMessageBytes;
        if (encryptedMessage == null) {
            return null;
        }
        if (!isInitialized()) {
            initialize();
        }
        try {
            if (this.stringOutputTypeBase64) {
                byte[] encryptedMessageBytes2 = encryptedMessage.getBytes("US-ASCII");
                encryptedMessageBytes = this.base64.decode(encryptedMessageBytes2);
            } else {
                encryptedMessageBytes = CommonUtils.fromHexadecimal(encryptedMessage);
            }
            byte[] message = this.byteEncryptor.decrypt(encryptedMessageBytes);
            return new String(message, "UTF-8");
        } catch (EncryptionInitializationException e) {
            throw e;
        } catch (EncryptionOperationNotPossibleException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new EncryptionOperationNotPossibleException();
        }
    }
}
