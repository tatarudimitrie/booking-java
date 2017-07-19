package com.assist.bookingjava.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class Settings extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService detailsService;
    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    @Autowired
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(detailsService).passwordEncoder(PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.csrf().

                disable().
              //  authorizeRequests().antMatchers("/login").permitAll().and().
                authorizeRequests() .
                anyRequest().
                authenticated().
                and().httpBasic() .
                authenticationEntryPoint(authEntryPoint);

      /*  http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
*/

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/admins/add");
       // web.ignoring().antMatchers("/login");
      //  web.ignoring().antMatchers("/**");
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }


}