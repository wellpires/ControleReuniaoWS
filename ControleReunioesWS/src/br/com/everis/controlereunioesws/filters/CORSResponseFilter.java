package br.com.everis.controlereunioesws.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wellington Gon�alves Pires
 */
@WebFilter("/*")
public class CORSResponseFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, PUT, DELETE, HEAD, OPTIONS, POST, PATCH");
        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
    
}
