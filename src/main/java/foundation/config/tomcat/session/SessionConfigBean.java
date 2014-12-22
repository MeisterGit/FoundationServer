package foundation.config.tomcat.session;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

/**
 * Replacement for web.xml &lt;session-config&gt;
 * <p>
 * Session configured to behave like a cookie.
 * <p>
 * Reasoning: We need the session to expire after 60m if the user is idle. Additionally,
 * if the user closes their browser, we want the session to die immediately.
 *  
 * @author seth.ellison
 *
 */
public class SessionConfigBean implements EmbeddedServletContainerCustomizer {
	/*
	 * (non-Javadoc)
	 * @see org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer#customize(org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer)
	 */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
    	
    	// Configure our embedded Tomcat server so that sessions act like cookies, expiring after 60m.
        TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) configurableEmbeddedServletContainer;
        tomcat.setSessionTimeout(60, TimeUnit.MINUTES);
    }
}