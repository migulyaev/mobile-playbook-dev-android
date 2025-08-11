package org.jasypt.web.pbeconfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.jasypt.commons.CommonUtils;
import org.jasypt.exceptions.EncryptionInitializationException;

/* loaded from: classes.dex */
public final class WebPBEInitializationContextListener implements ServletContextListener {
    public static final String INIT_PARAM_INITIALIZER_CLASS_NAME = "webPBEInitializerClassName";
    static /* synthetic */ Class class$org$jasypt$web$pbeconfig$WebPBEInitializer;

    public void contextDestroyed(ServletContextEvent sce) {
    }

    public void contextInitialized(ServletContextEvent sce) {
        Class cls;
        Class cls2;
        String className = sce.getServletContext().getInitParameter(INIT_PARAM_INITIALIZER_CLASS_NAME);
        if (CommonUtils.isEmpty(className)) {
            throw new EncryptionInitializationException("webPBEInitializerClassName context initialization parameter not set in web.xml");
        }
        try {
            Class initializerClass = Thread.currentThread().getContextClassLoader().loadClass(className);
            if (class$org$jasypt$web$pbeconfig$WebPBEInitializer == null) {
                cls = class$("org.jasypt.web.pbeconfig.WebPBEInitializer");
                class$org$jasypt$web$pbeconfig$WebPBEInitializer = cls;
            } else {
                cls = class$org$jasypt$web$pbeconfig$WebPBEInitializer;
            }
            if (!cls.isAssignableFrom(initializerClass)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Class ");
                stringBuffer.append(className);
                stringBuffer.append(" does not implement interface ");
                if (class$org$jasypt$web$pbeconfig$WebPBEInitializer == null) {
                    cls2 = class$("org.jasypt.web.pbeconfig.WebPBEInitializer");
                    class$org$jasypt$web$pbeconfig$WebPBEInitializer = cls2;
                } else {
                    cls2 = class$org$jasypt$web$pbeconfig$WebPBEInitializer;
                }
                stringBuffer.append(cls2.getName());
                throw new EncryptionInitializationException(stringBuffer.toString());
            }
            try {
                WebPBEInitializer initializer = (WebPBEInitializer) initializerClass.newInstance();
                initializer.initializeWebPBEConfigs();
            } catch (IllegalAccessException e) {
                throw new EncryptionInitializationException(e);
            } catch (InstantiationException e2) {
                throw new EncryptionInitializationException(e2);
            }
        } catch (ClassNotFoundException e3) {
            throw new EncryptionInitializationException(e3);
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError().initCause(x1);
        }
    }
}
