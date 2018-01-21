package edu.kit.aifb.step.gripper.web;



import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


import edu.kit.aifb.step.gripper.backend.GripperController;
import edu.kit.aifb.step.gripper.web.resources.GripperWebContainer;
import edu.kit.aifb.step.web.api.WebResource;
import edu.kit.aifb.step.wrapper.Utils;




@Path("/gripper")
public class VirtualResourceServlet {


	@Context
	ServletContext _ctx;
	
	@Context
	UriInfo uriInfo;
	


	@GET
	public Response doGet(@Context UriInfo uriinfo) {
		
		return Response.ok().build();

	}
	
	
	@Path("/")
	public WebResource root(@HeaderParam("Accept") String accept) {
		
		return new GripperWebContainer(new GripperController("gripper", Utils.getBaseURI(uriInfo)), Utils.getBaseURI(uriInfo), accept);
	}
}
