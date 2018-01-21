package edu.kit.aifb.step.gripper.backend;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.commons.cli.MissingArgumentException;
import org.apache.http.MethodNotSupportedException;
import org.semanticweb.yars.nx.Node;

import edu.kit.aifb.step.api.SemanticStateBasedResource;

public class GripperController implements SemanticStateBasedResource {
	
	private String baseUri;
	private String id;
	
	private MotorController arm;
	private MotorController claw;
	
	/**
	 * 
	 * @param id
	 * @param baseUri
	 */
	public GripperController (String id, String baseUri) {
		this.baseUri = baseUri;
		this.id = id;

		this.arm = new MotorController("arm", baseUri, 0);
		this.claw = new MotorController("claw", baseUri, 0);
	}

	public Iterable<Node[]> read() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Node[]> readDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(Iterable<Node[]> nodes) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public String create(Iterable<Node[]> nodes) throws RemoteException, MissingArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delete() throws RemoteException, MethodNotSupportedException {
		// TODO Auto-generated method stub
		return false;
	}

	public List<SemanticStateBasedResource> contains() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public SemanticStateBasedResource retrieve(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
