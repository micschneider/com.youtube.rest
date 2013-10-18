package com.youtube.rest.inventory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.Schema;

@Path("/v2/inventory")
public class V2_inventory 
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrandParts(@QueryParam("brand") String brand)
	{	
		String returnString = "";
		JSONArray jsonArr = new JSONArray();
		
		try
		{
			if(brand == null)
				return Response.status(400).entity("Please specify brand for this search").build();
			Schema schema = new Schema();
			jsonArr = schema.queryReturnBrandParts(brand);
			returnString = jsonArr.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return Response.ok(returnString).build();
	}
	
	@Path("/{brand}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrand(@PathParam("brand") String brand)
	{
		String returnString = null;
		JSONArray jsonArr = new JSONArray();
		
		try
		{
			Schema schema = new Schema();
			jsonArr = schema.queryReturnBrandParts(brand);
			returnString = jsonArr.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return Response.status(400).entity("Could not process your request").build();
		}
		return Response.ok(returnString).build();
	}
}
