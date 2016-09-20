package helper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String header = httpServletRequest.getHeader("X-AppEngine-Inbound-AppId");

        if(header != null){

            filterChain.doFilter(request, httpServletResponse);

        }else{

            log.warning("The current request has not been initiated from App Engine instance. Access denied");
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }


    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {}

}