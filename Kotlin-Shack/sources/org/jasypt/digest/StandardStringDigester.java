package org.jasypt.digest;

import java.security.Provider;
import org.jasypt.commons.CommonUtils;
import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.jasypt.digest.config.DigesterConfig;
import org.jasypt.digest.config.StringDigesterConfig;
import org.jasypt.exceptions.AlreadyInitializedException;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.normalization.Normalizer;
import org.jasypt.salt.SaltGenerator;

/* loaded from: classes.dex */
public final class StandardStringDigester implements StringDigester {
    public static final String DEFAULT_STRING_OUTPUT_TYPE = "base64";
    public static final boolean DEFAULT_UNICODE_NORMALIZATION_IGNORED = false;
    public static final String DIGEST_CHARSET = "US-ASCII";
    public static final String MESSAGE_CHARSET = "UTF-8";
    private final Base64 base64;
    private final StandardByteDigester byteDigester;
    private String prefix;
    private boolean prefixSet;
    private StringDigesterConfig stringDigesterConfig;
    private String stringOutputType;
    private boolean stringOutputTypeBase64;
    private boolean stringOutputTypeSet;
    private String suffix;
    private boolean suffixSet;
    private boolean unicodeNormalizationIgnored;
    private boolean unicodeNormalizationIgnoredSet;

    public StandardStringDigester() {
        this.stringDigesterConfig = null;
        this.unicodeNormalizationIgnored = false;
        this.stringOutputType = "base64";
        this.stringOutputTypeBase64 = true;
        this.prefix = null;
        this.suffix = null;
        this.unicodeNormalizationIgnoredSet = false;
        this.stringOutputTypeSet = false;
        this.prefixSet = false;
        this.suffixSet = false;
        this.byteDigester = new StandardByteDigester();
        this.base64 = new Base64();
    }

    private StandardStringDigester(StandardByteDigester standardByteDigester) {
        this.stringDigesterConfig = null;
        this.unicodeNormalizationIgnored = false;
        this.stringOutputType = "base64";
        this.stringOutputTypeBase64 = true;
        this.prefix = null;
        this.suffix = null;
        this.unicodeNormalizationIgnoredSet = false;
        this.stringOutputTypeSet = false;
        this.prefixSet = false;
        this.suffixSet = false;
        this.byteDigester = standardByteDigester;
        this.base64 = new Base64();
    }

    public synchronized void setConfig(DigesterConfig config) {
        this.byteDigester.setConfig(config);
        if (config != null && (config instanceof StringDigesterConfig)) {
            this.stringDigesterConfig = (StringDigesterConfig) config;
        }
    }

    public void setAlgorithm(String algorithm) {
        this.byteDigester.setAlgorithm(algorithm);
    }

    public void setSaltSizeBytes(int saltSizeBytes) {
        this.byteDigester.setSaltSizeBytes(saltSizeBytes);
    }

    public void setIterations(int iterations) {
        this.byteDigester.setIterations(iterations);
    }

    public void setSaltGenerator(SaltGenerator saltGenerator) {
        this.byteDigester.setSaltGenerator(saltGenerator);
    }

    public void setProviderName(String providerName) {
        this.byteDigester.setProviderName(providerName);
    }

    public void setProvider(Provider provider) {
        this.byteDigester.setProvider(provider);
    }

    public synchronized void setInvertPositionOfSaltInMessageBeforeDigesting(boolean invertPositionOfSaltInMessageBeforeDigesting) {
        this.byteDigester.setInvertPositionOfSaltInMessageBeforeDigesting(invertPositionOfSaltInMessageBeforeDigesting);
    }

    public synchronized void setInvertPositionOfPlainSaltInEncryptionResults(boolean invertPositionOfPlainSaltInEncryptionResults) {
        this.byteDigester.setInvertPositionOfPlainSaltInEncryptionResults(invertPositionOfPlainSaltInEncryptionResults);
    }

    public synchronized void setUseLenientSaltSizeCheck(boolean useLenientSaltSizeCheck) {
        this.byteDigester.setUseLenientSaltSizeCheck(useLenientSaltSizeCheck);
    }

