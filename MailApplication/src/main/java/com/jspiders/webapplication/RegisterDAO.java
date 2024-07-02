package com.jspiders.webapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class RegisterDAO 
{
	 public static int save(String name,String email,String password,String gender, String dob,String address, String city,String state, String country,String contact)
	 {
		 int status=0;
		java.sql.Date sqlDOB = Formatter.getSqlDate(dob); 
		try
		{
			
			Connection con = DM.getConnection();		
			PreparedStatement psmt = con.prepareStatement( "INSERT INTO COMPANY_MAIL_USER(NAME,EMAIL,PASSWORD,GENDER,DOB,ADDRESS,CITY,"
					+ "STATE,COUNTRY,CONTACT,REGISTEREDDATE,AUTHORIZED) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			psmt.setString(1, name);
			psmt.setString(2, email);
			psmt.setString(3, password);
			psmt.setString(4, gender);
			psmt.setDate(5, sqlDOB);
			psmt.setString(6, address);
			psmt.setString(7, city );
			psmt.setString(8, state);
			psmt.setString(9, country);
			psmt.setString(10, contact);
			psmt.setDate(11, Formatter.getCurrentDate());
			psmt.setString(12, "YES");
			
			status = psmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	 }

}
