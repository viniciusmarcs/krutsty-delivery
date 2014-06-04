package jfast.core.dwr;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.directwebremoting.convert.BeanConverter;
import org.directwebremoting.extend.MarshallException;
import org.directwebremoting.extend.Property;

/**
 * BeanConverter that works with TopLink.
 * 
 * @author <a href="mailto:vinicius.marcs@gmail.com">Marcos Vinicius</a>
 */
public class TopLinkConverter extends BeanConverter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.directwebremoting.convert.BeanConverter#getPropertyMapFromObject(
	 * java.lang.Object, boolean, boolean)
	 */
	@Override
	public Map<String, Property> getPropertyMapFromObject(Object example,
			boolean readRequired, boolean writeRequired)
			throws MarshallException {
		Class<?> clazz = example.getClass();
		try {
			BeanInfo info = Introspector.getBeanInfo(clazz);

			Map<String, Property> properties = new HashMap<String, Property>();
			for (PropertyDescriptor descriptor : info.getPropertyDescriptors()) {
				String name = descriptor.getName();

				// We don't marshall getClass()
				if ("class".equals(name)) {
					continue;
				}

				// Access rules mean we might not want to do this one
				if (!isAllowedByIncludeExcludeRules(name)) {
					continue;
				}

				if (readRequired && descriptor.getReadMethod() == null) {
					continue;
				}

				if (writeRequired && descriptor.getWriteMethod() == null) {
					continue;
				}

				properties.put(name, new TopLinkPropertyDescriptorProperty(
						descriptor));
			}

			return properties;
		} catch (Exception ex) {
			throw new MarshallException(clazz, ex);
		}
	}
}