    public synchronized void setUnicodeNormalizationIgnored(boolean unicodeNormalizationIgnored) {
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.unicodeNormalizationIgnored = unicodeNormalizationIgnored;
        this.unicodeNormalizationIgnoredSet = true;
    }

    public synchronized void setStringOutputType(String stringOutputType) {
        CommonUtils.validateNotEmpty(stringOutputType, "String output type cannot be set empty");
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
        this.stringOutputTypeSet = true;
    }

    public synchronized void setPrefix(String prefix) {
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.prefix = prefix;
        this.prefixSet = true;
    }

    public synchronized void setSuffix(String suffix) {
        if (isInitialized()) {
            throw new AlreadyInitializedException();
        }
        this.suffix = suffix;
        this.suffixSet = true;
    }

    StandardStringDigester cloneDigester() {
        if (!isInitialized()) {
            initialize();
        }
        StandardStringDigester cloned = new StandardStringDigester(this.byteDigester.cloneDigester());
        cloned.setPrefix(this.prefix);
        cloned.setSuffix(this.suffix);
        if (CommonUtils.isNotEmpty(this.stringOutputType)) {
            cloned.setStringOutputType(this.stringOutputType);
        }
        cloned.setUnicodeNormalizationIgnored(this.unicodeNormalizationIgnored);
        return cloned;
    }

