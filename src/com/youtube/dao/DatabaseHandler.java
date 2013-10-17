package com.youtube.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHandler 
{
	private static Connection DatabaseConnection = null;
	
	public static Connection connect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			DatabaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube", "root", "");
			return DatabaseConnection;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static boolean close()
	{
		try
		{
			if(DatabaseConnection != null)
			{
				DatabaseConnection.close();
				return true;
			}
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
