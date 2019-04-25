package com.tian.boot.config;

import com.tian.boot.entity.User;
import com.tian.boot.utils.MyPasswordEncoder;
import com.tian.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @BelongProject:security
 * @BelongPackage:com.tian.security.config
 * @Author:田宇寒
 * @CreateTime:2019-03-18
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService<User> userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new MyPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许基于HttpServletRequest使用限制访问
        http.authorizeRequests()
                //不需要身份验证
                .antMatchers("/js/**","/css/**","/images/**","/fronts/**","/doc/**","/login").permitAll()
                .antMatchers("/authenticate").hasRole("authenticate")
                .anyRequest().authenticated()
                //自定义登陆界面
                .and().formLogin()
                .loginProcessingUrl("/login").failureUrl("/login?error")
                .permitAll().defaultSuccessUrl("/index")
                .and().headers().frameOptions().disable()
                .and().logout().logoutSuccessUrl("/login?logout")
                .and().exceptionHandling().accessDeniedPage("/login?deny")
                .and().httpBasic()
                .and().sessionManagement().invalidSessionUrl("/login")
                .and().rememberMe()
                .and().csrf().disable();
    }
}
