package org.jasypt.web.pbeconfig;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jasypt.commons.CommonUtils;
import org.jasypt.encryption.pbe.config.WebPBEConfig;

/* loaded from: classes.dex */
public final class WebPBEConfigServlet extends HttpServlet {
    private static final long serialVersionUID = -7201635392816652667L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            WebPBEConfigRegistry registry = WebPBEConfigRegistry.getInstance();
            if (registry.isWebConfigurationDone()) {
                writeResponse(WebPBEConfigHtmlUtils.createConfigurationDoneHtml(), resp);
                return;
            }
            String settingFlag = req.getParameter(WebPBEConfigHtmlUtils.PASSWORD_SETTING_FLAG);
            int valid = 0;
            if (CommonUtils.isEmpty(settingFlag)) {
                writeResponse(WebPBEConfigHtmlUtils.createInputFormHtml(req, false), resp);
                return;
            }
            List<WebPBEConfig> configs = registry.getConfigs();
            int i = 0;
            for (WebPBEConfig config : configs) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(WebPBEConfigHtmlUtils.VALIDATION_PREFIX);
                stringBuffer.append(i);
                String validation = req.getParameter(stringBuffer.toString());
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(WebPBEConfigHtmlUtils.PASSWORD_PREFIX);
                stringBuffer2.append(i);
                String password = req.getParameter(stringBuffer2.toString());
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append(WebPBEConfigHtmlUtils.PASSWORD_RETYPED_PREFIX);
                stringBuffer3.append(i);
                String retypedPassword = req.getParameter(stringBuffer3.toString());
                if (!CommonUtils.isEmpty(validation) && !CommonUtils.isEmpty(password) && password.equals(retypedPassword) && config.getValidationWord().equals(validation)) {
                    valid++;
                }
                i++;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            Calendar now = Calendar.getInstance();
            if (valid < configs.size()) {
                ServletContext servletContext = getServletContext();
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("Failed attempt to set PBE Configuration from ");
                stringBuffer4.append(req.getRemoteAddr());
                stringBuffer4.append(" [");
                stringBuffer4.append(dateFormat.format(now.getTime()));
                stringBuffer4.append("]");
                servletContext.log(stringBuffer4.toString());
                writeResponse(WebPBEConfigHtmlUtils.createInputFormHtml(req, true), resp);
                return;
            }
            int i2 = 0;
            for (WebPBEConfig config2 : configs) {
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append(WebPBEConfigHtmlUtils.PASSWORD_PREFIX);
                stringBuffer5.append(i2);
                config2.setPassword(req.getParameter(stringBuffer5.toString()));
                i2++;
            }
            registry.setWebConfigurationDone(true);
            ServletContext servletContext2 = getServletContext();
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append("PBE Configuration succesfully set from ");
            stringBuffer6.append(req.getRemoteAddr());
            stringBuffer6.append(" [");
            stringBuffer6.append(dateFormat.format(now.getTime()));
            stringBuffer6.append("]");
            servletContext2.log(stringBuffer6.toString());
            writeResponse(WebPBEConfigHtmlUtils.createConfigurationDoneHtml(), resp);
        } catch (IOException e) {
            getServletContext().log("Exception raised during servlet execution", e);
            throw e;
        } catch (Throwable t) {
            getServletContext().log("Exception raised during servlet execution", t);
            throw new ServletException(t);
        }
    }

    private void writeResponse(String html, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.write(html);
        printWriter.flush();
    }
}
