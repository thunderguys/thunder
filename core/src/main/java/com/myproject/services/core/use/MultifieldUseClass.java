package com.myproject.services.core.use;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import org.apache.sling.api.SlingHttpServletRequest;
import com.adobe.cq.sightly.WCMUse;
public class MultifieldUseClass extends WCMUse {
	Node n1=null;
	Map m1 = new HashMap<String,String>();
	@Override
	public void activate() throws Exception {
		n1 = getResourceResolver().resolve("/content/fashion/about1/jcr:content/par/multifield").adaptTo(Node.class);
		PropertyIterator n2  = n1.getProperties();
		
		while(n2.hasNext()){
			 Property tmp = n2.nextProperty();
			m1.put(tmp.getName(), tmp.getValue());
		}
	}
		 public Map getNode1() throws RepositoryException{
			 return m1;	
		}
		
	}


