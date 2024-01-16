package com.example.microservicedemo.gatewayservice.filter;


import io.jsonwebtoken.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Base64;

@Component
public class AutherizationFilter extends AbstractGatewayFilterFactory<AutherizationFilter.Config> {
     Logger logger= LoggerFactory.getLogger(AutherizationFilter.class);
    @Autowired
    ValidateUrl validateUrl;
    public AutherizationFilter(){
                       super(Config.class);
       }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//      String auth=request.getHeader(AuthConstant.HEADER_STRING);
//       if(auth == null || !auth.startsWith(AuthConstant.TOKEN_PREFIX)){
//            chain.doFilter(request,response);
//            return;
//       }
//        UsernamePasswordAuthenticationToken authenticationToken=getAuthenticationToken(request) ;
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        chain.doFilter(request,response);
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
//
//          String authToken=   request.getHeader(AuthConstant.HEADER_STRING);
//          if(authToken == null){
//              return null;
//          }
//          String token  = authToken.replace(AuthConstant.TOKEN_PREFIX,"");
//          byte[] secretKeyByte= Base64.getEncoder().encode(AuthConstant.TOKEN_SECRET.getBytes());
//        SecretKey secretKey=new SecretKeySpec(secretKeyByte, SignatureAlgorithm.HS512.getJcaName());
//        JwtParser jwtParser= Jwts.parserBuilder().setSigningKey(secretKey).build();
//          Jwt<Header, Claims> ass=jwtParser.parse(token);
//          String subject=ass.getBody().getSubject();
//           return UsernamePasswordAuthenticationToken.authenticated(subject,null,new ArrayList<>());
//    }

    @Override
    public GatewayFilter apply(AutherizationFilter.Config config) {
        return (((exchange, chain) -> {
            Logger logger=LoggerFactory.getLogger(AutherizationFilter.class);
            logger.info("inside api class apply method");
                     if(validateUrl.isSecured.test(exchange.getRequest())){
                         if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                             logger.info("not autherized");
                             throw new RuntimeException("not Authrized");
                         }
                         logger.info("inside apigateway apply method");
                         UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=doAutherization(exchange);
                     }

            return chain.filter(exchange);
        }));
    }

    private UsernamePasswordAuthenticationToken doAutherization(ServerWebExchange exchange) {

               logger.info("inside api doautherization method");
        String auth= exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
        if(auth != null && auth.startsWith(AuthConstant.TOKEN_PREFIX)){
            auth = auth.replace(AuthConstant.TOKEN_PREFIX,"");
            byte[] secretKeyByte= Base64.getEncoder().encode(AuthConstant.TOKEN_SECRET.getBytes());
        SecretKey secretKey=new SecretKeySpec(secretKeyByte, SignatureAlgorithm.HS512.getJcaName());
        JwtParser jwtParser= Jwts.parserBuilder().setSigningKey(secretKey).build();
          Jwt<Header, Claims> ass=jwtParser.parse(auth);
          String subject=ass.getBody().getSubject();
            logger.info("inside api subject method");

            return UsernamePasswordAuthenticationToken.authenticated(subject,null,new ArrayList<>());

        }
        logger.info("inside api doautherization method returnning null");
        return null;
    }


    public static class Config {
    }
}
