package foundation.config.jsf;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import com.sun.faces.config.ConfigureListener;

/**
 * Handles configuration of (Mojarra!) JSF 2.2 atop Spring Boot. The beans in here are loaded on application startup.
 * @author seth.ellison
 *
 */
@Configuration
public class JSFConfig implements ServletContextAware{

    /**
     * Registers the JSF Servlet to run when Spring Boot turns on.
     * JSF setup. This allows mapping xhtml requests to our FacesServlet.
     * @return a configured ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean facesServletRegistration() {
    	
    	String[] urlPatterns = {"*.xhtml"};
    	
        ServletRegistrationBean registration = new ServletRegistrationBean(new FacesServlet(), urlPatterns); // Map to any URL ending in .xhtml or starting with /faces/*
        registration.setName("Faces Servlet");

        registration.setLoadOnStartup(1); // Initialize the JSF Servlet on startup.
        
        return registration;
    }


    /*
     * (non-Javadoc)
     * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
    	// This function is called BEFORE ConfigureListener is driven, which allows the init parameter to take effect.
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());       
    }
    
    /**
     * JSF's configuration listener requires a quick setup via Jasper's ConfigureListener.
     * This is a JSP dependency.
     * 
     */
    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<ConfigureListener>(
        		new ConfigureListener());
    }
}