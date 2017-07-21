package com.assist.bookingjava;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Boot extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }
}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    AdminRepository adminRepository;
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return email -> {
            Admin company = adminRepository.findByEmail(email);
        if(company != null) {
            return new User(company.getEmail(), company.getPass(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
        }
        else {
            throw new UsernameNotFoundException("could not find the user '"            + email + "'");
        }    };
    }
}


@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().fullyAuthenticated().and().
        httpBasic().and().        csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/**");

 }
}