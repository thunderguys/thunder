package com.myproject.services.core.use;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Style;
import com.myproject.services.core.impl.CommonServiceImpl;
public class SampleUse extends WCMUse{
	private static final Logger log = LoggerFactory.getLogger(SampleUse.class);
	private String pageValue = "";
	@Override
	public void activate() throws Exception {
	// TODO Auto-generated method stub
	CommonServiceImpl commonServiceImpl = new CommonServiceImpl();
	ValueMap properties = getProperties();
	log.debug("properties:{}",properties);
	ValueMap Pageproperties = getPageProperties();
	log.debug("Pagepropertiess:{}",Pageproperties);
	Page page =  getCurrentPage();
	log.debug("CurrentPage:{}",page);
	SlingHttpServletRequest requst = getRequest();
	log.debug("SlingHttpServletRequest:{}",requst);
	SlingHttpServletResponse response = getResponse();
	log.debug("SlingHttpServletResponse:{}",response);
	Resource resource = getResource();
	log.debug("resource {}",resource);
	ResourceResolver resourceResolver = getResourceResolver();
	log.debug("resourceResolver {}",resourceResolver);
	Design design = getCurrentDesign();
	log.debug("CurrentDesig {}",design);
	commonServiceImpl.callMethod();
	Style style = getCurrentStyle();
	log.debug("CurrentStyle {}",style);
	pageValue = page.getPath();
	    AdaaaaaaptTo(resource,Node.class);
	log.debug("Node.class {}",Node.class);
	AEMApi();
	SlingApi();
	JCRApi();
	}

	private void JCRApi() {
		// TODO Auto-generated method stub
		
	}

	private void SlingApi() {
		// TODO Auto-generated method stub
		
	}

	private void AEMApi() {
		// TODO Auto-generated method stub

	}
	
	private void AdaaaaaaptTo(Resource resource, Class<Node> class1) throws RepositoryException {
		Node resourceNode = resource.adaptTo(class1);
		log.debug("Resource path:{}",resource.getPath());
		log.debug("Node path:{}",resourceNode.getPath());
		//Node pageNode = class1.adaptTo(Node.class);
		log.debug("adding node:{}",resourceNode.addNode("testNode","nt:unstructured").getPath());
		log.debug("Node has property:{}",resourceNode.hasProperty("fileReference"));
		log.debug("Node get property:{}",resourceNode.getProperty("fileReference").getString());
		
		resourceNode.getSession().save();
		
	}
	
	public String getPageValue()
	{
		return pageValue;
	}

}
