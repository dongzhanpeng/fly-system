package com.fly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 安全框架配置类
 * </p>
 *
 * @author Fly
 * @since 2022-05-19
 */
@Configuration
/**
 * 开启security
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfg extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //security 用户
        auth.inMemoryAuthentication()
                .withUser("admin1")
                .password(passwordEncoder().encode("admin1"))
                .roles("ADMIN");
        auth.inMemoryAuthentication()
                .withUser("fly")
                .password(passwordEncoder().encode("fly"))
                .roles("USER");
    }


    /**
     * 指定加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        //允许访问的资源
        String[] patterns = {
                "/web/wss/**",
                "/**.xlsx",
                "/doc.html",
                "/job/**",
                "/v2/api-docs/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**",
                "/logback/**",
                "/api",
                "/feign/**",
                "/**"};

        httpSecurity.csrf().disable().exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and().authorizeRequests().antMatchers(patterns).permitAll()
                .anyRequest().authenticated().and().httpBasic();
    }
}
