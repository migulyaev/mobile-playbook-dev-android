package org.jasypt.salt;

import org.jasypt.commons.CommonUtils;
import org.jasypt.exceptions.EncryptionInitializationException;

/* loaded from: classes.dex */
public class ByteArrayFixedSaltGenerator implements FixedSaltGenerator {
    private final byte[] salt;

    public ByteArrayFixedSaltGenerator(byte[] salt) {
        CommonUtils.validateNotNull(salt, "Salt cannot be set null");
        this.salt = (byte[]) salt.clone();
    }

    @Override // org.jasypt.salt.SaltGenerator
    public byte[] generateSalt(int lengthBytes) {
        if (this.salt.length < lengthBytes) {
            throw new EncryptionInitializationException("Requested salt larger than set");
        }
        byte[] generatedSalt = new byte[lengthBytes];
        System.arraycopy(this.salt, 0, generatedSalt, 0, lengthBytes);
        return generatedSalt;
    }

    @Override // org.jasypt.salt.SaltGenerator
    public boolean includePlainSaltInEncryptionResults() {
        return false;
    }
}
