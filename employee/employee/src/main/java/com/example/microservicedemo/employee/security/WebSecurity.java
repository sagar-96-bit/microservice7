package com.example.microservicedemo.employee.security;

import com.example.microservicedemo.employee.service.EmpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmpService empService;

    public WebSecurity(BCryptPasswordEncoder bCryptPasswordEncoder, EmpService empService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.empService = empService;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
         authenticationManagerBuilder.userDetailsService(empService).passwordEncoder(bCryptPasswordEncoder);
            AuthenticationManager authenticationManager= authenticationManagerBuilder.build();
            AuthenticationFilter authenticationFilter=new AuthenticationFilter(authenticationManager);
            authenticationFilter.setFilterProcessesUrl("/employee/login");
    httpSecurity.csrf().disable().authorizeRequests().requestMatchers(HttpMethod.POST,"/employee").permitAll()
            .anyRequest().authenticated().and().authenticationManager(authenticationManager)
            .addFilter(authenticationFilter)
            .addFilter(new AutherizationFilter(authenticationManager))
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);



    return httpSecurity.build();
    }
}
