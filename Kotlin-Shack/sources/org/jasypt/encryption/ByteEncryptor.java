package org.jasypt.encryption;

/* loaded from: classes.dex */
public interface ByteEncryptor {
    byte[] decrypt(byte[] bArr);

    byte[] encrypt(byte[] bArr);
}
