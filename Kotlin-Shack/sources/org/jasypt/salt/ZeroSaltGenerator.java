package org.jasypt.salt;

import java.util.Arrays;

/* loaded from: classes.dex */
public class ZeroSaltGenerator implements SaltGenerator {
    @Override // org.jasypt.salt.SaltGenerator
    public byte[] generateSalt(int lengthBytes) {
        byte[] result = new byte[lengthBytes];
        Arrays.fill(result, (byte) 0);
        return result;
    }

    @Override // org.jasypt.salt.SaltGenerator
    public boolean includePlainSaltInEncryptionResults() {
        return false;
    }
}
