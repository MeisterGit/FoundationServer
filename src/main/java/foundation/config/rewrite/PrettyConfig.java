package foundation.config.rewrite;

import javax.servlet.DispatcherType;

//import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ocpsoft.pretty.PrettyFilter;

@Configuration
public class PrettyConfig {
	@Bean 
	public FilterRegistrationBean prettyFilter() {
		FilterRegistrationBean rewriteFilter = new FilterRegistrationBean(new PrettyFilter());
		
		rewriteFilter.setDispatcherTypes(DispatcherType.FORWARD,
										DispatcherType.REQUEST,
										DispatcherType.ASYNC,
										DispatcherType.ERROR);
		
		rewriteFilter.addUrlPatterns("/*");
		
		return rewriteFilter;
	}
}