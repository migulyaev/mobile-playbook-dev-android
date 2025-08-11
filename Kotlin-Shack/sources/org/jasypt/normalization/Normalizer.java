package org.jasypt.normalization;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.jasypt.exceptions.EncryptionInitializationException;

/* loaded from: classes.dex */
public final class Normalizer {
    private static final String ICU_NORMALIZER_CLASS_NAME = "com.ibm.icu.text.Normalizer";
    private static final String JDK_NORMALIZER_CLASS_NAME = "java.text.Normalizer";
    private static final String JDK_NORMALIZER_FORM_CLASS_NAME = "java.text.Normalizer$Form";
    static /* synthetic */ Class class$java$lang$CharSequence;
    private static Boolean useIcuNormalizer = null;
    private static Method javaTextNormalizerMethod = null;
    private static Object javaTextNormalizerFormNFCConstant = null;

    public static String normalizeToNfc(String message) {
        return new String(normalizeToNfc(message.toCharArray()));
    }

    public static char[] normalizeToNfc(char[] message) {
        if (useIcuNormalizer == null) {
            try {
                initializeIcu4j();
            } catch (ClassNotFoundException e) {
                try {
                    initializeJavaTextNormalizer();
                } catch (ClassNotFoundException e2) {
                    throw new EncryptionInitializationException("Cannot find a valid UNICODE normalizer: neither java.text.Normalizer nor com.ibm.icu.text.Normalizer have been found at the classpath. If you are using a version of the JDK older than JavaSE 6, you should include the icu4j library in your classpath.");
                } catch (IllegalAccessException e3) {
                    throw new EncryptionInitializationException("Cannot find a valid UNICODE normalizer: java.text.Normalizer$Form has been found at the classpath, but seems to have no 'NFC' value.");
                } catch (NoSuchFieldException e4) {
                    throw new EncryptionInitializationException("Cannot find a valid UNICODE normalizer: java.text.Normalizer$Form has been found at the classpath, but seems to have no 'NFC' value.");
                } catch (NoSuchMethodException e5) {
                    throw new EncryptionInitializationException("Cannot find a valid UNICODE normalizer: java.text.Normalizer has been found at the classpath, but has an incompatible signature for its 'normalize' method.");
                }
            }
        }
        if (useIcuNormalizer.booleanValue()) {
            return normalizeWithIcu4j(message);
        }
        return normalizeWithJavaNormalizer(message);
    }

    static void initializeIcu4j() throws ClassNotFoundException {
        Thread.currentThread().getContextClassLoader().loadClass(ICU_NORMALIZER_CLASS_NAME);
        useIcuNormalizer = Boolean.TRUE;
    }

    static void initializeJavaTextNormalizer() throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        Class<?> cls;
        Class javaTextNormalizerClass = Thread.currentThread().getContextClassLoader().loadClass(JDK_NORMALIZER_CLASS_NAME);
        Class javaTextNormalizerFormClass = Thread.currentThread().getContextClassLoader().loadClass(JDK_NORMALIZER_FORM_CLASS_NAME);
        Class<?>[] clsArr = new Class[2];
        if (class$java$lang$CharSequence == null) {
            cls = class$("java.lang.CharSequence");
            class$java$lang$CharSequence = cls;
        } else {
            cls = class$java$lang$CharSequence;
        }
        clsArr[0] = cls;
        clsArr[1] = javaTextNormalizerFormClass;
        javaTextNormalizerMethod = javaTextNormalizerClass.getMethod("normalize", clsArr);
        Field javaTextNormalizerFormNFCConstantField = javaTextNormalizerFormClass.getField("NFC");
        javaTextNormalizerFormNFCConstant = javaTextNormalizerFormNFCConstantField.get(null);
        useIcuNormalizer = Boolean.FALSE;
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError().initCause(x1);
        }
    }

    static char[] normalizeWithJavaNormalizer(char[] message) {
        if (javaTextNormalizerMethod == null || javaTextNormalizerFormNFCConstant == null) {
            throw new EncryptionInitializationException("Cannot use: java.text.Normalizer$Form, as JDK-based normalization has not been initialized! (check previous execution errors)");
        }
        String messageStr = new String(message);
        try {
            String result = (String) javaTextNormalizerMethod.invoke(null, messageStr, javaTextNormalizerFormNFCConstant);
            return result.toCharArray();
        } catch (Exception e) {
            throw new EncryptionInitializationException("Could not perform a valid UNICODE normalization", e);
        }
    }

    static char[] normalizeWithIcu4j(char[] message) {
        int normalizationResultSize;
        char[] normalizationResult = new char[message.length * 2];
        char[] normalizationResult2 = normalizationResult;
        while (true) {
            normalizationResultSize = com.ibm.icu.text.Normalizer.normalize(message, normalizationResult2, com.ibm.icu.text.Normalizer.NFC, 0);
            if (normalizationResultSize <= normalizationResult2.length) {
                break;
            }
            for (int i = 0; i < normalizationResult2.length; i++) {
                normalizationResult2[i] = 0;
            }
            normalizationResult2 = new char[normalizationResultSize];
        }
        char[] result = new char[normalizationResultSize];
        System.arraycopy(normalizationResult2, 0, result, 0, normalizationResultSize);
        for (int i2 = 0; i2 < normalizationResult2.length; i2++) {
            normalizationResult2[i2] = 0;
        }
        return result;
    }

    private Normalizer() {
    }
}
