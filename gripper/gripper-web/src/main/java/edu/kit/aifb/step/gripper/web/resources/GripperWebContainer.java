package edu.kit.aifb.step.gripper.web.resources;

import javax.ws.rs.core.Response;

import org.semanticweb.yars.nx.Node;

import edu.kit.aifb.step.gripper.backend.GripperController;
import edu.kit.aifb.step.web.api.WebResource;

public class GripperWebContainer implements WebResource {

	/**
	 * 
	 * @param gripperController
	 * @param baseURI
	 * @param accept
	 */
	public GripperWebContainer(GripperController gripperController, String baseURI, String accept) {
		// TODO Auto-generated constructor stub
	}

	public Response doGet() {
		// TODO Auto-generated method stub
		return null;
	}

	public Response doPost(Iterable<Node[]> input) {
		// TODO Auto-generated method stub
		return null;
	}

	public Response doPut(Iterable<Node[]> input) {
		// TODO Auto-generated method stub
		return null;
	}

	public Response doDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	public Response doOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	public Response doHead() {
		// TODO Auto-generated method stub
		return null;
	}

	public WebResource retrieve(String child) {
		// TODO Auto-generated method stub
		return null;
	}

}
