package com.free.gasstation.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailProperties {

	public Properties loadConfig() {
		try {
			InputStream fis = this.getClass().getClassLoader().getResourceAsStream("email.properties");
			Properties props = new Properties();
			props.load(fis);
			fis.close();
			return props;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}