package foundation.controllers.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import foundation.dao.entities.User;

/**
 * Handles RESTful API aspect of the application.
 */
@Controller
public class UserRestController {
	
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public @ResponseBody User getUserSettings(@RequestHeader(value="id") long id) {
    	// Query DB using Key, fill object with result.
    	// Return JSON encoding of object.
    	
    	User u = new User();
    	u.setUsername("REST");
    	u.setId(id);
    	u.setAge(25);
    	return u;
    }
    
    @RequestMapping(value="/users", method=RequestMethod.POST)
    public @ResponseBody User createUser(@RequestHeader(value="id") long id) {
    	
    	// Create new User object
    	// Fill with defaults
    	// Propagate to DB.
    	// Return JSON object with success/fail message.
    	
    	return null;
    }
    
    @RequestMapping(value="/users", method=RequestMethod.PUT)
    public @ResponseBody User updateUser(@RequestHeader(value="id") long id) {
    	
    	// Put all user data into object (Potentially many headers!)
    	// Update DB
    	// Return JSON object with success/fail message.
    	
    	return null;
    }
}