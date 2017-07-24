package com.myproject.services.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;





@SlingServlet(resourceTypes = {"fashion/select/datasource"}, methods = {"GET" })
public class DatasourceServlet extends SlingSafeMethodsServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1659759062357085158L;

	


	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		List<Resource> resourceList = new ArrayList<Resource>();
		 
		ValueMap vm = null;
 
		for (int i = 1; i <= 5; i++) {
			vm = new ValueMapDecorator(new HashMap<String, Object>());
			String Value = "samplevalue" + i;
			String Text = "Sample Text " + i;
			vm.put("value", Value);
			vm.put("text", Text);
 
			resourceList.add(new ValueMapResource(request.getResourceResolver(),
					new ResourceMetadata(), "nt:unstructured", vm));
		}
		
		DataSource ds = new SimpleDataSource(resourceList.iterator());
		request.setAttribute(DataSource.class.getName(), ds);
	}
}
