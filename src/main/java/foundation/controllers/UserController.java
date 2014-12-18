package foundation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import foundation.model.UserDAO;

/**
 *  Handles the display of a web frontend via JSF and PrettyFaces.
 */
@Controller
@Scope("request") // Set the scope for this controller
@PropertySource("classpath:/config/name.properties") // Load in a custom properties file.
@URLMappings(mappings = {
	@URLMapping(id = UserController.HOME,
				pattern = "/",
				viewId = "/content/index.xhtml"), // Default page.
	@URLMapping(id = UserController.NAME,
				pattern = "/home/#{userController.name}",
				viewId = "/content/index.xhtml") // Home page w/ name specified.
})
public class UserController {

	public static final String HOME = "home";
	public static final String NAME = "name";
	
	@Autowired
	private UserDAO userDao;
	
	@Value("${name}") // Map a value from the @PropertySource file to a variable
	private String name;
	
	public String getHello() {
		return "Hello, " + name + "!";
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
    /*
    @RequestMapping("/create")
    String create()
    {
    	User newUser = new User();
		newUser.setUsername("sellison");
		newUser.setAge(25);
		
		userDao.save(newUser);
		
    }*/

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserController.class, args);
    }
}