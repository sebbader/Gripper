package edu.kit.aifb.step.gripper.web.resources;


import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.semanticweb.yars.nx.Node;
import org.semanticweb.yars.nx.Resource;
import org.semanticweb.yars.nx.namespace.LDP;

import com.google.common.collect.Lists;

import edu.kit.aifb.step.api.SemanticStateBasedResource;
import edu.kit.aifb.step.gripper.backend.GripperController;
import edu.kit.aifb.step.web.api.WebResource;
import edu.kit.aifb.step.utils.Utils;

public class GripperWebContainer implements WebResource {

	private GripperController controller;
	private List<WebResource> childs;

	private MotorWebContainer arm;
	private MotorWebContainer claw;

	private String baseUri;

	/**
	 * 
	 * @param gripperController
	 * @param baseURI
	 * @param accept
	 */
	public GripperWebContainer(GripperController gripperController, String baseURI, String accept) {
		this.baseUri = baseURI;
		
		controller = new GripperController("gripper", baseURI);
		
//		for (SemanticStateBasedResource child : controller.contains()) {
//			childs.add(new MotorWebContainer(child.getId, baseURI));
//		}

		arm = new MotorWebContainer("arm", baseURI);
		claw = new MotorWebContainer("claw", baseURI);

	}

	public Response doGet() {

		try {
			List<Node[]> graph = Lists.newArrayList( controller.read() );

			if (arm != null) graph.add( new Node[] {new Resource(baseUri), LDP.CONTAINS, new Resource(baseUri + "arm/")} );
			if (claw != null) graph.add( new Node[] {new Resource(baseUri), LDP.CONTAINS, new Resource(baseUri + "claw/")} );
			
			return Response.ok().entity( new GenericEntity<Iterable<Node[]>>( graph ) {  } ).build();
		} catch (RemoteException | NullPointerException e) {
			e.printStackTrace();
			return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity(e.getMessage()).build();
		}
	}

	public Response doPost(Iterable<Node[]> input) {

		return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
	}

	public Response doPut(Iterable<Node[]> input) {

		return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
	}

	public Response doDelete() {

		return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
	}

	public Response doOptions() {

		return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
	}

	public Response doHead() {

		return doGet();
	}

	
	@Path("{child}")
	public WebResource retrieve(@PathParam("child") String child) {

		if (child.toLowerCase().contains("arm")) {
			return this.arm;
		} else if (child.toLowerCase().contains("claw")) {
			return this.claw;
		} else {
			return null;
		}
	}

}
