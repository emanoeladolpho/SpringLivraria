package com.emanoel.socialbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Esse método determina que tipo de método de autenticação vai ser usado
        // Nesse caso vai ser uma autenticação em memória
        auth.inMemoryAuthentication().withUser("emanoel")
                .password("teste").roles("USER");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll().anyRequest().authenticated().
                and()
                    .httpBasic()
                .and()
                    .csrf().disable();
    }

}
