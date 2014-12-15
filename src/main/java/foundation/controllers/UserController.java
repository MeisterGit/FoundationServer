package foundation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import foundation.model.UserDAO;

/**
 *  Handles the display of a web frontend via JSF and PrettyFaces.
 */
@Controller
@URLMapping(id = UserController.INDEX,
			pattern = "/",
			viewId = "/content/index.xhtml") // Home page.
public class UserController {

	public static final String INDEX = "index";
	
	@Autowired
	private UserDAO userDao;
	
	private String name = "Seth";
	
	public String getHello() {
		return "Hello, " + name + "!";
	}
	
    /*
    @RequestMapping("/create")
    String create()
    {
    	User newUser = new User();
		newUser.setUsername("sellison");
		newUser.setAge(25);
		
		userDao.save(newUser);
		
    	return "index";
    }*/

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserController.class, args);
    }
}