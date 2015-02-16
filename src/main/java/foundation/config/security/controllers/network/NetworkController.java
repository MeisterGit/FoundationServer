package foundation.config.security.controllers.network;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * This controller exists to identify computers running on our internal network,
 * <br/>
 * and whether or not we're running in production mode.
 * 
 * @author Seth Ellison
 */
public class NetworkController /*extends BaseController*/ {
	
	private static final long serialVersionUID = 8364853418982875918L;
	
	private List<String> ignoreAddresses;
	
	/**
	 * The default constructor
	 */
	public NetworkController() {
		this.ignoreAddresses = new ArrayList<String>();
	}
	
	/**
	 * Gets the computer name
	 * @return String with the computer name
	 */
	public String getComputerName() {
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
		return ""; //NetworkUtils.getComputerName(request);
	}
	
	/**
	 * Gets the computer name message
	 * @return String with the computer name message
	 */
	public String getComputerNameMessage() {
		return ""; //FacesUtils.buildCompoundMessage(this.getMessages().get("network.user"), new Object[] {this.getComputerName()});
	}
	
	/**
	 * Checks to see if the remote user ip address is within the internal network ip range
	 * @return Boolean with the value of true if the remote user ip address is within the internal network ip range; otherwise false
	 */
	public Boolean getIsNetworkUser() {
		final HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		//if (this.ignoreAddresses.contains(NetworkUtils.getIpAddress(request))) {
		//	return Boolean.FALSE;
		//}
		
		return true;//NetworkUtils.isNetworkIp(request);
	}
	
	/**
	 * Gets the production status
	 * @return Boolean with the value of true if the current environment is production; otherwise false
	 */
	public Boolean getIsProduction() {
		return true; //SystemUtils.isProduction();
	}
	
	/**
	 * Gets the ignore addresses list
	 * @return List<String> with the ignore addresses list
	 */
	public List<String> getIgnoreAddresses() {
		return this.ignoreAddresses;
	}
	
	/**
	 * Sets the ignore addresses list
	 * @param ignoreAddresses the ignore addresses list
	 */
	public void setIgnoreAddresses(final List<String> ignoreAddresses) {
		this.ignoreAddresses = ignoreAddresses;
	}
	
}