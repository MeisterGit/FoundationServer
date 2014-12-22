package foundation.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Java Configuration for our Spring Security package.
 * 
 * @author seth.ellison
 *
 */
@Configuration
@EnableWebSecurity
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
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
    
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
    	http
    		.logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}