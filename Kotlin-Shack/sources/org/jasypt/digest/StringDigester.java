package org.jasypt.digest;

/* loaded from: classes.dex */
public interface StringDigester {
    String digest(String str);

    boolean matches(String str, String str2);
}
