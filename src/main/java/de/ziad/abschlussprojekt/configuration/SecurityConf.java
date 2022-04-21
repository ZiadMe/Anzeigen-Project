package de.ziad.abschlussprojekt.configuration;

import de.ziad.abschlussprojekt.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author User
 */

@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home","/registration", "/login", "/login-error","/home/homeanzeigedetails/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/gast/**").hasRole("GAST")
                .anyRequest().authenticated()
                .and().formLogin()
                    .loginPage("/login")
                    .failureUrl("/login-error")
                .successHandler(customAuthenticationHandler());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/img/*", "/css/*");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService(); // Muss implementiert werden
    }
    
    @Bean
    public AuthenticationSuccessHandler customAuthenticationHandler() {
        return new CustomAuthenticationHandler(); 
    }
    
    
}
