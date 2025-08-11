package org.jasypt.encryption.pbe;

import java.math.BigInteger;
import java.security.Provider;
import org.jasypt.commons.CommonUtils;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.salt.SaltGenerator;

/* loaded from: classes.dex */
public final class StandardPBEBigIntegerEncryptor implements PBEBigIntegerCleanablePasswordEncryptor {
    private final StandardPBEByteEncryptor byteEncryptor;

    public StandardPBEBigIntegerEncryptor() {
        this.byteEncryptor = new StandardPBEByteEncryptor();
    }

    private StandardPBEBigIntegerEncryptor(StandardPBEByteEncryptor standardPBEByteEncryptor) {
        this.byteEncryptor = standardPBEByteEncryptor;
    }

    public void setConfig(PBEConfig config) {
        this.byteEncryptor.setConfig(config);
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

    synchronized StandardPBEBigIntegerEncryptor[] cloneAndInitializeEncryptor(int size) {
        StandardPBEBigIntegerEncryptor[] clones;
        StandardPBEByteEncryptor[] byteEncryptorClones = this.byteEncryptor.cloneAndInitializeEncryptor(size);
        clones = new StandardPBEBigIntegerEncryptor[size];
        clones[0] = this;
        for (int i = 1; i < size; i++) {
            clones[i] = new StandardPBEBigIntegerEncryptor(byteEncryptorClones[i]);
        }
        return clones;
    }

    public boolean isInitialized() {
        return this.byteEncryptor.isInitialized();
    }

    public void initialize() {
        this.byteEncryptor.initialize();
    }

    @Override // org.jasypt.encryption.BigIntegerEncryptor
    public BigInteger encrypt(BigInteger message) {
        if (message == null) {
            return null;
        }
        try {
            byte[] messageBytes = message.toByteArray();
            byte[] encryptedMessage = this.byteEncryptor.encrypt(messageBytes);
            byte[] encryptedMessageLengthBytes = NumberUtils.byteArrayFromInt(encryptedMessage.length);
            byte[] encryptionResult = CommonUtils.appendArrays(encryptedMessage, encryptedMessageLengthBytes);
            return new BigInteger(encryptionResult);
        } catch (EncryptionInitializationException e) {
            throw e;
        } catch (EncryptionOperationNotPossibleException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new EncryptionOperationNotPossibleException();
        }
    }

    @Override // org.jasypt.encryption.BigIntegerEncryptor
    public BigInteger decrypt(BigInteger encryptedMessage) {
        if (encryptedMessage == null) {
            return null;
        }
        try {
            byte[] encryptedMessageBytes = encryptedMessage.toByteArray();
            byte[] message = this.byteEncryptor.decrypt(NumberUtils.processBigIntegerEncryptedByteArray(encryptedMessageBytes, encryptedMessage.signum()));
            return new BigInteger(message);
        } catch (EncryptionInitializationException e) {
            throw e;
        } catch (EncryptionOperationNotPossibleException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new EncryptionOperationNotPossibleException();
        }
    }
}
