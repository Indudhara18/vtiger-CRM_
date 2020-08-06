package com.autodeskcrm.genericUtils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 
 * @author indudhara
 *
 */
public class FileLib {
	
	String filepath = "./testData/commonData.properties";
	
	/**
	 * To get the data from properties file by passing key
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	public String getPropertyKeyValue(String key) throws Throwable
	{
		FileInputStream fis = new FileInputStream(filepath);
		Properties pobj = new Properties();
		pobj.load(fis);
		String value = pobj.getProperty(key);
		return value;
	}
	
}
