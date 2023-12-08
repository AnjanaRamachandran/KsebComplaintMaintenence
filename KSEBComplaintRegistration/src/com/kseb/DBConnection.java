package com.kseb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
	final String driver="com.mysql.cj.jdbc.Driver";
	final String url="jdbc:mysql://localhost:3306/kseb";
	final String user="root";
	final String password="mysql";
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	public Connection getConnection(){
		try	{
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,password);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
		
		
	}
	
}
