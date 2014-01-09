package goinfo.cfg.security;

import org.springframework.beans.factory.annotation.Value;
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

@EnableWebSecurity
@Configuration
@Order(1)
public class OAuth2ServerConfig extends OAuth2ServerConfigurerAdapter {

    @Value("${oAuth2.secureIpAddress}")
    String secureIpAddress;
    @Value("${oAuth2.client}")
    String client;
    @Value("${oAuth2.secret}")
    String secret;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .apply(new InMemoryClientDetailsServiceConfigurer())
                    .withClient(client)
                    .authorizedGrantTypes("client_credentials")
                    .authorities("ROLE_CLIENT")
                    .scopes("read", "write")
                    .secret(secret);
    }

    @Bean
    @DependsOn("springSecurityFilterChain") // FIXME remove the need for @DependsOn
    public UserApprovalHandler userApprovalHandler() throws Exception {
        UserApprovalHandler handler = new UserApprovalHandler();
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

        http.csrf().disable();

        if(!secureIpAddress.equals("")){
            http
                .authorizeRequests()
                    .antMatchers("/oauth/token").hasIpAddress(secureIpAddress);
        }else {
            http
                .authorizeRequests()
                    .antMatchers("/oauth/token").fullyAuthenticated();
        }

        http
            .authorizeRequests()
                .antMatchers("/test/sendMsg").authenticated()
                .antMatchers("/rest/api").authenticated()
                .antMatchers("/admin/.*").authenticated();
//                .access("#oauth2.clientHasRole('ROLE_CLIENT') and #oauth2.isClient() and #oauth2.hasScope('read')")


        http.apply(new OAuth2ServerConfigurer());

    }
}
