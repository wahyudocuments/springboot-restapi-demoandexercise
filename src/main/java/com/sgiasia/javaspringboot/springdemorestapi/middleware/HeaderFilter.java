package com.sgiasia.javaspringboot.springdemorestapi.middleware;

import com.sgiasia.javaspringboot.springdemorestapi.helpers.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class HeaderFilter extends GenericFilterBean {
    Logger logger = LoggerFactory.getLogger(HeaderFilter.class);
    private JwtHelper jwtHelper = new JwtHelper();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        //if header is missing, send un_authorized error back
        String authHeader = request.getHeader("x-api-key");
        if (StringUtils.isEmpty(authHeader)){
            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );
        }else {
            String userName = jwtHelper.getUsernameFromToken(authHeader);
            if(userName.equals("user")){
                chain.doFilter(req,res);
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        logger.info("Header filtered");
    }
}
