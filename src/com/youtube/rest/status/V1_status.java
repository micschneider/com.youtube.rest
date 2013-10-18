package com.youtube.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.sql.*;

import com.youtube.dao.*;

@Path("/v1/status/")
public class V1_status 
{	
	private static final String api_version = "00.01.00";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle()
	{
		return "<p>Java WebService</p>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion()
	{
		return "<p>Version: " + api_version + "</p>";
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus()
	{
		PreparedStatement query = null;
		String returnString = "";
		Connection conn = null;
			
		try
		{
			conn = DatabaseHandler.connect();
			query = conn.prepareStatement("SELECT * FROM books");
			ResultSet result = query.executeQuery();
			
			while(result.next())
			{
				returnString += result.getString("author") + ": " + result.getString("name") + "<br>";
			}
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return returnString;
	}
}
