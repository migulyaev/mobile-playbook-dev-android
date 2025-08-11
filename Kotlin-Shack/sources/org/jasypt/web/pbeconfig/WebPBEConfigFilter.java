package org.jasypt.web.pbeconfig;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/* loaded from: classes.dex */
public final class WebPBEConfigFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        WebPBEConfigRegistry registry = WebPBEConfigRegistry.getInstance();
        if (registry.isWebConfigurationDone()) {
            chain.doFilter(request, response);
            return;
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.write(WebPBEConfigHtmlUtils.createNotInitializedHtml());
        printWriter.flush();
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }
}
