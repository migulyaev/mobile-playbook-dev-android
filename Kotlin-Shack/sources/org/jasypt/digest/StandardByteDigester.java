package org.jasypt.digest;

import java.security.MessageDigest;
import java.security.Provider;
import org.jasypt.commons.CommonUtils;
import org.jasypt.digest.config.DigesterConfig;
import org.jasypt.exceptions.AlreadyInitializedException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.salt.SaltGenerator;

/* loaded from: classes.dex */
public final class StandardByteDigester implements ByteDigester {
    public static final String DEFAULT_ALGORITHM = "MD5";
    public static final int DEFAULT_ITERATIONS = 1000;
    public static final int DEFAULT_SALT_SIZE_BYTES = 8;
    private String algorithm = "MD5";
    private int saltSizeBytes = 8;
    private int iterations = 1000;
    private SaltGenerator saltGenerator = null;
    private String providerName = null;
    private Provider provider = null;
    private boolean invertPositionOfSaltInMessageBeforeDigesting = false;
    private boolean invertPositionOfPlainSaltInEncryptionResults = false;
    private boolean useLenientSaltSizeCheck = false;
    private DigesterConfig config = null;
    private boolean algorithmSet = false;
    private boolean saltSizeBytesSet = false;
    private boolean iterationsSet = false;
    private boolean saltGeneratorSet = false;
    private boolean providerNameSet = false;
    private boolean providerSet = false;
    private boolean invertPositionOfSaltInMessageBeforeDigestingSet = false;
    private boolean invertPositionOfPlainSaltInEncryptionResultsSet = false;
    private boolean useLenientSaltSizeCheckSet = false;
    private boolean initialized = false;
    private boolean useSalt = true;
    private MessageDigest md = null;
    private int digestLengthBytes = 0;

    public synchronized void setConfig(DigesterConfig config) {
        CommonUtils.validateNotNull(config, "Config cannot be set null");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.config = config;
    }

    public synchronized void setAlgorithm(String algorithm) {
        CommonUtils.validateNotEmpty(algorithm, "Algorithm cannot be empty");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.algorithm = algorithm;
        this.algorithmSet = true;
    }

    public synchronized void setSaltSizeBytes(int saltSizeBytes) {
        CommonUtils.validateIsTrue(saltSizeBytes >= 0, "Salt size in bytes must be non-negative");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.saltSizeBytes = saltSizeBytes;
        this.useSalt = saltSizeBytes > 0;
        this.saltSizeBytesSet = true;
    }

    public synchronized void setIterations(int iterations) {
        CommonUtils.validateIsTrue(iterations > 0, "Number of iterations must be greater than zero");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.iterations = iterations;
        this.iterationsSet = true;
    }

    public synchronized void setSaltGenerator(SaltGenerator saltGenerator) {
        CommonUtils.validateNotNull(saltGenerator, "Salt generator cannot be set null");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.saltGenerator = saltGenerator;
        this.saltGeneratorSet = true;
    }

    public synchronized void setProviderName(String providerName) {
        CommonUtils.validateNotNull(providerName, "Provider name cannot be set null");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.providerName = providerName;
        this.providerNameSet = true;
    }

    public synchronized void setProvider(Provider provider) {
        CommonUtils.validateNotNull(provider, "Provider cannot be set null");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.provider = provider;
        this.providerSet = true;
    }

    public synchronized void setInvertPositionOfSaltInMessageBeforeDigesting(boolean invertPositionOfSaltInMessageBeforeDigesting) {
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.invertPositionOfSaltInMessageBeforeDigesting = invertPositionOfSaltInMessageBeforeDigesting;
        this.invertPositionOfSaltInMessageBeforeDigestingSet = true;
    }

    public synchronized void setInvertPositionOfPlainSaltInEncryptionResults(boolean invertPositionOfPlainSaltInEncryptionResults) {
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.invertPositionOfPlainSaltInEncryptionResults = invertPositionOfPlainSaltInEncryptionResults;
        this.invertPositionOfPlainSaltInEncryptionResultsSet = true;
    }

    public synchronized void setUseLenientSaltSizeCheck(boolean useLenientSaltSizeCheck) {
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.useLenientSaltSizeCheck = useLenientSaltSizeCheck;
        this.useLenientSaltSizeCheckSet = true;
    }

