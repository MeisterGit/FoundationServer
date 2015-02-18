package foundation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;


/**
 * The lowest level of application definition for the FoundationServer project.
 * This replaces the need for Spring's applicationContext.xml.
 * 
 * @author seth.ellison
 *
 */
//@Configuration
//@ComponentScan // Starts at this package, and goes down from there.
//@EnableAutoConfiguration // Starts at this package, and goes down from there.
@SpringBootApplication // Equivalent to using @Configuration, @ComponentScan, and @EnableAutoConfiguration with default settings.
public class Application extends SpringBootServletInitializer{

   //public static void main(String[] args) {
   //     SpringApplication.run(Application.class, args);
   // }
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(Application.class);
	}
	
}
