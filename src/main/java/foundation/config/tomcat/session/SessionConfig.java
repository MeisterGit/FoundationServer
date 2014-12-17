package foundation.config.tomcat.session;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionConfig {

    @Bean
    public EmbeddedServletContainerCustomizer servletContainerCustomizer() {
        return new SessionTimeoutConfig();
    }
}