package goinfo.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/test/sendMsg").hasRole("USER")
                .anyRequest().authenticated();
//        http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
//                .sessionFixation().none()
//                .and().csrf().disable();

        http.httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
        authManagerBuilder.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN");
    }
}
