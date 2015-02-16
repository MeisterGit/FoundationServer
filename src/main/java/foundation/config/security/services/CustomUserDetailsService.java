package foundation.config.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import foundation.dao.entities.User;
import foundation.model.UserDAO;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserDAO userDao;
	
	/**
	 * Gets a user's information from their username.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername("user");
		
		System.out.println(user.getUsername());
		return new org.springframework.security.core.userdetails.User(user.getUsername(),
						"supersecretpassword",
						true, true, true, true, null); // <-- SO INCOMPLETE
	}
	
	// ========== EVERYTHING BELOW THIS LINE IS PLACEHOLDER ================
	// An array-list of roles constructed from a set.
	public List<String> getRolesAsList(Set<Role> roles) {
	    List <String> rolesAsList = new ArrayList<String>();
	    for(Role role : roles){
	        rolesAsList.add(role.toString());
	    }
	    return rolesAsList;
	}
	
	// Construct a list of SimpleGrantedAuthority objects from the roles owned by this user.
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    for (String role : roles) {
	        authorities.add(new SimpleGrantedAuthority(role));
	    }
	    return authorities;
	}

	//  A collection of granted authorities for a given user.
	public Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
	    List<GrantedAuthority> authList = getGrantedAuthorities(getRolesAsList(roles));
	    return authList;
	}
	
}