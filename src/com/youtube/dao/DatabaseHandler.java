package com.youtube.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler 
{
	private static Connection DatabaseConnection = null;
	
	public static Connection connect()
	{	
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			DatabaseConnection = DriverManager.getConnection("jdbc:mysql://localhost/youtube", "root", "");
			return DatabaseConnection;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean close()
	{
		try
		{
			if(!DatabaseConnection.isClosed())
			{
				DatabaseConnection.close();
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