    StandardByteDigester cloneDigester() {
        if (!isInitialized()) {
            initialize();
        }
        StandardByteDigester cloned = new StandardByteDigester();
        if (CommonUtils.isNotEmpty(this.algorithm)) {
            cloned.setAlgorithm(this.algorithm);
        }
        cloned.setInvertPositionOfPlainSaltInEncryptionResults(this.invertPositionOfPlainSaltInEncryptionResults);
        cloned.setInvertPositionOfSaltInMessageBeforeDigesting(this.invertPositionOfSaltInMessageBeforeDigesting);
        cloned.setIterations(this.iterations);
        if (this.provider != null) {
            cloned.setProvider(this.provider);
        }
        if (this.providerName != null) {
            cloned.setProviderName(this.providerName);
        }
        if (this.saltGenerator != null) {
            cloned.setSaltGenerator(this.saltGenerator);
        }
        cloned.setSaltSizeBytes(this.saltSizeBytes);
        cloned.setUseLenientSaltSizeCheck(this.useLenientSaltSizeCheck);
        return cloned;
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x008f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ac A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00c6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00d6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00e6 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void initialize() {
        /*
            Method dump skipped, instructions count: 431
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jasypt.digest.StandardByteDigester.initialize():void");
    }

    @Override // org.jasypt.digest.ByteDigester
    public byte[] digest(byte[] message) {
        if (message == null) {
            return null;
        }
        if (!isInitialized()) {
            initialize();
        }
        byte[] salt = null;
        if (this.useSalt) {
            salt = this.saltGenerator.generateSalt(this.saltSizeBytes);
        }
        return digest(message, salt);
    }

    private byte[] digest(byte[] message, byte[] salt) {
        byte[] digest;
        try {
            synchronized (this.md) {
                this.md.reset();
                if (salt != null) {
                    if (!this.invertPositionOfSaltInMessageBeforeDigesting) {
                        this.md.update(salt);
                        this.md.update(message);
                    } else {
                        this.md.update(message);
                        this.md.update(salt);
                    }
                } else {
                    this.md.update(message);
                }
                digest = this.md.digest();
                for (int i = 0; i < this.iterations - 1; i++) {
                    this.md.reset();
                    digest = this.md.digest(digest);
                }
            }
            if (this.saltGenerator.includePlainSaltInEncryptionResults() && salt != null) {
                if (!this.invertPositionOfPlainSaltInEncryptionResults) {
                    return CommonUtils.appendArrays(salt, digest);
                }
                return CommonUtils.appendArrays(digest, salt);
            }
            return digest;
        } catch (Exception e) {
            throw new EncryptionOperationNotPossibleException();
        }
    }

    @Override // org.jasypt.digest.ByteDigester
    public boolean matches(byte[] message, byte[] digest) {
        if (message == null) {
            return digest == null;
        }
        if (digest == null) {
            return false;
        }
        if (!isInitialized()) {
            initialize();
        }
        byte[] salt = null;
        try {
            if (this.useSalt) {
                if (this.saltGenerator.includePlainSaltInEncryptionResults()) {
                    int digestSaltSize = this.saltSizeBytes;
                    if (this.digestLengthBytes > 0) {
                        if (this.useLenientSaltSizeCheck) {
                            if (digest.length < this.digestLengthBytes) {
                                throw new EncryptionOperationNotPossibleException();
                            }
                            digestSaltSize = digest.length - this.digestLengthBytes;
                        } else if (digest.length != this.digestLengthBytes + this.saltSizeBytes) {
                            throw new EncryptionOperationNotPossibleException();
                        }
                    } else if (digest.length < this.saltSizeBytes) {
                        throw new EncryptionOperationNotPossibleException();
                    }
                    if (!this.invertPositionOfPlainSaltInEncryptionResults) {
                        salt = new byte[digestSaltSize];
                        System.arraycopy(digest, 0, salt, 0, digestSaltSize);
                    } else {
                        salt = new byte[digestSaltSize];
                        System.arraycopy(digest, digest.length - digestSaltSize, salt, 0, digestSaltSize);
                    }
                } else {
                    salt = this.saltGenerator.generateSalt(this.saltSizeBytes);
                }
            }
            byte[] encryptedMessage = digest(message, salt);
            return digestsAreEqual(encryptedMessage, digest);
        } catch (Exception e) {
            throw new EncryptionOperationNotPossibleException();
        }
    }

    private static boolean digestsAreEqual(byte[] a, byte[] b) {
        int aLen;
        if (a == null || b == null || b.length != (aLen = a.length)) {
            return false;
        }
        int match = 0;
        for (int match2 = 0; match2 < aLen; match2++) {
            match |= a[match2] ^ b[match2];
        }
        return match == 0;
    }
}
