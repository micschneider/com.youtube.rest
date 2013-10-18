package com.youtube.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.DatabaseHandler;
import com.youtube.util.ToJSON;

@Path("/v1/inventory")
public class V1_inventory 
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String returnAllPcParts()
	{
		PreparedStatement query = null;
		Connection conn = null;
		String returnString = "";
		
		try
		{
			conn = DatabaseHandler.connect();
			query = conn.prepareStatement("SELECT * FROM pc_parts");
			
			ResultSet rs = query.executeQuery();
			
			ToJSON converter = new ToJSON();
			JSONArray jsonArr = new JSONArray();
			
			jsonArr = converter.toJSONArray(rs);
			query.close();
			
			returnString = jsonArr.toString();
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
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}	
		
		return returnString;
	}
}
