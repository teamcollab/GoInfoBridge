package goinfo.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.authentication.configurers.InMemoryClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.OAuth2ServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.OAuth2ServerConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;

@EnableWebSecurity
@Configuration
@Order(1)
public class OAuth2ServerConfig extends OAuth2ServerConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .apply(new InMemoryClientDetailsServiceConfigurer())
                    .withClient("clientId")
                    .authorizedGrantTypes("client_credentials","implicit")
                    .authorities("ROLE_CLIENT")
                    .scopes("read", "write")
                    .secret("clientSecret");
    }

    @Bean
    @DependsOn("springSecurityFilterChain") // FIXME remove the need for @DependsOn
    public SparklrUserApprovalHandler userApprovalHandler() throws Exception {
        SparklrUserApprovalHandler handler = new SparklrUserApprovalHandler();
        handler.setTokenServices(tokenServices());
        handler.setUseTokenServices(true);
        return handler;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/oauth/token").fullyAuthenticated()
                .antMatchers("/test/sendMsg").authenticated()
//                .access("#oauth2.clientHasRole('ROLE_CLIENT') and #oauth2.isClient() and #oauth2.hasScope('read')")

                .and()
            .requestMatchers()
                .antMatchers("/test/sendMsg", "/oauth/token", "/oauth/authorize")
                .and()
            .apply(new OAuth2ServerConfigurer());
    }
}
