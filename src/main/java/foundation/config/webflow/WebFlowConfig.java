package foundation.config.webflow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.executor.FlowExecutor;

/**
 * Configures the Spring Webflow system.
 * @author seth.ellison
 *
 */
@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration {

	/**
	 * Load in XML-based page flow definitions.
	 * @return
	 */
	@Bean
	public FlowDefinitionRegistry flowRegistry() {
	    return getFlowDefinitionRegistryBuilder()
	        .addFlowLocation("/WEB-INF/flows/example.xml") // By default, the id of this flow is "example" (File name without file extension)
	        // OPTION .addFlowLocation("/WEB-INF/flows/**/*-flow.xml") // Register all files within /flows ending with -flow.xml as flows. 
	        .build();
	}
	
	/**
	 * Initialize the object which executes our registered flows.
	 * @return
	 */
	@Bean
	public FlowExecutor flowExecutor() {
	    return getFlowExecutorBuilder(flowRegistry()).build();
	}
	
	
}