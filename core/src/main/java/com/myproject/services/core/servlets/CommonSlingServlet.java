package com.myproject.services.core.servlets;

import java.io.IOException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myproject.services.core.impl.CommonServiceImpl;

@SlingServlet(methods={"post"},paths="/bin/myservlet",extensions="xml")

public class CommonSlingServlet extends SlingAllMethodsServlet {
	Logger log=LoggerFactory.getLogger(CommonServiceImpl.class);

	private static final long serialVersionUID = 1L;
	
	@Override
protected void doGet(SlingHttpServletRequest request ,SlingHttpServletResponse response) throws IOException{
		log.debug("this is get method called");
		doPost(request, response);
	}
	@Override
	protected void doPost(SlingHttpServletRequest request ,SlingHttpServletResponse response) throws IOException{
		log.debug("this is post method called");
		response.getWriter().print("this is my servlet");
	}

}
