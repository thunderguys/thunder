package com.myproject.services.core.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SlingServlet(
resourceTypes = "sling/servlet/default12345",
extensions = "html",
methods = "GET")
public class CustomResourceTypeServlet extends SlingSafeMethodsServlet {
	private static final long serialVersionUID = 7986362929224786961L;
	Logger log=LoggerFactory.getLogger(CustomResourceTypeServlet.class);
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
	throws ServletException, IOException {
	log.debug("123456789");
	response.getWriter().print("this is my servlet CustomResourceTypeServle ");

	}

}
