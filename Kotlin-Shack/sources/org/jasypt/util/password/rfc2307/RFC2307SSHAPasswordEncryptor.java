package org.jasypt.util.password.rfc2307;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.util.password.PasswordEncryptor;

/* loaded from: classes.dex */
public final class RFC2307SSHAPasswordEncryptor implements PasswordEncryptor {
    private final StandardStringDigester digester = new StandardStringDigester();

    public RFC2307SSHAPasswordEncryptor() {
        this.digester.setAlgorithm("SHA-1");
        this.digester.setIterations(1);
        this.digester.setSaltSizeBytes(8);
        this.digester.setPrefix("{SSHA}");
        this.digester.setInvertPositionOfSaltInMessageBeforeDigesting(true);
        this.digester.setInvertPositionOfPlainSaltInEncryptionResults(true);
        this.digester.setUseLenientSaltSizeCheck(true);
    }

    public void setSaltSizeBytes(int saltSizeBytes) {
        this.digester.setSaltSizeBytes(saltSizeBytes);
    }

    public void setStringOutputType(String stringOutputType) {
        this.digester.setStringOutputType(stringOutputType);
    }

    @Override // org.jasypt.util.password.PasswordEncryptor
    public String encryptPassword(String password) {
        return this.digester.digest(password);
    }

    @Override // org.jasypt.util.password.PasswordEncryptor
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return this.digester.matches(plainPassword, encryptedPassword);
    }
}
