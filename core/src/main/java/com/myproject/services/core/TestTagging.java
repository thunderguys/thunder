package com.myproject.services.core;
		import com.adobe.cq.sightly.WCMUse;
        import javax.jcr.Node;
		import javax.jcr.Session; 
		import org.apache.sling.api.resource.Resource;
		import org.apache.sling.api.resource.ResourceResolver;
		
		import org.slf4j.Logger;
		import org.slf4j.LoggerFactory;
		 
		public class TestTagging extends WCMUse
		{
		 Logger log=LoggerFactory.getLogger(TestTagging.class);
		     /** The bean tagging. */
		    private BeanTagging node = null;
		      
		    @Override
		    public void activate() throws Exception {
		          Resource rs=getResource();
		          log.debug("Resource {}:", rs);
		          ResourceResolver rr=rs.getResourceResolver();
		          log.debug("ResourceResolver {}:", rr);
		          Session ss = rr.adaptTo(Session.class);
		          log.debug("Session {}:", ss);
		          Node wn=ss.getNode("/etc/tags/geometrixx-outdoors/gender");
		         log.debug("node {}:", wn);
		          node= new BeanTagging();
		        if(wn.hasNodes()){
		          node.setNode(wn);
		        }
		        Node s1=node.getNode();
		    }
		    
//		        public BeanTagging getNode() {
//		    		return node;
//		        
//		    	}
		        
		        }