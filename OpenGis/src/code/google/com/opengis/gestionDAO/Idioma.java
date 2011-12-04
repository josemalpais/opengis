package code.google.com.opengis.gestionDAO;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Idioma {
	private static final String BUNDLE_NAME = "resources.lang_en_US"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Idioma() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
