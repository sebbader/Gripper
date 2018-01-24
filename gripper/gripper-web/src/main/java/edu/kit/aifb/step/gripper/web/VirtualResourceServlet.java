package edu.kit.aifb.step.gripper.web;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.semanticweb.yars.nx.Literal;
import org.semanticweb.yars.nx.Node;
import org.semanticweb.yars.nx.Resource;
import org.semanticweb.yars.nx.namespace.LDP;
import org.semanticweb.yars.nx.namespace.RDF;
import org.semanticweb.yars.nx.namespace.RDFS;
import org.semanticweb.yars.nx.namespace.XSD;

import com.google.common.collect.Lists;

import edu.kit.aifb.step.gripper.backend.GripperController;
import edu.kit.aifb.step.gripper.web.resources.GripperWebContainer;
import edu.kit.aifb.step.web.api.WebResource;
import edu.kit.aifb.step.wrapper.Utils;




@Path("/")
public class VirtualResourceServlet {


	private static WebResource gripperWebResource;


	@Context
	ServletContext _ctx;

	@Context
	UriInfo uriInfo;



	@GET
	public Response doGet(@Context UriInfo uriinfo) {
		
		List<Node[]> graph = new ArrayList<Node[]>();

		graph.add( new Node[] {new Resource(Utils.getBaseURI(uriInfo)), RDF.TYPE, LDP.RDF_SOURCE} );
		graph.add( new Node[] {new Resource(Utils.getBaseURI(uriInfo)), RDFS.LABEL, new Literal("IoT server", XSD.STRING)} );
		graph.add( new Node[] {new Resource(Utils.getBaseURI(uriInfo)), RDFS.COMMENT, new Literal("Dummy web server for the gripper project in order to show a sample virtual representation of an IoT thing.", XSD.STRING)} );
		graph.add( new Node[] {new Resource(Utils.getBaseURI(uriInfo)), LDP.CONTAINS, new Resource(Utils.getBaseURI(uriInfo) + "gripper/")} );
		
		return Response.ok().entity( new GenericEntity<Iterable<Node[]>>( graph ) {  } ).build();

	}


	@Path("/gripper")
	public WebResource root(@HeaderParam("Accept") String accept) {

		if (gripperWebResource == null) {
			gripperWebResource = new GripperWebContainer(new GripperController("gripper", Utils.getBaseURI(uriInfo)), Utils.getBaseURI(uriInfo), accept);
		}
		return gripperWebResource;
	}
}
