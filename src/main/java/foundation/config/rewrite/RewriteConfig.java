package foundation.config.rewrite;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;

import org.ocpsoft.logging.Logger.Level;
import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Log;
import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@RewriteConfiguration
public class RewriteConfig //extends HttpConfigurationProvider
{
	/*
	@Override
	public int priority()
	{
		return 10;
	}
	
	@Override
	public org.ocpsoft.rewrite.config.Configuration getConfiguration(ServletContext context)
	{
		System.err.println("Rewrite getConfiguration() executed.");
		return ConfigurationBuilder.begin()
				.addRule()
				.perform(Log.message(Level.INFO, "Rewrite is active.")) ;
   }
   */
   
}