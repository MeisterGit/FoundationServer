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

/**
 * Handles setting up Memcached such that:
 * <p>
 * - All nodes available for caching have their IP Address + Port specified
 * <p>
 * - All file types which should NOT be cached are specified.
 * <p>
 * - The cache manager is configured to use a ConcurrentMapCache.
 * 
 * @author seth.ellison
 *
 */
//@Configuration
//@EnableCaching
public class MemcachedConfig
{
	
	/**
	 * Specify which cache manager implementation to use.
	 */
	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("default")));
		return cacheManager;
	}
	
	/**
	 * Configure embedded Tomcat server "context" to use a cache (Memcached).
	 */
	@Bean
	public EmbeddedServletContainerFactory tomcat() {
	    return new TomcatEmbeddedServletContainerFactory() {

	        @Override
	        protected void postProcessContext(Context context) {
	            MemcachedBackupSessionManager manager = new MemcachedBackupSessionManager();
	            manager.setMemcachedNodes("n1:127.0.0.1:11211"); // IP address:Port of memcached server
	            manager.setRequestUriIgnorePattern(".*\\.(ico|png|gif|jpg|css|js)$"); // Don't attempt to cache images, css, or js.
	            context.setManager(manager);
	        }

	    };
	}
}