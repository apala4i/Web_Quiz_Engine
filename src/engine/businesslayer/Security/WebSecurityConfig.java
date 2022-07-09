package engine.businesslayer.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsService = userDetailsServiceImpl;
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
//                .mvcMatchers("/**").permitAll() // access to all for testing
                .mvcMatchers(HttpMethod.POST, "/actuator/shutdown", "/api/register").permitAll()
                .mvcMatchers("/**").authenticated()
                .and()
                .csrf().disable()
                .httpBasic().and()
                .formLogin();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

}
