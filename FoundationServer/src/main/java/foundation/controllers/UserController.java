package foundation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import foundation.dao.entities.User;
import foundation.model.UserDAO;

/**
 *  Handles the display of a web frontend via Thymeleaf.
 */
@Controller
public class UserController {

	@Autowired
	private UserDAO userDao;
	
    @RequestMapping("/")
    String home() {
        return "index";
    }
    
    @RequestMapping("/create")
    String create()
    {
    	User newUser = new User();
		newUser.setUsername("sellison");
		newUser.setAge(25);
		
		userDao.save(newUser);
		
    	return "index";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserController.class, args);
    }
}