/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg.filter;

import apg.util.SessionUtils;
import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author daseel
 */
@WebFilter(filterName = "UserFilter", urlPatterns = {"/account.xhtml", "/item.xhtml", "/cart.xhtml"}, dispatcherTypes = DispatcherType.REQUEST)
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter time");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        
        boolean loggedIn = (session != null) ? (session.getAttribute(SessionUtils.USERNAME) != null && !session.getAttribute(SessionUtils.USERNAME).equals("")) : false;
        String loginURL = request.getContextPath() + "/login.xhtml";

        if (!loggedIn && !request.getRequestURI().equals(loginURL)) {
            response.sendRedirect(loginURL);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("Filter destoryed!");
    }

}
