package org.jasypt.salt;

/* loaded from: classes.dex */
public interface SaltGenerator {
    byte[] generateSalt(int i);

    boolean includePlainSaltInEncryptionResults();
}
