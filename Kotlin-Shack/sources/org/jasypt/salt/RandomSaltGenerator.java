package org.jasypt.salt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.jasypt.exceptions.EncryptionInitializationException;

/* loaded from: classes.dex */
public class RandomSaltGenerator implements SaltGenerator {
    public static final String DEFAULT_SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
    private final SecureRandom random;

    public RandomSaltGenerator() {
        this(DEFAULT_SECURE_RANDOM_ALGORITHM);
    }

    public RandomSaltGenerator(String secureRandomAlgorithm) {
        try {
            this.random = SecureRandom.getInstance(secureRandomAlgorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptionInitializationException(e);
        }
    }

    @Override // org.jasypt.salt.SaltGenerator
    public byte[] generateSalt(int lengthBytes) {
        byte[] salt = new byte[lengthBytes];
        synchronized (this.random) {
            this.random.nextBytes(salt);
        }
        return salt;
    }

    @Override // org.jasypt.salt.SaltGenerator
    public boolean includePlainSaltInEncryptionResults() {
        return true;
    }
}
