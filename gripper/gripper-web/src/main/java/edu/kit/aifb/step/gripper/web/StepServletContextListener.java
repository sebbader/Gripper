package edu.kit.aifb.step.gripper.web;

import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import edu.kit.aifb.step.web.api.WebResource;

@WebListener
public class StepServletContextListener implements ServletContextListener {

	final static Logger _log = Logger.getLogger(StepServletContextListener.class
			.getName());

	ServletContext _ctx;
	
	private WebResource vr;


	@Override 
	public void contextInitialized(ServletContextEvent sce) {

		_ctx = sce.getServletContext();

		// Register Servlet
		ServletRegistration sr = _ctx.addServlet("gripper",
				org.glassfish.jersey.servlet.ServletContainer.class);
		sr.addMapping("/*");
		sr.setInitParameter(org.glassfish.jersey.server.ServerProperties.PROVIDER_PACKAGES,
				this.getClass().getPackage().getName());
		
		//_ctx.setAttribute("vr", new FlsVisitourContainer());


	}


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public WebResource getVirtualResource() {
		return vr;
	}
	
	
}
