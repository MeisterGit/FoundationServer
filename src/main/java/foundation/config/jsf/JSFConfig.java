package foundation.config.jsf;

import javax.el.ELResolver;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

import com.sun.faces.config.ConfigureListener;

/**
 * Handles configuration of (Mojarra!) JSF 2.2 atop Spring Boot.
 * @author seth.ellison
 *
 */
@Configuration
public class JSFConfig implements ServletContextAware{

	/**
	 * Create a JSF servlet bean.
	 * @return a new JSF servlet.
	 */
    @Bean
    public FacesServlet facesServlet() {
        return new FacesServlet();
    }

    /**
     * Registers the JSF Servlet to run when Spring Boot turns on.
     * JSF setup. This allows mapping xhtml requests to our FacesServlet.
     * @return a configured ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean facesServletRegistration() {

        ServletRegistrationBean registration = new ServletRegistrationBean(facesServlet(), "*.xhtml");
        registration.setName("FacesServlet");
        registration.setLoadOnStartup(1); // Initialize the JSF Servlet on startup.
        
        return registration;
    }

    /**
     * This function is called BEFORE ConfigureListener is run, which allows the initialization parameters to run.
     * Forces JSF to load its configuration.
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());       
    }
    
    /**
     * JSF's configuration listener requires a quick setup via Jasper's ConfigureListener.
     * This is a JSP dependency.
     * 
     */
    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
    }

    /**
     * Allows Spring Beans to be used within JSF.
     */
    @Bean
    public ELResolver elResolver() {
        return new SpringBeanFacesELResolver();
    }
    
}