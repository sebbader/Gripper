package edu.kit.aifb.step.gripper.web.resources;

import java.lang.annotation.Retention;
import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.semanticweb.yars.nx.Node;
import org.semanticweb.yars.nx.Resource;
import org.semanticweb.yars.nx.namespace.LDP;

import com.google.common.collect.Lists;

import edu.kit.aifb.step.gripper.backend.GripperController;
import edu.kit.aifb.step.gripper.backend.MotorController;
import edu.kit.aifb.step.web.api.WebResource;
import edu.kit.aifb.step.wrapper.Utils;

public class MotorWebContainer implements WebResource {
	

	private MotorController controller;

	private String baseUri;
	private String id;
	
	
	public MotorWebContainer(String id, String baseUri) {
		
		this.id = id;
		this.baseUri = baseUri;
		this.controller = new MotorController(id, baseUri, 0);
	}

	@Override
	public Response doGet() {

		try {
			List<Node[]> graph = Lists.newArrayList( controller.read() );
			
			graph.addAll( Lists.newArrayList( controller.readDescription() ) );

			return Response.ok().entity( new GenericEntity<Iterable<Node[]>>( graph ) {  } ).variants(Utils.getVariants()).build();
			
		} catch (RemoteException e) {
			e.printStackTrace();
			return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity(e.getMessage()).build();
		}

	}

	@Override
	public Response doPost(Iterable<Node[]> input) {
		return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
	}

	@Override
	public Response doPut(Iterable<Node[]> input) {
		
		try {
			if (controller.update(input)) {
				return Response.ok().build();
			} else {
				return Response.status( Response.Status.BAD_REQUEST ).build();
			}
		} catch (Exception e) {
			return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response doDelete() {
		return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
	}

	@Override
	public Response doOptions() {
		return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
	}

	@Override
	public Response doHead() {
		return Response.ok().entity( new GenericEntity<Iterable<Node[]>>( controller.readDescription() ) {  } ).variants(Utils.getVariants()).build();
	}

	@Override
	public WebResource retrieve(String child) {
		return null;
	}

}
