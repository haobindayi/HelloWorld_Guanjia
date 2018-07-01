package com.it.tools;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class ConfigUtils {
	private ConfigUtils() {}
	private static BasicDataSource ds=new BasicDataSource();
	static {
		Properties properties = new Properties();
		try {
			InputStream in = ConfigUtils.class.getClassLoader().getResourceAsStream("config.properties");
			properties.load(in);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e+"≈‰÷√Œƒº˛∂¡»°¥ÌŒÛ");
		}		
		try {			
			ds.setDriverClassName(properties.getProperty("driver"));
			ds.setUrl(properties.getProperty("url"));
			ds.setUsername(properties.getProperty("user"));
			ds.setPassword(properties.getProperty("password"));
			ds.setInitialSize(10);
			ds.setMaxActive(10);
			ds.setMaxIdle(5);
			ds.setMinIdle(2);
			} catch (Exception e) {				
				e.printStackTrace();				
			}
	}	
	public static DataSource getDataSource() {
		return ds;
	}
}
