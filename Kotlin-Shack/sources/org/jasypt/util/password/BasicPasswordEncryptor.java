package org.jasypt.util.password;

import org.jasypt.digest.StandardStringDigester;

/* loaded from: classes.dex */
public final class BasicPasswordEncryptor implements PasswordEncryptor {
    private final StandardStringDigester digester = new StandardStringDigester();

    public BasicPasswordEncryptor() {
        this.digester.initialize();
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
