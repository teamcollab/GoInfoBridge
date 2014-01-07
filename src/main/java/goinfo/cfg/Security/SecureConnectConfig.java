package goinfo.cfg.security;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@Configuration
public class SecureConnectConfig {
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainerFactory factory) {
                if(factory instanceof TomcatEmbeddedServletContainerFactory){
                    TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) factory;
                    containerFactory.addConnectorCustomizers(new TomcatConnectorCustomizer() {

                        @Override
                        public void customize(Connector connector) {

                            connector.setSecure(true);
                            connector.setScheme("https");
                            connector.setAttribute("keyAlias", "tomcat");
                            connector.setAttribute("keystorePass", "tomcat");
                            try {
                                connector.setAttribute("keystoreFile", ResourceUtils.getFile("/Users/Spooky/projects/goinfoBridgeService/tomcat.keystore").getAbsolutePath());
                            } catch (FileNotFoundException e) {
                                throw new IllegalStateException("Cannot load keystore", e);
                            }
                            connector.setAttribute("clientAuth", "false");
                            connector.setAttribute("sslProtocol", "TLS");
                            connector.setAttribute("SSLEnabled", true);

                        }
                    });
                }
            }
        };
    }

}
