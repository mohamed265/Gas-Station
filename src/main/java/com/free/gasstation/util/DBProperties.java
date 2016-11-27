package com.free.gasstation.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBProperties {

	private String url = "jdbc:mysql://localhost:3306/gas-station";
	private String user = "root";
	private String password = "root";
	private String driver = "com.mysql.jdbc.Driver";
	private int poolSize = 5;

	public void loadConfig() {
		try {
			InputStream fis = this.getClass().getClassLoader()
					.getResourceAsStream("db.properties");
			Properties props = new Properties();
			props.load(fis);
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			driver = props.getProperty("driver");
			poolSize = Integer.parseInt(props.getProperty("poolsize"));
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}
}