package br.com.todo.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService ())
                .authorizeRequests ()
                .antMatchers ( HttpMethod.OPTIONS, "/**" ).permitAll ()
                .anyRequest ().permitAll ()
                .and ()
                .httpBasic ()
                .and ()
                .csrf ().disable ();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager result = new InMemoryUserDetailsManager();
        result.createUser( User.withDefaultPasswordEncoder().username("todo").password("todo").authorities("USER").build());

        return result;
    }
}
