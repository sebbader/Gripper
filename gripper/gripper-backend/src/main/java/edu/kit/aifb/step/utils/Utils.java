package edu.kit.aifb.step.utils;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Variant;

import org.semanticweb.yars.jaxrs.JsonLdMessageBodyReaderWriter;
import org.semanticweb.yars.jaxrs.NxMessageBodyReaderWriter;
import org.semanticweb.yars.jaxrs.RdfXmlMessageBodyWriter;


public class Utils {



	public static String getBaseURI(UriInfo uriInfo) {
		return uriInfo.getAbsolutePath().toString().endsWith("/") ? uriInfo.getAbsolutePath().toString() : uriInfo.getAbsolutePath() + "/" ;
	}


}
