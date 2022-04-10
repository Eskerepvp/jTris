package com.pepperoni.jtris.views.security;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.*;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PROC_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Not using Spring CSRF here to be able to use plan HTML for the login page
        http.csrf().disable()
                /**
                 * Register our CustomRequestCache, that saves unauthorized access attempts
                 *  so the user is redirected after login.
                 */
                .requestCache().requestCache(new CustomRequestCache())
                // Restrict access to our application.
                .and().authorizeRequests()
                // Allow all vaadin internal request.
                .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
                // Allow all requst by logged in users.
                .anyRequest().authenticated()
                // Configure the login page.
                .and().formLogin()
                .loginPage(LOGIN_URL).permitAll()
                .loginProcessingUrl(LOGIN_PROC_URL)
                .failureUrl(LOGIN_FAILURE_URL)
                // Configure the logout page.
                .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Allows access to static resources, bypassing Spring Security..
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                //Client-side js
                "/VAADIN/**",
                // the standard favicon URI
                "/favicon.ico",
                // the robots exclusion standard
                "/robots.txt",

                // web application manifest
                "/manifest.webmanifest",
                "/sw.js",
                "/offline.hmtl",

                // icons and images
                "/icons/**",
                "/images/**",
                "/styles/**",
                // (development mode) H2 debugging console
                "h2-console/**"
        );
    }
}
