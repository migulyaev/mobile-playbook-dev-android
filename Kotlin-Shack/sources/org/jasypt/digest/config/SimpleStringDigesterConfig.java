package org.jasypt.digest.config;

import org.jasypt.commons.CommonUtils;

/* loaded from: classes.dex */
public class SimpleStringDigesterConfig extends SimpleDigesterConfig implements StringDigesterConfig {
    private Boolean unicodeNormalizationIgnored = null;
    private String stringOutputType = null;
    private String prefix = null;
    private String suffix = null;

    public void setUnicodeNormalizationIgnored(Boolean unicodeNormalizationIgnored) {
        this.unicodeNormalizationIgnored = unicodeNormalizationIgnored;
    }

    public void setUnicodeNormalizationIgnored(String unicodeNormalizationIgnored) {
        if (unicodeNormalizationIgnored != null) {
            this.unicodeNormalizationIgnored = CommonUtils.getStandardBooleanValue(unicodeNormalizationIgnored);
        } else {
            this.unicodeNormalizationIgnored = null;
        }
    }

    public void setStringOutputType(String stringOutputType) {
        this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override // org.jasypt.digest.config.StringDigesterConfig
    public Boolean isUnicodeNormalizationIgnored() {
        return this.unicodeNormalizationIgnored;
    }

    @Override // org.jasypt.digest.config.StringDigesterConfig
    public String getStringOutputType() {
        return this.stringOutputType;
    }

    @Override // org.jasypt.digest.config.StringDigesterConfig
    public String getPrefix() {
        return this.prefix;
    }

    @Override // org.jasypt.digest.config.StringDigesterConfig
    public String getSuffix() {
        return this.suffix;
    }
}
