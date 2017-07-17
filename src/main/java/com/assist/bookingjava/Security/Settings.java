package com.assist.bookingjava.Security;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.repositories.AdminRepository;
import org.apache.catalina.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Settings extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    DetailsService detailsService;

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       List<Admin> adminList = new ArrayList<>();
       for(Admin a : adminRepository.findAll()) {
           a.getPass();

           auth.inMemoryAuthentication().withUser(a.getName()).password(a.getPass()).roles("USER");
           adminList.add(a);
       }
       auth.userDetailsService(detailsService).passwordEncoder(PASSWORD_ENCODER);

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
               .authorizeRequests()
               .antMatchers("**/login").permitAll()
               .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/companies")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()

                .permitAll();

       // http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login")).and().authorizeRequests()
       //         .antMatchers("/companies").hasRole("USER").and().formLogin().defaultSuccessUrl("/companies")
       //         .loginPage("/login").and().logout().permitAll();
      //  http.headers().frameOptions().disable().addHeaderWriter(new StaticHeadersWriter("X-FRAME-OPTIONS", "SAMEORIGIN"));


    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/admins");
        web.ignoring().antMatchers("/admins/input");
        web.ignoring().antMatchers("/*.css");
        web.ignoring().antMatchers("/*.js");
    }

}
