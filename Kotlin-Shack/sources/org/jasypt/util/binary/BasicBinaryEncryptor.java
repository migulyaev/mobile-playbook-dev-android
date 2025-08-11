package org.jasypt.util.binary;

import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;

/* loaded from: classes.dex */
public final class BasicBinaryEncryptor implements BinaryEncryptor {
    private final StandardPBEByteEncryptor encryptor = new StandardPBEByteEncryptor();

    public BasicBinaryEncryptor() {
        this.encryptor.setAlgorithm(StandardPBEByteEncryptor.DEFAULT_ALGORITHM);
    }

    public void setPassword(String password) {
        this.encryptor.setPassword(password);
    }

    public void setPasswordCharArray(char[] password) {
        this.encryptor.setPasswordCharArray(password);
    }

    @Override // org.jasypt.util.binary.BinaryEncryptor
    public byte[] encrypt(byte[] binary) {
        return this.encryptor.encrypt(binary);
    }

    @Override // org.jasypt.util.binary.BinaryEncryptor
    public byte[] decrypt(byte[] encryptedBinary) {
        return this.encryptor.decrypt(encryptedBinary);
    }
}
