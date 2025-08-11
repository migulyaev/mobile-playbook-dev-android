package org.jasypt.util.password;

/* loaded from: classes.dex */
public interface PasswordEncryptor {
    boolean checkPassword(String str, String str2);

    String encryptPassword(String str);
}
