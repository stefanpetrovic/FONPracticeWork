/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filter;

import beans.LoggedInUserBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stefan
 */
public class SessionFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String loginURL = ((HttpServletRequest)request).getContextPath()+ "/index.jsf";
        LoggedInUserBean liub = (LoggedInUserBean) ((HttpServletRequest) request).getSession().getAttribute("loggedInUserBean");
        if (liub == null || liub.getLoggedInPerson().isEmpty()) {
           ((HttpServletResponse) response).sendRedirect(loginURL);
           return;
        }else {
            chain.doFilter(request, response);
            return;
        }
    }

    @Override
    public void destroy() {
        
    }
    
}
