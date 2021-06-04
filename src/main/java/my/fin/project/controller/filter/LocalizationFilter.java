package my.fin.project.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalizationFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(LocalizationFilter.class);
    private static final String LOCALE = "locale";
    private static final String BUNDLE = "bundle";
    private String defaultBundle;
    private String locale;

    @Override
    public void init(FilterConfig filterConfig) {
        defaultBundle = filterConfig.getInitParameter(BUNDLE);
        locale = filterConfig.getInitParameter(LOCALE);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String localeParameter = request.getParameter(LOCALE);
        locale = setLocale(httpRequest, localeParameter);
        httpRequest.getSession().setAttribute(LOCALE, locale);
        httpRequest.getSession().setAttribute(BUNDLE, defaultBundle);
        LOG.info("set locale in Filter" + locale);
        chain.doFilter(request, response);
    }

    private String setLocale(HttpServletRequest httpRequest, String localeParameter) {
        if (localeParameter != null) {
            return localeParameter;
        }
        String sessionLocale = (String) httpRequest.getSession().getAttribute(LOCALE);
        if (sessionLocale != null) {
            return sessionLocale;
        }
        return this.locale;
    }

    @Override
    public void destroy() {
    }
}
