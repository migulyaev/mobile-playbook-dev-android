package org.jasypt.encryption.pbe.config;

import org.jasypt.commons.CommonUtils;

/* loaded from: classes.dex */
public class WebStringPBEConfig extends WebPBEConfig implements StringPBEConfig {
    private String stringOutputType = null;

    public void setStringOutputType(String stringOutputType) {
        this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
    }

    @Override // org.jasypt.encryption.pbe.config.StringPBEConfig
    public String getStringOutputType() {
        return this.stringOutputType;
    }
}
