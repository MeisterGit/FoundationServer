package foundation.config.cache;

import java.util.Arrays;

import org.apache.catalina.Context;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.javakaffee.web.msm.MemcachedBackupSessionManager;

@Configuration
@EnableCaching
public class MemcachedConfig
{
	
	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("default")));
		return cacheManager;
	}
	
	@Bean
	public EmbeddedServletContainerFactory tomcat() {
	    return new TomcatEmbeddedServletContainerFactory() {

	        @Override
	        protected void postProcessContext(Context context) {
	            MemcachedBackupSessionManager manager = new MemcachedBackupSessionManager();
	            manager.setMemcachedNodes("localhost:11211"); // IP address:Port of memcached server
	            manager.setRequestUriIgnorePattern(".*\\.(ico|png|gif|jpg|css|js)$"); // Don't attempt to cache images, css, or js.
	            context.setManager(manager);
	        }

	    };
	}
}