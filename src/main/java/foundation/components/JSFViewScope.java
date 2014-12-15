package foundation.components;
 
import java.util.Map;
 
import javax.faces.context.FacesContext;
 
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
 
/**
 * Spring doesn't support JSF 2.0 "view" scope. This is a port of the functionality provided
 * by one of the creators of PrimeFaces.
 * 
 * @author seth.ellison
 *
 */
public class JSFViewScope implements Scope {
 
	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#get(java.lang.String, org.springframework.beans.factory.ObjectFactory)
	 */
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String,Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
 
        if(viewMap.containsKey(name)) {
            return viewMap.get(name);
        } else {
            Object object = objectFactory.getObject();
            viewMap.put(name, object);
 
            return object;
        }
    }
 
    /*
     * (non-Javadoc)
     * @see org.springframework.beans.factory.config.Scope#remove(java.lang.String)
     */
    public Object remove(String name) {
        return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
    }
 
    /*
     * (non-Javadoc)
     * @see org.springframework.beans.factory.config.Scope#getConversationId()
     */
    public String getConversationId() {
        return null;
    }
 
    /*
     * (non-Javadoc)
     * @see org.springframework.beans.factory.config.Scope#registerDestructionCallback(java.lang.String, java.lang.Runnable)
     */
    public void registerDestructionCallback(String name, Runnable callback) {
        //Not supported
    }
 
    /*
     * (non-Javadoc)
     * @see org.springframework.beans.factory.config.Scope#resolveContextualObject(java.lang.String)
     */
    public Object resolveContextualObject(String key) {
        return null;
    }
}