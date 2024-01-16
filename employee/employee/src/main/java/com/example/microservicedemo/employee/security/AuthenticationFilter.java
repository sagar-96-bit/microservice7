package com.example.microservicedemo.employee.security;

import com.example.microservicedemo.employee.controller.AuthConstant;
import com.example.microservicedemo.employee.controller.SpringApplication;
import com.example.microservicedemo.employee.dto.EmpDto;
import com.example.microservicedemo.employee.request.model.UserLoginReq;
import com.example.microservicedemo.employee.service.EmpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLoginReq userLoginReq=new ObjectMapper().readValue(request.getInputStream(), UserLoginReq.class);
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(userLoginReq.getEmail(),userLoginReq.getPassword(),new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

         byte[] secretByte = Base64.getEncoder().encode(AuthConstant.TOKEN_SECRET.getBytes());
        SecretKey secretKey= new SecretKeySpec(secretByte, SignatureAlgorithm.HS512.getJcaName());
        Instant now=Instant.now();
         String username=((User)authResult.getPrincipal()).getUsername();
        String token= Jwts.builder().setSubject(username).setExpiration(Date.from(now.plusMillis(AuthConstant.TIME_EXPIRE)))
        .setIssuedAt(Date.from(now)).signWith(secretKey,SignatureAlgorithm.HS512).compact();

        EmpService empService=(EmpService)SpringApplication.getBean("empServiceImpl");
        EmpDto empDto=empService.getUser(username);
        response.addHeader(AuthConstant.HEADER_STRING,AuthConstant.TOKEN_PREFIX+token);
        response.addHeader("userId",empDto.getEmpId());
    }
}
