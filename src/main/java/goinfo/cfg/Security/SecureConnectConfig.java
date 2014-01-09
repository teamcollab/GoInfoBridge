package goinfo.cfg.security;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${SSL.enable}")
    boolean sslEnable;
    @Value("${SSL.keystoreFile.location}")
    String sslKeystoreFileLocation;
    @Value("${SSL.keyAlias}")
    String sslKeyAlias;
    @Value("${SSL.keystorePass}")
    String sslKeystorePass;
    @Value("${SSL.clientAuth}")
    boolean sslClientAuth;
    @Value("${SSL.protocol}")
    String sslProtocol;

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

                            connector.setSecure(sslEnable);
                            connector.setScheme("https");
                            connector.setAttribute("keyAlias", sslKeyAlias);
                            connector.setAttribute("keystorePass", sslKeystorePass);
                            try {
                                connector.setAttribute("keystoreFile", ResourceUtils.getFile(sslKeystoreFileLocation).getAbsolutePath());
                            } catch (FileNotFoundException e) {
                                throw new IllegalStateException("Cannot load keystore", e);
                            }
                            connector.setAttribute("clientAuth", sslClientAuth);
                            connector.setAttribute("sslProtocol", sslProtocol);

                            connector.setAttribute("SSLEnabled", sslEnable);

                        }
                    });
                }
            }
        };
    }

}
