package foundation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * The lowest level of application definition for the FoundationServer project.
 * @author seth.ellison
 *
 */
@Configuration
@ComponentScan // Starts at this package, and goes down from there.
@EnableAutoConfiguration // Starts at this package, and goes down from there.
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
