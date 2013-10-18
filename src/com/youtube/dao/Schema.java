package com.youtube.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.util.ToJSON;

public class Schema extends DatabaseHandler
{
	public JSONArray queryReturnBrandParts(String brand)
	{
		PreparedStatement query = null;
		Connection conn = null;
		JSONArray jsonArr = new JSONArray();
		ToJSON converter = new ToJSON();
		
		try
		{
			conn = DatabaseHandler.connect();
			query = conn.prepareStatement("SELECT pc_parts_pk, pc_parts_title, "
					+ "pc_parts_code, pc_parts_maker, pc_parts_avail, pc_parts_desc"
					+ " FROM pc_parts "
					+ "WHERE UPPER(pc_parts_maker) = ? ");
			query.setString(1, brand.toUpperCase());
			ResultSet rs = query.executeQuery();
			
			jsonArr = converter.toJSONArray(rs);
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
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return jsonArr;
	}
}
