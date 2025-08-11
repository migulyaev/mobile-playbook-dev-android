package org.jasypt.util.numeric;

import java.math.BigInteger;
import org.jasypt.encryption.pbe.StandardPBEBigIntegerEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;

/* loaded from: classes.dex */
public final class BasicIntegerNumberEncryptor implements IntegerNumberEncryptor {
    private final StandardPBEBigIntegerEncryptor encryptor = new StandardPBEBigIntegerEncryptor();

    public BasicIntegerNumberEncryptor() {
        this.encryptor.setAlgorithm(StandardPBEByteEncryptor.DEFAULT_ALGORITHM);
    }

    public void setPassword(String password) {
        this.encryptor.setPassword(password);
    }

    public void setPasswordCharArray(char[] password) {
        this.encryptor.setPasswordCharArray(password);
    }

    @Override // org.jasypt.util.numeric.IntegerNumberEncryptor
    public BigInteger encrypt(BigInteger number) {
        return this.encryptor.encrypt(number);
    }

    @Override // org.jasypt.util.numeric.IntegerNumberEncryptor
    public BigInteger decrypt(BigInteger encryptedNumber) {
        return this.encryptor.decrypt(encryptedNumber);
    }
}
