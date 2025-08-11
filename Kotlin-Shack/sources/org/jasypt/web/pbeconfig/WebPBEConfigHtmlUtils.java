package org.jasypt.web.pbeconfig;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jasypt.encryption.pbe.config.WebPBEConfig;
import org.jasypt.exceptions.EncryptionInitializationException;

/* loaded from: classes.dex */
final class WebPBEConfigHtmlUtils {
    private static final String HTTPS_SCHEME = "https";
    public static final String PASSWORD_PREFIX = "jasyptPw";
    public static final String PASSWORD_RETYPED_PREFIX = "jasyptRPw";
    public static final String PASSWORD_SETTING_FLAG = "jasyptPwSetting";
    public static final String VALIDATION_PREFIX = "jasyptVa";

    private WebPBEConfigHtmlUtils() {
    }

    public static String createConfigurationDoneHtml() {
        StringBuffer strBuff = new StringBuffer();
        addHeader(strBuff);
        strBuff.append("   <h2>All Configuration Done</h2>\n");
        addFoot(strBuff);
        return strBuff.toString();
    }

    public static String createInputFormHtml(HttpServletRequest request, boolean inputError) {
        WebPBEConfigRegistry registry = WebPBEConfigRegistry.getInstance();
        List<WebPBEConfig> configs = registry.getConfigs();
        StringBuffer strBuff = new StringBuffer();
        addHeader(strBuff);
        strBuff.append("   <h2>Please enter the PBE configuration parameters</h2>\n");
        if (!HTTPS_SCHEME.equals(request.getScheme().toLowerCase())) {
            strBuff.append("   <div class=\"warning\">WARNING: NOT IN SECURE MODE (HTTPS)</div>\n");
        }
        if (inputError) {
            strBuff.append("   <div class=\"warning\">Validation error!</div>\n");
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("   <form action=\"");
        stringBuffer.append(request.getRequestURI());
        stringBuffer.append("\" method=\"POST\">\n");
        strBuff.append(stringBuffer.toString());
        strBuff.append("    <div>\n");
        int i = 0;
        for (WebPBEConfig config : configs) {
            if (!config.isComplete()) {
                throw new EncryptionInitializationException("Incomplete WebPBEConfig object: all configs must specify both a name and a validation word");
            }
            strBuff.append("     <fieldset>\n");
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("      <legend>");
            stringBuffer2.append(config.getName());
            stringBuffer2.append("</legend>\n");
            strBuff.append(stringBuffer2.toString());
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("      <label for=\"jasyptVa");
            stringBuffer3.append(i);
            stringBuffer3.append("\">Validation word</label>: <input type=\"password\" name=\"");
            stringBuffer3.append(VALIDATION_PREFIX);
            stringBuffer3.append(i);
            stringBuffer3.append("\" />\n");
            strBuff.append(stringBuffer3.toString());
            strBuff.append("      <br /><br />\n");
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("      <label for=\"jasyptPw");
            stringBuffer4.append(i);
            stringBuffer4.append("\">Password</label>: <input type=\"password\" name=\"");
            stringBuffer4.append(PASSWORD_PREFIX);
            stringBuffer4.append(i);
            stringBuffer4.append("\" />\n");
            strBuff.append(stringBuffer4.toString());
            strBuff.append("      <br /><br />\n");
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("      <label for=\"jasyptRPw");
            stringBuffer5.append(i);
            stringBuffer5.append("\">Retype password</label>: <input type=\"password\" name=\"");
            stringBuffer5.append(PASSWORD_RETYPED_PREFIX);
            stringBuffer5.append(i);
            stringBuffer5.append("\" />\n");
            strBuff.append(stringBuffer5.toString());
            strBuff.append("     </fieldset>\n");
            i++;
        }
        strBuff.append("    </div>\n");
        strBuff.append("    <div id=\"button\">\n");
        strBuff.append("     <input type=\"hidden\" name=\"jasyptPwSetting\" value=\"true\" />\n");
        strBuff.append("     <input type=\"submit\" value=\"Submit\" />\n");
        strBuff.append("    </div>\n");
        strBuff.append("   </form>\n");
        addFoot(strBuff);
        return strBuff.toString();
    }

    public static String createNotInitializedHtml() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("<html>\n");
        strBuff.append(" <head>\n");
        strBuff.append("  <title>Forbidden</title>\n");
        strBuff.append(" </head>\n");
        strBuff.append(" <body>\n");
        strBuff.append("   <h1>Access Forbidden</h1>\n");
        strBuff.append(" </body>\n");
        strBuff.append("</html>\n");
        return strBuff.toString();
    }

    private static void addHeader(StringBuffer strBuff) {
        strBuff.append("<html>\n");
        strBuff.append(" <head>\n");
        strBuff.append("  <title>Web Password Based Encryption Configuration</title>\n");
        strBuff.append("  <style type=\"text/css\">");
        strBuff.append("   html { background-color: #ccc; text-align: center; margin: 0px; padding: 0px;} body {text-align:center;} #page { width: 700px; background-color: white; margin-top: 10px; margin-left: auto; margin-right: auto; padding: 10px; border: 1px solid #000; text-align: left;} h1 { text-weight: bold;} #button { text-align: center; margin-top: 20px; } fieldset { margin-bottom: 20px; } label { font-style: italic; } legend { font-weight: bold; } div.warning { border: 1px dotted #000; margin: 15px; padding: 5px; background-color: eee; font-weight: bold; }");
        strBuff.append("  </style>\n");
        strBuff.append(" </head>\n");
        strBuff.append(" <body>\n");
        strBuff.append("  <div id=\"page\">\n");
        strBuff.append("   <h1>Web PBE Configuration</h1>\n");
    }

    private static void addFoot(StringBuffer strBuff) {
        strBuff.append("  </div>\n");
        strBuff.append(" </body>\n");
        strBuff.append("</html>\n");
    }
}
