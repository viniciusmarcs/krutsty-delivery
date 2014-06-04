package jfast.core.dwr;

import java.beans.PropertyDescriptor;

import org.directwebremoting.extend.Property;
import org.directwebremoting.impl.PropertyDescriptorProperty;

/**
 * A {@link Property} that catches TopLink exceptions. This is useful for
 * TopLink where lazy loading results in an exception and you are unable to
 * detect and prevent this.
 * 
 * @author <a href="mailto:vinicius.marcs@gmail.com">Vinicius </a>
 */
public class TopLinkPropertyDescriptorProperty extends
		PropertyDescriptorProperty {

	/**
	 * Simple constructor
	 * 
	 * @param descriptor
	 *            The PropertyDescriptor that we are proxying to
	 */
	public TopLinkPropertyDescriptorProperty(PropertyDescriptor descriptor) {
		super(descriptor);
	}

}