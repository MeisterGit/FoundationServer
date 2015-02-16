package foundation.config.security.authentication;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import foundation.model.UserDAO;


/**
 * The default implementations for authentication assume too much about table structure in the database.
 * <br/>
 * Our current system isn't even close to the same layout, and we have many more tables.
 * <p>
 * As a result, this custom authentication provider exists to override the Spring Security authenticate()
 * <br/>
 * functionality, rebuilding it to query our unique structure instead of using the default assumptions for JDBC authentication.
 * <p>
 * This class also handles assigning authorities (roles) to a user if their username + pass combo is valid.
 *   
 * @author seth.ellison
 *
 */
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
	@Autowired
	private UserDAO userDao;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		// When the login form is submitted, Spring Security provides an Authentication object with the username and credentials.
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		String version = FacesContext.class.getPackage().getImplementationVersion();
		System.out.println("JSF Version: " + version);
		System.out.println("name: " + name + "\npassword:" + password);
		
		// Generate the salt+hash for the password.
		
		// Test the username and generated hash against the database.
		if (userDao.findByUsername(name) != null) {
			System.out.println("Match found.");
			// List of roles allowed for this user. Traditionally, this list is queried from the DB.
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
			Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
			return auth;
		} else {
			System.out.println("No Matching Record");
			
			// No match, return null.
			return null;
		}
	}
	
	@Override
    public boolean supports(Class<?> authentication) {
       // return authentication.equals(UsernamePasswordAuthenticationToken.class);
		return true;
    }
	
	/*
	@Override
	protected void additionalAuthenticationChecks(final UserDetails userDetails, final UsernamePasswordAuthenticationToken authentication) {
		Object salt = null;
		if (this.getSaltSource() != null) {
			salt = this.getSaltSource().getSalt(userDetails);
		}
		
		if (authentication.getCredentials() == null) {
			this.logger.debug("Authentication failed: no credentials provided");
			throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
		
		// Scary stuff...
//		if (this.networkController.getIsNetworkUser()) {
//			this.logger.debug("Skipping the password check because the user is 'in-house'");
//		}
		else 
		{
			final String presentedPassword = authentication.getCredentials().toString();
			if (BooleanUtils.isFalse(this.getPasswordEncoder().isPasswordValid(userDetails.getPassword(), presentedPassword, salt)))
			{
				this.logger.debug("Invalid Password");
			}
			else
			{
				this.logger.debug("Valid password!");
			}
			
			/* Original (Test above)
			if (userDetails instanceof OnlineAccessUser) {
				final OnlineAccessUser user = (OnlineAccessUser)userDetails;
				final TemporaryAccount temporaryAccount = this.temporaryAccountService.get(user.getUserId());
				if (temporaryAccount != null) {
					final String presentedPassword = authentication.getCredentials().toString();
					if (BooleanUtils.isFalse(StringUtils.equals(temporaryAccount.getPassword(), presentedPassword))) {
						this.logger.debug("Authentication failed: password does not match stored value");
						throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
					}
				}
				else {
					final String presentedPassword = authentication.getCredentials().toString();
					if (BooleanUtils.isFalse(this.getPasswordEncoder().isPasswordValid(userDetails.getPassword(), presentedPassword, salt))) {
						this.logger.debug("Authentication failed: password does not match stored value");
						throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
					}
				}
			}*/
	//	}
	//}
}
