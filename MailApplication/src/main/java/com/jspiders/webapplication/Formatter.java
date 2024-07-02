package com.jspiders.webapplication;

import java.text.SimpleDateFormat;

public class Formatter 
{
	public static java.sql.Date getSqlDate(String date) //logic to set the date in format
	{
		java.sql.Date sqlDate =null;
		
		try
		{
			SimpleDateFormat format= new SimpleDateFormat("YYYY-MM-DD");
			java.util.Date utilDate=format.parse(date);
			sqlDate = new java.sql.Date(utilDate.getTime());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sqlDate;
	}
	public static java.sql.Date getCurrentDate()
	{
		java.sql.Date sqlCurrentDate =null;
		try
		{
			java.util.Date utilDate=java.util.Calendar.getInstance().getTime();
			sqlCurrentDate=new java.sql.Date(utilDate.getTime());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sqlCurrentDate;
	}
}
