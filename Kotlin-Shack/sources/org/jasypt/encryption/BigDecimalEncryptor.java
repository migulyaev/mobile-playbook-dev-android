package org.jasypt.encryption;

import java.math.BigDecimal;

/* loaded from: classes.dex */
public interface BigDecimalEncryptor {
    BigDecimal decrypt(BigDecimal bigDecimal);

    BigDecimal encrypt(BigDecimal bigDecimal);
}
