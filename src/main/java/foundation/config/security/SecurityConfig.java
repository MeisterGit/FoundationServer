package foundation.config.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import foundation.config.security.authentication.FdlicAuthenticationProvider;
import foundation.config.security.authentication.FdlicAuthenticationSuccessHandler;
import foundation.config.security.services.FdlicUserDetailsService;

/**
 * Java Configuration for our Spring Security package.
 * 
 * @author seth.ellison
 *
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * This function creates an in-memory authentication system. It is completely temporary, and needs to be replaced
	 * with an actual database of usernames + hashed passwords to validate against. 
	 * 
	 * @param auth Used to build a very simple authentication record.
	 * @throws Exception
	 */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
    	// In-Memory Authentication DB.
        auth
        	.authenticationProvider(this.fdlicAuthenticationProvider());
    }
    
	/**
	 * This function creates an in-memory authentication system. It is completely temporary, and needs to be replaced
	 * with an actual database of usernames + hashed passwords to validate against. 
	 * 
	 * @param auth Used to build a very simple authentication record.
	 * @throws Exception
	 */
    //@Autowired
    //public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //    auth
    //        .inMemoryAuthentication()
    //            .withUser("user").password("password").roles("USER");
    //}
    
    /**
     *  [Currently not functioning.]
     *  
     * Set up pattern matcher for logout. 
     *
     * Redirects to /login?logout
     * When CSRF is turned on, logout has to be done with a form. Using this GET style is not recommended,
     * and I should simply create a logout form to submit via JS when a link/button is clicked.
     * 
     * This prevents malicious attackers from logging out our users.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	/*http
    		.logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));*/
    	
    	http
	        .authorizeRequests()
	        	.antMatchers("/resources/**").permitAll() // Allows anyone to access a URL that begins with /resources/ since this is where our static resources live. 
	            .anyRequest().authenticated() // Require authentication for any URL (below, an exception is made.)
	            .and()
	        .formLogin()
	            .loginPage("/login") // The specific location of the login page. This is what PrettyFaces rewrites /login to behind the scenes.
	            .successHandler(new FdlicAuthenticationSuccessHandler())
	            .permitAll(); // We must grant all users (i.e. unauthenticated users) access to our log in page. The formLogin().permitAll() method allows granting access to all users for all URLs associated with form based log in.
    }
    
    /**
     * We need to instantiate the spring security filter explicitly, because by default it is registered with the lowest precedence.
     * Spring security needs to run BEFORE PrettyFaces, or else we end up with an infinite redirect loop.
     * <p>
     * This loop is extra lame, because the fix requires either this function, or a hard-coded login page (/content/login.xhtml) that is normally
     * hidden by PrettyFaces. We want to have our pretty URLs and secure them too, so this is the solution.
     *  
     * @param securityFilter The spring security filter normally autowired.
     * @return A re-configured Spring Security filter chain guaranteed to be run first.
     */
    @Bean
    public FilterRegistrationBean securityFilterChain(
            @Qualifier(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME) Filter securityFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(securityFilter);
        registration.setOrder(0); // FIRST Filter
        registration.setName(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME);
        
        return registration;
    }
    
    // Handles authenticating users. Wires up our custom authentication provider to override the Spring Security default, and links up the user detail service.
    @Bean
    public AuthenticationProvider fdlicAuthenticationProvider() {
    	FdlicAuthenticationProvider provider = new FdlicAuthenticationProvider();
    	
    	provider.setUserDetailsService(this.fdlicUserDetailsService());
    	
    	return provider;
    }
    
    // Used ONLY for providing use data. This does NOT authenticate anything.
    @Bean
    public UserDetailsService fdlicUserDetailsService() {
    	
    	UserDetailsService service = new FdlicUserDetailsService();
    	
    	return service;
    }
}