package foundation.config.tomcat.mime;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.context.annotation.Configuration;

/**
 * Replaces web.xml &lt;mime-mapping&gt; entries.
 * <p>
 * This allows Tomcat to cleanly serve up a variety of file types.
 * 
 * @author seth.ellison
 *
 */
public class MimeConfigBean implements EmbeddedServletContainerCustomizer {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer#customize(org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer)
	 */
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		
		// Detail custom MIME mappings. <extension, mime-type> 
		MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
		mappings.add("xhtml", "application/xml");
		
		// Fonts
		mappings.add("eot", "application/vnd.ms-fontobject");
		mappings.add("otf", "application/font-sfnt");
		mappings.add("svg", "images/svg+xml");
		mappings.add("tff", "application/font-sfnt");
		mappings.add("woff", "application/font-woff");

		container.setMimeMappings(mappings);
	}
}