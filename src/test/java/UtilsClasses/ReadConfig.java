package UtilsClasses;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig
{
	Properties Prop;
	
	public ReadConfig ()
	{
		File filesrc = new File ("./00Configurations\\config.properties");
		try {
			FileInputStream file = new FileInputStream (filesrc);
			Prop = new Properties();
			Prop.load(file);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception is"+e.getMessage());
		}
	}

	public String getbaseURL()
	{
		String url = Prop.getProperty("baseURL");
		return url;
	}
	
	public String getUserName()
	{
		String url = Prop.getProperty("UserName");
		return url;
	}
	
	public String getPassword()
	{
		String url = Prop.getProperty("Password");
		return url;
	}

	public String getchromePath()
	{
		String chpath = Prop.getProperty("chromePath");
		return chpath;
	}
	
	public String getiePath()
	{
		String iepath = Prop.getProperty("iePath");
		return iepath;
	}
	
	public String getffPath()
	{
		String ffpath = Prop.getProperty("firefoxPath");
		return ffpath;
	}
	
	public String getTDPath()
	{
		String tdpath = Prop.getProperty("TDPath");
		return tdpath;
	}
}
