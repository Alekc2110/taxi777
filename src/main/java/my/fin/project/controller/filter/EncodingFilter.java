package my.fin.project.controller.filter;

import org.apache.log4j.Logger;
import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(EncodingFilter.class);
    private static final String CONTENT_TYPE = "text/html; charset=";

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) {
        LOG.debug("Filter initialization starts");
        encoding = filterConfig.getInitParameter("encoding");
        LOG.trace("Encoding from web.xml --> " + encoding);
        LOG.debug("Filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.debug("Filter starts");

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            LOG.trace("Request encoding = null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
            response.setContentType(CONTENT_TYPE + encoding);
        }
        LOG.debug("Filter finished");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOG.debug("Filter destruction starts");
        // do nothing
        LOG.debug("Filter destruction finished");
    }
}