    public boolean isInitialized() {
        return this.byteDigester.isInitialized();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0044 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0051 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void initialize() {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.isInitialized()     // Catch: java.lang.Throwable -> L6b
            if (r0 != 0) goto L69
            org.jasypt.digest.config.StringDigesterConfig r0 = r5.stringDigesterConfig     // Catch: java.lang.Throwable -> L6b
            if (r0 == 0) goto L5a
            org.jasypt.digest.config.StringDigesterConfig r0 = r5.stringDigesterConfig     // Catch: java.lang.Throwable -> L6b
            java.lang.Boolean r0 = r0.isUnicodeNormalizationIgnored()     // Catch: java.lang.Throwable -> L6b
            org.jasypt.digest.config.StringDigesterConfig r1 = r5.stringDigesterConfig     // Catch: java.lang.Throwable -> L6b
            java.lang.String r1 = r1.getStringOutputType()     // Catch: java.lang.Throwable -> L6b
            org.jasypt.digest.config.StringDigesterConfig r2 = r5.stringDigesterConfig     // Catch: java.lang.Throwable -> L6b
            java.lang.String r2 = r2.getPrefix()     // Catch: java.lang.Throwable -> L6b
            org.jasypt.digest.config.StringDigesterConfig r3 = r5.stringDigesterConfig     // Catch: java.lang.Throwable -> L6b
            java.lang.String r3 = r3.getSuffix()     // Catch: java.lang.Throwable -> L6b
            boolean r4 = r5.unicodeNormalizationIgnoredSet     // Catch: java.lang.Throwable -> L6b
            if (r4 != 0) goto L2f
            if (r0 != 0) goto L2a
            goto L2f
        L2a:
            boolean r4 = r0.booleanValue()     // Catch: java.lang.Throwable -> L6b
            goto L31
        L2f:
            boolean r4 = r5.unicodeNormalizationIgnored     // Catch: java.lang.Throwable -> L6b
        L31:
            r5.unicodeNormalizationIgnored = r4     // Catch: java.lang.Throwable -> L6b
            boolean r4 = r5.stringOutputTypeSet     // Catch: java.lang.Throwable -> L6b
            if (r4 != 0) goto L3c
            if (r1 != 0) goto L3a
            goto L3c
        L3a:
            r4 = r1
            goto L3e
        L3c:
            java.lang.String r4 = r5.stringOutputType     // Catch: java.lang.Throwable -> L6b
        L3e:
            r5.stringOutputType = r4     // Catch: java.lang.Throwable -> L6b
            boolean r4 = r5.prefixSet     // Catch: java.lang.Throwable -> L6b
            if (r4 != 0) goto L49
            if (r2 != 0) goto L47
            goto L49
        L47:
            r4 = r2
            goto L4b
        L49:
            java.lang.String r4 = r5.prefix     // Catch: java.lang.Throwable -> L6b
        L4b:
            r5.prefix = r4     // Catch: java.lang.Throwable -> L6b
            boolean r4 = r5.suffixSet     // Catch: java.lang.Throwable -> L6b
            if (r4 != 0) goto L56
            if (r3 != 0) goto L54
            goto L56
        L54:
            r4 = r3
            goto L58
        L56:
            java.lang.String r4 = r5.suffix     // Catch: java.lang.Throwable -> L6b
        L58:
            r5.suffix = r4     // Catch: java.lang.Throwable -> L6b
        L5a:
            java.lang.String r0 = "base64"
            java.lang.String r1 = r5.stringOutputType     // Catch: java.lang.Throwable -> L6b
            boolean r0 = r0.equalsIgnoreCase(r1)     // Catch: java.lang.Throwable -> L6b
            r5.stringOutputTypeBase64 = r0     // Catch: java.lang.Throwable -> L6b
            org.jasypt.digest.StandardByteDigester r0 = r5.byteDigester     // Catch: java.lang.Throwable -> L6b
            r0.initialize()     // Catch: java.lang.Throwable -> L6b
        L69:
            monitor-exit(r5)
            return
        L6b:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jasypt.digest.StandardStringDigester.initialize():void");
    }

    @Override // org.jasypt.digest.StringDigester
    public String digest(String message) {
        String normalizedMessage;
        if (message == null) {
            return null;
        }
        if (!isInitialized()) {
            initialize();
        }
        try {
            if (!this.unicodeNormalizationIgnored) {
                normalizedMessage = Normalizer.normalizeToNfc(message);
            } else {
                normalizedMessage = message;
            }
            byte[] messageBytes = normalizedMessage.getBytes(MESSAGE_CHARSET);
            byte[] digest = this.byteDigester.digest(messageBytes);
            StringBuffer result = new StringBuffer();
            if (this.prefix != null) {
                result.append(this.prefix);
            }
            if (this.stringOutputTypeBase64) {
                result.append(new String(this.base64.encode(digest), DIGEST_CHARSET));
            } else {
                result.append(CommonUtils.toHexadecimal(digest));
            }
            if (this.suffix != null) {
                result.append(this.suffix);
            }
            return result.toString();
        } catch (EncryptionInitializationException e) {
            throw e;
        } catch (EncryptionOperationNotPossibleException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new EncryptionOperationNotPossibleException();
        }
    }

    @Override // org.jasypt.digest.StringDigester
    public boolean matches(String message, String digest) {
        String normalizedMessage;
        byte[] digestBytes;
        String processedDigest = digest;
        if (processedDigest != null) {
            if (this.prefix != null) {
                if (!processedDigest.startsWith(this.prefix)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Digest does not start with required prefix \"");
                    stringBuffer.append(this.prefix);
                    stringBuffer.append("\"");
                    throw new EncryptionOperationNotPossibleException(stringBuffer.toString());
                }
                processedDigest = processedDigest.substring(this.prefix.length());
            }
            if (this.suffix != null) {
                if (processedDigest.endsWith(this.suffix)) {
                    processedDigest = processedDigest.substring(0, processedDigest.length() - this.suffix.length());
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Digest does not end with required suffix \"");
                    stringBuffer2.append(this.suffix);
                    stringBuffer2.append("\"");
                    throw new EncryptionOperationNotPossibleException(stringBuffer2.toString());
                }
            }
        }
        if (message == null) {
            return processedDigest == null;
        }
        if (processedDigest == null) {
            return false;
        }
        if (!isInitialized()) {
            initialize();
        }
        try {
            if (!this.unicodeNormalizationIgnored) {
                normalizedMessage = Normalizer.normalizeToNfc(message);
            } else {
                normalizedMessage = message;
            }
            byte[] messageBytes = normalizedMessage.getBytes(MESSAGE_CHARSET);
            if (this.stringOutputTypeBase64) {
                byte[] digestBytes2 = processedDigest.getBytes(DIGEST_CHARSET);
                digestBytes = this.base64.decode(digestBytes2);
            } else {
                digestBytes = CommonUtils.fromHexadecimal(processedDigest);
            }
            return this.byteDigester.matches(messageBytes, digestBytes);
        } catch (EncryptionInitializationException e) {
            throw e;
        } catch (EncryptionOperationNotPossibleException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new EncryptionOperationNotPossibleException();
        }
    }
}
