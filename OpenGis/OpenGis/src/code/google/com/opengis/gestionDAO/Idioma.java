package code.google.com.opengis.gestionDAO;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Idioma {
	
	
	private static String BUNDLE_NAME;

	public Idioma(String fichero) {
		
		BUNDLE_NAME = fichero;
		System.out.println(fichero);
		
	}


	public static String getString(String key) {
		try {
			
			ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
