package com.brunopbrito31.financecontrolapi.configs;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
    // private static final String ENDERECO_FRONT = "http://localhost:3000";

    // Liberado para todas as origens
    private static final String FRONT_ADRESS_ALLOWED = "*";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.setHeader("Access-Control-Allow-Origin", FRONT_ADRESS_ALLOWED);
        resp.setHeader("Access-Control-Allow-Credentials","true");

        if("OPTIONS".equals(req.getMethod()) && isValidOrigin(req.getHeader("Origin"))){
            resp.setHeader("Access-Control-Allow-Methods","GET, POST, DELETE, PUT, OPTIONS");
            resp.setHeader("Access-Control-Allow-Headers","Authorization, Content-Type, Accept");
            resp.setHeader("Access-Control-Max-Age", "3600");
            resp.setStatus(HttpServletResponse.SC_OK);
        }else{
            chain.doFilter(request, response);
        }
    }

    public Boolean isValidOrigin(String originInReqHeader) {
        return (FRONT_ADRESS_ALLOWED == "*") ? true :
                FRONT_ADRESS_ALLOWED.equals(originInReqHeader);
    }
}
