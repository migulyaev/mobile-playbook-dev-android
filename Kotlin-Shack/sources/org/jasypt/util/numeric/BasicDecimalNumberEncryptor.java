package org.jasypt.util.numeric;

import java.math.BigDecimal;
import org.jasypt.encryption.pbe.StandardPBEBigDecimalEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;

/* loaded from: classes.dex */
public final class BasicDecimalNumberEncryptor implements DecimalNumberEncryptor {
    private final StandardPBEBigDecimalEncryptor encryptor = new StandardPBEBigDecimalEncryptor();

    public BasicDecimalNumberEncryptor() {
        this.encryptor.setAlgorithm(StandardPBEByteEncryptor.DEFAULT_ALGORITHM);
    }

    public void setPassword(String password) {
        this.encryptor.setPassword(password);
    }

    public void setPasswordCharArray(char[] password) {
        this.encryptor.setPasswordCharArray(password);
    }

    @Override // org.jasypt.util.numeric.DecimalNumberEncryptor
    public BigDecimal encrypt(BigDecimal number) {
        return this.encryptor.encrypt(number);
    }

    @Override // org.jasypt.util.numeric.DecimalNumberEncryptor
    public BigDecimal decrypt(BigDecimal encryptedNumber) {
        return this.encryptor.decrypt(encryptedNumber);
    }
}
