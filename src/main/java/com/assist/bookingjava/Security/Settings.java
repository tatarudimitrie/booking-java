/*
package com.assist.bookingjava.Security;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.*;

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
        http.cors().and();
              //  anyRequest().
             //   authenticated().
               // and().httpBasic() .
               /// authenticationEntryPoint(authEntryPoint);;
      */
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
*//*


    }
   */
/* @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("*/
/**", configuration);
        return source;
    }*//*

 */
/*   @Bean
    public ComponentScan.Filter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
                "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

    }*//*

 */
/*  @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE"));
       List<String> headers = new ArrayList<>(Arrays.asList("Access-Control-Allow-Headers", "Authorization, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
               "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));

        configuration.setAllowedHeaders(headers);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("*/
/**", configuration);
        return source;
    }*//*


    */
/*@Bean
    public CorsConfigurationSource corsFilter() {
        UrlBasedCorsConfigurationSource source = new     UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");

        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("*/
/**", config);

        source.registerCorsConfiguration("*/
/**", config);


        return source;


    }*//*

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/admins/add");
        // web.ignoring().antMatchers("/login");
        //web.ignoring().antMatchers("*/
/**");
        web.ignoring().antMatchers("/css*/
/**", "/js*/
/**", "/img*/
/**", "/lib*/
/**");
    }


}*/
