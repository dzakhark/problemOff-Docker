package com.hw.config;

import com.hw.util.security.StatelessAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private StatelessAuthFilter statelessAuthFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(statelessAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers("/readme.txt", "/css/*").permitAll()
                    .antMatchers("/api/register").permitAll()
                    .antMatchers("/api/login").permitAll()
                    .antMatchers("/api/admin/**").permitAll()
                    .antMatchers("/api/user/**").permitAll()
                    .antMatchers("/api/account/**").permitAll()
                .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .and()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .csrf()
                        .disable();
    }
}
