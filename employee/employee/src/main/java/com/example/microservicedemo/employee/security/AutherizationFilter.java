package com.example.microservicedemo.employee.security;

import com.example.microservicedemo.employee.controller.AuthConstant;
import com.example.microservicedemo.employee.controller.EmpController;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

public class AutherizationFilter extends BasicAuthenticationFilter {
    @Autowired
    public AutherizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Logger logger=LoggerFactory.getLogger(EmpController.class);
        logger.info("inside emp class do filter method");
        String auth=request.getHeader(AuthConstant.HEADER_STRING);
       if(auth == null || !auth.startsWith(AuthConstant.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
       }
        UsernamePasswordAuthenticationToken authenticationToken=getAuthenticationToken(request) ;
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        Logger logger=LoggerFactory.getLogger(EmpController.class);
        logger.info("inside emp class getAuthtoken method");
        String authToken=   request.getHeader(AuthConstant.HEADER_STRING);
          if(authToken == null){
              return null;
          }
          String token  = authToken.replace(AuthConstant.TOKEN_PREFIX,"");
          byte[] secretKeyByte= Base64.getEncoder().encode(AuthConstant.TOKEN_SECRET.getBytes());
        SecretKey secretKey=new SecretKeySpec(secretKeyByte, SignatureAlgorithm.HS512.getJcaName());
        JwtParser jwtParser= Jwts.parserBuilder().setSigningKey(secretKey).build();
          Jwt<Header,Claims> ass=jwtParser.parse(token);
          String subject=ass.getBody().getSubject();
           return UsernamePasswordAuthenticationToken.authenticated(subject,null,new ArrayList<>());
    }
}
