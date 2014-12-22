package foundation.config.rewrite.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ocpsoft.pretty.PrettyFilter;
import com.ocpsoft.pretty.faces.util.StringUtils;

/**
 * A modification of the normal PrettyFilter to ignore a special URL pattern.
 * 
 * This filter exists to exclude the .../trip/... pattern from PrettyFaces.
 *  
 * @author seth.ellison
 */
public class ExcludePatternFilter extends PrettyFilter {
	
	// The pattern PrettyFaces shouldn't ever recieve.
	private String excludePattern = ".*/trip.*";
	
	/**
	 * @see com.ocpsoft.pretty.PrettyFilter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		final String url = ((HttpServletRequest)request).getRequestURL().toString();
		
		// If we match against the pattern, stop execution here, don't pass up the chain to PrettyFaces.
		if (StringUtils.isNotBlank(this.excludePattern) && url.matches(this.excludePattern)) {
			chain.doFilter(request, response);
		}
		else { // Pass up to PrettyFilter like normal.
			super.doFilter(request, response, chain);
		}
	}
	
	/**
	 * Gets the excluded  pattern
	 * @return String with the excluded patterns
	 */
	public String getTripPattern() {
		return this.excludePattern;
	}
	
	/**
	 * Sets the excluded pattern
	 * @param excludePatterns the excluded patterns
	 */
	public void setTripPattern(final String excludePattern) {
		this.excludePattern = excludePattern;
	}
	
}