package com.myproject.services.core.use;
import java.util.ArrayList;
import java.util.List;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;
public class TestOne extends WCMUse{
	private static final Logger log = LoggerFactory.getLogger(TestOne.class);
	Node n1=null;
	List l1 = new ArrayList<String>();
	@Override
	public void activate() throws Exception {
	SlingHttpServletRequest requst = getRequest();
	log.debug("SlingHttpServletRequest:{}",requst);
	//asdfghjklmasdfgh
	log.debug("SlingHttpServletRequest:{}","--------------------------------------------");
	n1 = getResourceResolver().resolve("/etc/tags/geometrixx-outdoors/gender").adaptTo(Node.class);
	log.debug("SlingHttpServletRequest:{}",n1);
	NodeIterator n2 = n1.getNodes();
	while(n2.hasNext()){
		 Node tmp = n2.nextNode();
		l1.add(tmp.getName());
	}
}
	 public List getNode1() throws RepositoryException{
		 return l1;	
	}

}
