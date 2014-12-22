package foundation.config.tomcat;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}