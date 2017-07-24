package com.myproject.services.core.workflow;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

@Component
@Service
public class CommonServicePageCreationWorkflow implements WorkflowProcess{

	private static final Logger log = LoggerFactory.getLogger(CommonServicePageCreationWorkflow.class);
	
	@Property(value = "Page Creation Workflow", label = "Process.label value")
	private static final String PROCESS_LABEL = "process.label";
	
	@Reference
	ResourceResolverFactory factory;
	
	@Override
	public void execute(WorkItem item, WorkflowSession wfSession, MetaDataMap map) throws WorkflowException {
		String payload = (String) item.getWorkflowData().getPayload();
		log.debug("Page Creation PAyload: {}", payload);
		Session adminSession = wfSession.adaptTo(Session.class);
		Session userSession = null;
		ResourceResolver resolver = null;
		try {
			userSession = adminSession
					.impersonate(new SimpleCredentials(item.getWorkflow().getInitiator(), "".toCharArray()));
			resolver = getResourceResolverFromSession(userSession);
			createPage(resolver, payload, userSession.getRootNode());
		} catch (LoginException e) {
			log.error("Exception - LoginException: {}", e);
		} catch (RepositoryException e) {
			log.error("Exception - RepositoryException: {}", e);
		} catch (org.apache.sling.api.resource.LoginException e) {
			log.error("Exception - LoginException: {}", e);
		} catch (WCMException e) {
			log.error("Exception - WCMException: {}", e);
		} finally {
			if (null != resolver && resolver.isLive()) {
				resolver.close();
			}
			if (null != userSession && userSession.isLive()) {
				userSession.logout();
			}
		}
	}

	/**
	 * 
	 * @param resolver
	 * @param payload
	 * @param rootNode 
	 * @throws WCMException 
	 * @throws RepositoryException 
	 */
	private void createPage(ResourceResolver resolver, String payload, Node rootNode) throws RepositoryException, WCMException {
		if(payload.startsWith("/content/dam/fashion")) {
			String actualAssetPath = payload.substring(0, payload.lastIndexOf("jcr:content") - 1);
			log.debug("Actual Asset path: {}", actualAssetPath);
			String assetName = actualAssetPath.substring(actualAssetPath.lastIndexOf("/") + 1, actualAssetPath.lastIndexOf("."));
			actualAssetPath = actualAssetPath.replace("/content/dam", "/content");
			log.debug("After Replacing contentdam: {}", actualAssetPath);
			Node parentPageNode = createParentPages(actualAssetPath, resolver, rootNode);
			log.debug("Parent Page Created: {}", parentPageNode.getPath());
			Page actualPage = resolver.adaptTo(PageManager.class).create(parentPageNode.getPath(), assetName
					, "/apps/myproject/templates/fashionbasepage", assetName, true);
			log.debug("actualPage Created: {}", actualPage.getPath());
		}
	}

	/**
	 * 
	 * @param actualAssetPath
	 * @param resolver
	 * @param rootNode 
	 * @throws RepositoryException 
	 * @throws WCMException 
	 */
	private Node createParentPages(String actualAssetPath, ResourceResolver resolver, Node rootNode) throws RepositoryException, WCMException {
		String parentPath = actualAssetPath.substring(0, actualAssetPath.lastIndexOf("/"));
		log.debug("Parent PAth created: {}", parentPath);
		StringTokenizer tokenizer = new StringTokenizer(parentPath, "/");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Node pageNode = rootNode;
		log.debug("Page Node: {}", pageNode.getPath());
		while (tokenizer.hasMoreTokens()) {
			String nextToken = tokenizer.nextToken();
			log.debug("Processing Next Token");
			if(pageNode.hasNode(nextToken)) {
				pageNode = pageNode.getNode(nextToken);
			} else {
				Page createdPage = pageManager.create(pageNode.getPath(), nextToken
						, "/apps/myproject/templates/fashionbasepage", nextToken, true);
				pageNode = resolver.resolve(createdPage.getPath()).adaptTo(Node.class);
			}
		}
		return pageNode;
	}

	/**
	 * 
	 * @param userSession
	 * @return
	 * @throws org.apache.sling.api.resource.LoginException 
	 */
	private ResourceResolver getResourceResolverFromSession(Session userSession) throws org.apache.sling.api.resource.LoginException {
		Map<String, Object> sessionMap = new HashMap<String, Object>();
		sessionMap.put("user.jcr.session", userSession);
		return factory.getResourceResolver(sessionMap);
	}

}