package utils;

import java.io.*;
import java.util.*;

public class PropertiesFileReader {

	InputStream inputStream;
	String result = "";

	public String getPropertyvalues(String property) {
		try {
			Properties prop = new Properties();
			// Gets a stream of properties file
			inputStream = getClass().getClassLoader().getResourceAsStream("config/config.properties");
			if (inputStream != null) {
				prop.load(inputStream);

			} else {
				throw new FileNotFoundException("Property File  Not found");
			}

			result = prop.getProperty(property);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
