package com.example._20220521springsecuritytest1;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@EnableWebSecurity
public class SecurConfig extends WebSecurityConfigurerAdapter  {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/h").authenticated()
            .and()
            .formLogin().loginPage("loginPage").and().logout().logoutSuccessUrl("/");
    }
    }
