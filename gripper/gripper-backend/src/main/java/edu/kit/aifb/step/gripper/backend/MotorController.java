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
import edu.kit.aifb.step.vocabs.STEP;

public class MotorController implements SemanticStateBasedResource {

	private String baseUri;
	private String id;
	private int state;
	private int numberOfActions;

	/**
	 * 
	 * @param id
	 * @param baseUri
	 * @param status
	 */
	public MotorController(String id, String baseUri, int status) {

		this.id = id;

		int l = baseUri.split("/").length;
		for (int i = 0; i < l-1 ; i++) this.baseUri += baseUri.split("/")[i] + "/";
		this.baseUri += id + "/";

		this.state = status;
		this.numberOfActions = 0;
	}


	public Iterable<Node[]> read() throws RemoteException {
		List<Node[]> graph = new ArrayList<Node[]>();

		Resource motorController = new Resource(baseUri);
		graph.add( new Node[] {motorController, RDFS.LABEL, new Literal(id, XSD.STRING)} );
		graph.add( new Node[] {motorController, SAREF.hasState, new Literal( readState(), XSD.STRING)} );
		graph.add( new Node[] {motorController, STEP.numberOfRequests, new Literal(Integer.toString(numberOfActions), XSD.INTEGER)} );

		return graph;
	}


	private String readState() throws RemoteException {

		switch (this.id) {
		case "arm":
			if (state == 1) return "up";
			if (state == 0) return "down";
			break;
		case "claw":
			if (state == 1) return "opened";
			if (state == 0) return "closed";
			break;
		default:
			throw new RemoteException();
		}
		return null;
	}


	public Iterable<Node[]> readDescription() {
		List<Node[]> graph = new ArrayList<Node[]>();

		Resource motorController = new Resource(baseUri);
		graph.add( new Node[] {motorController, RDF.TYPE, new Resource("http://ssn#actuator")} );
		graph.add( new Node[] {motorController, RDF.TYPE, new Resource("http://people.aifb.kit.edu/mu2771/step#gripper_motor")} );

		return graph;
	}

	public boolean update(Iterable<Node[]> nodes) throws RemoteException {

		try {
			for (Node[] node : nodes) {

				if (node[1].getLabel().toLowerCase().contains("hasstate")) {
					try {

						int newState = translateState(node[2].getLabel());

						if (newState != 0 && newState != 1) throw new RemoteException("Only 1 and 0 allowed!");

						if (this.state < newState) {
							// transition from 0 to 1
							move(0, 0, 1);
							this.state = 1;
							this.numberOfActions++;
						} else if (this.state > newState) {
							// transition from 0 to 1
							move(0, 0, -1);
							this.state = 0;
							this.numberOfActions++;
						} else {
							return false;
						}
					} catch (NumberFormatException e) {
						throw new RemoteException(e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}

		return true;
	}




	private int translateState(String label) throws RemoteException {

		switch (label.toLowerCase()) {
		case "up":
			return 1;
		case "down":
			return 0;
		case "closed":
			return 1;
		case "opened":
			return 0;
		case "1":
			return 1;
		case "0":
			return 0;
		default:
			throw new RemoteException("Could not parse desired state.");
		}

	}


	private void move(int i, int j, int k) {
		// TODO Auto-generated method stub

	}


	public String create(Iterable<Node[]> nodes) throws RemoteException, MissingArgumentException {
		throw new RemoteException("Create not possible!");
	}

	public boolean delete() throws RemoteException, MethodNotSupportedException {
		throw new RemoteException("Delete not possible!");
	}

	public List<SemanticStateBasedResource> contains() throws RemoteException {
		return new ArrayList<SemanticStateBasedResource>() ;
	}

	public SemanticStateBasedResource retrieve(String id) throws RemoteException {
		return null;
	}

}
