package foundation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import foundation.dao.entities.User;
import foundation.model.UserDAO;

/**
 *  Handles the display of a web frontend via JSF and PrettyFaces.
 */
@Controller
@Scope("request") // Set the scope for this controller
@PropertySource("classpath:/config/name.properties") // Load in a custom properties file.
@PropertySource("classpath:/config/email.properties")
@URLMappings(mappings = {
	@URLMapping(id = UserController.HOME,
				pattern = "/",
				viewId = "/content/index.xhtml"), // Default page.
	@URLMapping(id = UserController.NAME,
				pattern = "/home/#{userController.name}",
				viewId = "/content/index.xhtml"), // Home page w/ name specified.
	@URLMapping(id = UserController.LEARN,
				pattern = "/learn",
				viewId = "/content/learn.xhtml"), // This just exists for testing purposes.
	@URLMapping(id = UserController.LOGIN,
				pattern = "/login",
				viewId = "/content/login.xhtml") // This just exists for testing purposes.
})
public class UserController {

	// IDs for URLMappings
	public static final String HOME = "home";
	public static final String NAME = "name";
	public static final String LEARN = "learn";
	public static final String LOGIN = "login";

	// A custom Data Access Object configured to access the Database through Hibernate.
	@Autowired
	private UserDAO userDao;
	
	// Configured at application startup, this is the default Mail system for Spring
	@Autowired
	private MailSender mailer;
	
	// Contains all properties available to this controller. (application.properties by default + name.properties + email.properties)
	@Autowired
	Environment env;
	
	@Value("${name}") // Map a value from the @PropertySource file to a variable
	private String name;
	
	/**
	 * Used in simple proof that Spring Beans are accessible in JSF. Ex: #{userController.hello}
	 * @return
	 */
	public String getHello() {
		return "Hello, " + name + "!";
	}
	
	/**
	 * Example for how to send email.
	 */
	public void sendMail() {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(env.getProperty("mail.from"));
		msg.setSubject("Learner!");
		msg.setText("You learned ALL OF THE THINGS!");
		msg.setTo("sellison@dig-inc.net");
		
		try
		{
			this.mailer.send(msg);
		}
		catch(MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

	/**
	 * Proof that simple URL->Java Function mappings can co-exist with PrettyFaces
	 * <p>
	 * Creates a new user entry into the users database table.
	 */
    @RequestMapping("/create")
    public @ResponseBody User create()
    {
    	User newUser = new User();
		newUser.setUsername("sellison");
		newUser.setAge(25);
		
		return userDao.save(newUser);
    }
    
    /**
     * Set a new name for this controller (Utilized by PrettyFaces with URL Mappings)
     * @param name
     */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Name is populated from name.properties by default, and from a PrettyFaces URLMapping optionally.
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserController.class, args);
    }
}