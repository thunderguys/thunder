package com.myproject.services.core.impl;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myproject.services.core.CommonReferenceService;
import com.myproject.services.core.CommonService;
@Component(metatype=true,immediate=true)
@Service(value={CommonReferenceService.class })
@Properties({
	@Property(name="prop.sample",boolValue=true,label="check this to verify",description="sdfjklkn")
})
public class CommonReferenceServiceImpl implements CommonReferenceService {
	Logger log=LoggerFactory.getLogger(CommonServiceImpl.class);
	@Reference
	CommonService commonService;
@Activate
@Modified
protected void activate(){
	if(null!=commonService){
		log.debug("hi this is call method1236545");
		commonService.callMethod();
		commonService.displayParams();
	}
}
}
