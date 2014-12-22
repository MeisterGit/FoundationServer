package foundation.config.tomcat;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.HttpSessionMutexListener;

import com.sun.faces.config.ConfigureListener;

import foundation.config.tomcat.mime.MimeConfigBean;
import foundation.config.tomcat.session.SessionConfigBean;

/**
 * Customizes our embedded Tomcat server. All customizers are turned into Spring beans within this class.
 * <p>
 * This is a top-level configuration file. It aggregates all of the pieces of our Tomcat configuration into a single file for ease of reference.
 * @author seth.ellison
 *
 */
@Configuration
public class TomcatConfig
{
	
	/**
	 * Sets up our custom Tomcat Session settings. (Timeout to 60m, etc...)
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer sessionCustomizer() {
		return new SessionConfigBean();
	}
	
	/**
	 * Sets up Tomcat to serve content across various MIME types. (Fonts, XML, Images, etc...)
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer mimeCustomizer() {
		return new MimeConfigBean();
	}
	
	/**
	 * Sets up a listener for the HttpSessionMutex. This is one of the safest objects to synchronize on in our system.
	 */
	@Bean
    public ServletListenerRegistrationBean<HttpSessionMutexListener> httpSessionMutexListener() {
        return new ServletListenerRegistrationBean<HttpSessionMutexListener>(
        		new HttpSessionMutexListener());
    }
}