package edu.kit.aifb.step.gripper.backend;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.MissingArgumentException;
import org.apache.http.MethodNotSupportedException;
import org.semanticweb.yars.nx.Literal;
import org.semanticweb.yars.nx.Node;
import org.semanticweb.yars.nx.Resource;
import org.semanticweb.yars.nx.namespace.RDF;
import org.semanticweb.yars.nx.namespace.RDFS;
import org.semanticweb.yars.nx.namespace.XSD;

import edu.kit.aifb.step.api.SemanticStateBasedResource;
import edu.kit.aifb.step.vocabs.SAREF;

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

		this.id = id;

		int l = baseUri.split("/").length;
		for (int i = 0; i < l-1 ; i++) this.baseUri += baseUri.split("/")[i] + "/";
		this.baseUri += id + "/";

		this.arm = new MotorController("arm", baseUri, 0);
		this.claw = new MotorController("claw", baseUri, 0);
	}

	public Iterable<Node[]> read() throws RemoteException {

		return readDescription();
	}

	public Iterable<Node[]> readDescription() {
		List<Node[]> gripper_graph = new ArrayList<Node[]>();

		Node gripper_resource = new Resource(baseUri);
		gripper_graph.add(new Node[] {gripper_resource, RDF.TYPE, SAREF.Actuator});
		gripper_graph.add(new Node[] {gripper_resource, RDFS.LABEL, new Literal(id, XSD.STRING)});
		gripper_graph.add(new Node[] {gripper_resource, RDFS.SEEALSO, new Resource("http://lego.brandls.info/leg_grabber.pdf")});

		return gripper_graph;
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
		List<SemanticStateBasedResource> childs = new ArrayList<SemanticStateBasedResource>();
		childs.add(arm);
		childs.add(claw);
		return childs;
	}

	public SemanticStateBasedResource retrieve(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
