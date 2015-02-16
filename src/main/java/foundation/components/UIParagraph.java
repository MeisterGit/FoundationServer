package foundation.components;

import java.io.IOException;

import javax.faces.component.FacesComponent;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 * The Paragraph Component
 * @author Seth Ellison
 */
@FacesComponent(value=UIParagraph.COMPONENT_TYPE, createTag=true, tagName="paragraph", namespace="http://www.blah.com/components/html")
public class UIParagraph extends UIComponentBase {
	
	public static final String COMPONENT_TYPE = "foundation.components.Paragraph";
	
	private String value;
	private String styleClass;
	
	
	@Override
	public void encodeBegin(final FacesContext facesContext) throws IOException {
		
		System.out.println("Encode called!");
		if(this.value != null)
		{
			final ResponseWriter writer = facesContext.getResponseWriter();
			
			writer.startElement("p", this);
			writer.writeAttribute("id", this.getClientId(), null);
			
			if (this.styleClass != null) {
				writer.writeAttribute("class", this.styleClass, null);
			}
				
			
			writer.writeText(this.value, null);
		}
	}
	
	@Override
    public String getFamily() {        
		return "blah.components.family";
    }
	
	/**
	 * Gets the paragraph's text.
	 * @return String containing the text.
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Get the CSS styleClass attribute value.
	 * @return String containing css class for this paragraph.
	 */
	public String getStyleClass() {
		return this.styleClass;
	}
	
	/**
	 * Sets the paragraph text
	 * @param value new text for the paragraph
	 */
	public void setValue(final String value) {
		this.value = value;
	}
	
	/**
	 * Sets the CSS class
	 * @param styleClass new CSS class
	 */
	public void setStyleClass(final String styleClass) {
		this.styleClass = styleClass;
	}
	
}