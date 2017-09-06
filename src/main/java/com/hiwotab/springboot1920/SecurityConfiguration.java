package com.hiwotab.springboot1920;

import com.hiwotab.springboot1920.services.SSUserDetailsService;
import com.hiwotab.springboot1920.repositories.NUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SSUserDetailsService userDetailsSerevice;

    @Autowired
    private NUserRepo userRepo;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUserDetailsService(userRepo);
    }

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth)throws  Exception{
//        auth.inMemoryAuthentication().
//                withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("dave").password("begreat").roles("ADMIN");
//
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
//                .access("hasRole('ROLE_USER') or hasRole('ADMIN')")
//                .antMatchers("/admin").access("hasRole('ADMIN')")
                .antMatchers("/","/signUpForm","/css/**","/js/**","/img/**","/font-awesome/**","lib/**").permitAll()//
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/index")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll().permitAll()
                .and()
                .httpBasic();
        http
                .csrf().disable();
        http
                .headers().frameOptions().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsServiceBean());
    }
}