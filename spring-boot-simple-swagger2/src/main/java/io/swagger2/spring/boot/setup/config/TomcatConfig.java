package io.swagger2.spring.boot.setup.config;


import org.springframework.context.annotation.Configuration;

/**
 * tomcat 配置
 */
@Configuration
public class TomcatConfig {
/*
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.setUriEncoding(Charset.forName("UTF-8"));
        tomcat.addAdditionalTomcatConnectors(createSslConnector());
        return tomcat;
    }

    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        try {
            File truststore = new File("/Users/liaokailin/software/ca1/keystore");
            connector.setScheme("https");
            protocol.setSSLEnabled(true);
            connector.setSecure(true);
            connector.setPort(8443);
            protocol.setKeystoreFile(truststore.getAbsolutePath());
            protocol.setKeystorePass("123456");
            protocol.setKeyAlias("springboot");
            return connector;
        } catch (Exception ex) {
            throw new IllegalStateException("cant access keystore: [" + "keystore" + "]  ", ex);
        }
    }*/
}