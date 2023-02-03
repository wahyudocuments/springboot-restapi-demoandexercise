package com.sgiasia.javaspringboot.springdemorestapi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .httpBasic()
                .and()
                .authorizeHttpRequests((request) -> request
                        .antMatchers("/").permitAll()
                        .antMatchers("/**").authenticated()
                )
                .csrf().disable()
                .formLogin().disable()
                .logout();

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user =
                User.withUsername("user")
                        .password("{noop}password")
                        .roles("USER")
                        .build();

        UserDetails admin =
                User.withUsername("admin")
                        .password("{noop}password")
                        .roles("ADMIN")
                        .build();

        UserDetails wahyu =
                User.withUsername("wahyu")
                        .password("{noop}wah")
                        .roles("WAHYU")
                        .build();

        return new InMemoryUserDetailsManager(user, admin,wahyu);
    }
}
