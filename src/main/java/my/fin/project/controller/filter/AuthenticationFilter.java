package my.fin.project.controller.filter;

import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.Role;
import my.fin.project.utils.LoginUserUtils;
import my.fin.project.utils.security.SecurityUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static my.fin.project.controller.command.Path.*;

public class AuthenticationFilter implements Filter {
    private final Logger LOG = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String pathInfo = httpRequest.getPathInfo();
        String contextAndServletPath = httpRequest.getContextPath() + httpRequest.getServletPath();
        User loginedUser = LoginUserUtils.getLoginedUser(httpRequest.getSession());
        LOG.info("get all info in doFilter");
        if ((LOGIN.equals(pathInfo) || REGISTER.equals(pathInfo)) && loginedUser != null) {
            if (loginedUser.getRole().equals(Role.CLIENT)) {
                LOG.info("role = Client, return client account");
                httpResponse.sendRedirect(contextAndServletPath + CLIENT_ACCOUNT);

            }
            if (loginedUser.getRole().equals(Role.ADMIN)) {
                LOG.info("role = Admin, return admin account");
                httpResponse.sendRedirect(contextAndServletPath + ADMIN_ACCOUNT);

            } if (loginedUser.getRole().equals(Role.DRIVER)) {
                LOG.info("role = Driver, return driver account");
                httpResponse.sendRedirect(contextAndServletPath + DRIVER_ACCOUNT);
            }
            return;
        }
        LOG.info("check if page is secure");
        if (SecurityUtils.isSecurityPage(httpRequest)) {
            LOG.info("check if user has permission to access resource");
            if (loginedUser != null && SecurityUtils.hasPermission(httpRequest, loginedUser.getRole())) {
                LOG.info("person has access to this page");
                chain.doFilter(request, response);
            } else {
                if (MAKE_ORDER.equals(pathInfo)) {
                    LOG.info("redirect to login");
                    httpResponse.sendRedirect(contextAndServletPath + LOGIN);
                } else {
                    LOG.info("redirect to 403");
                    httpResponse.sendRedirect(contextAndServletPath + FORBIDDEN);
                }
            }
        } else {
            LOG.info("page is not secure, doFilter");
            chain.doFilter(request, response);
        }
    }


    @Override
    public void destroy() {
    }
}
