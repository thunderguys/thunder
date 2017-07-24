package com.myproject.services.core.workflow;

import java.util.Map;

import javax.jcr.LoginException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;


@Component
@Service
@Properties({@Property(name = "process.label", value= "*******my project wf")})
public class CommonServiceWorkflow implements WorkflowProcess{
	
	private static final Logger log = LoggerFactory.getLogger(CommonServiceWorkflow.class);
	@Reference
	Replicator replicator;
	@Override
	public void execute(WorkItem item, WorkflowSession wfSession, MetaDataMap map) throws WorkflowException {
		String payload =(String) item.getWorkflowData().getPayload();
	
		log.debug("payload: {}",payload);
		Session adminSession = wfSession.adaptTo(Session.class);
		try {
			Session userSession = adminSession.impersonate(new SimpleCredentials(item.getWorkflow().getInitiator(), "".toCharArray()));
			replicator.replicate(userSession, ReplicationActionType.ACTIVATE, payload);
		} catch (LoginException e) {
			log.error("LoginException:{}",e);
		} catch (RepositoryException e) {
			log.error("RepositoryException:{}",e);
		}catch (ReplicationException e) {
			log.error("ReplicationException:{}",e);
		}
		
		
	}

}