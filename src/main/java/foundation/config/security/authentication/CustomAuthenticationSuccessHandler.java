package foundation.config.security.authentication;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * When a user successfully logs in, this class handles redirecting the browser based on the current user's role.
 * <p>
 * See SecurityConfig.java for invocation of this class.
 * <br/>
 * An alternative to this class in the HttpSecurity chain is a .defaultSuccessUrl(String) call.
 * <br/>
 * Super simple, but no room for extra logic during authentication.
 * @author seth.ellison
 *
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	// Used to send a redirect to the client.
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		
        handle(request, response, authentication); // Handle the authentication success
        clearAuthenticationAttributes(request); // Clean up the session linked to the request.
        
    }
 
	/**
	 * Retrieves the URL needed for the redirect, determines if a redirect is a valid action, and sends the redirect.
	 * 
	 * @param request The current request linked to submission of the login form.
	 * @param response The response which will be redirected.
	 * @param authentication Represents the authenticated user.
	 * @throws IOException
	 */
    protected void handle(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException {
    	
        String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
            System.out.println("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
 
    /**
     * Builds the target URL according to the role of the logging in user.
     * <p>
     * - Admins go to an admin control panel.
     * <br/>
     * - Users go to the home page.
     */
    protected String determineTargetUrl(Authentication authentication) {
    	
        boolean isUser = false;
        boolean isAdmin = false;
        
        // Determine the authority (role) of the user who logged in.
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }
 
        // Redirect based on role.
        if (isUser) {
            return "/";
        } else if (isAdmin) {
            return "/admin";
        } else {
            throw new IllegalStateException();
        }
    }
 
    /**
     * Clear all authentication attributes from the request's session.
     * @param request The request to be cleaned up.
     */
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
    	
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}