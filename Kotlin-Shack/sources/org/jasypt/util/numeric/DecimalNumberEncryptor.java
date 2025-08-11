package org.jasypt.util.numeric;

import java.math.BigDecimal;

/* loaded from: classes.dex */
public interface DecimalNumberEncryptor {
    BigDecimal decrypt(BigDecimal bigDecimal);

    BigDecimal encrypt(BigDecimal bigDecimal);
}
