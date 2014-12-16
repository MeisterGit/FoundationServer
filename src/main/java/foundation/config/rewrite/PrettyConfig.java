package foundation.config.rewrite;

import javax.servlet.DispatcherType;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PrettyConfig
{
	@Bean
	public FilterRegistrationBean prettyFilter() {
	    FilterRegistrationBean prettyFilter = new FilterRegistrationBean(new RewriteFilter());
	    prettyFilter.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST,
	            DispatcherType.ASYNC, DispatcherType.ERROR);
	    prettyFilter.addUrlPatterns("/*");
	    
	    prettyFilter.addInitParameter("com.ocpsoft.pretty.BASE_PACKAGES", "foundation");
	    return prettyFilter;
	}
}