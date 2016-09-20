package helper;

import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * Created by Jean-Baptiste Clion on 01.06.2016.
 * Filter managing proxy authentication to the application proxy.
 *
 * @author Jean-Baptiste Clion - jbclion@gmail.com
 * @version 0.1
 */
public class AuthenticationFilter implements Filter {

    private FilterConfig filterConfig;
    private static final Logger log = Logger.getLogger(AuthenticationFilter.class.getName());

    /**
     * Filter incoming requests by checking if the effective user is current authenticated
     * @param request HTTP request to filter
     * @param response Filtered HTTP response
     * @param filterChain Filter chain allowing to pass the request to requested servlets
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if(UserManager.getCurrentUser() == null){

            httpServletResponse.setContentType("text/plain");
            httpServletResponse.setStatus(403);
            PrintWriter out = httpServletResponse.getWriter();
            out.print("To authenticate, please visit: " + UserServiceFactory.getUserService().createLoginURL("https://microshop.appspot.com"));
            out.flush();

            return;
        }

        filterChain.doFilter(request, httpServletResponse);

    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {}

}