package com.myproject.services.core.impl;

import java.util.Dictionary;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.jackrabbit.oak.commons.PropertiesUtil;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.ComponentContext;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.myproject.services.core.CommonService;


@Component(immediate=true, metatype =true)
@Service(value={CommonService.class})
@Properties({@Property(name ="Common.Service.Name",value="",label="Enter Name",description="This property will take name of the user"),
	@Property(name ="Common.Service.Name2")})

public class CommonServiceImpl  implements CommonService {
	@Reference
	ResourceResolverFactory rrf ;

	
	Logger log=LoggerFactory.getLogger(CommonServiceImpl.class);
	private String nameProperty="";
	@Override
	public void callMethod() {
		// TODO Auto-generated method stub
		log.debug("hi this is call method");
	}

	@Override
	public void displayParams() {
		log.debug("hi this is display parmas");
		// TODO Auto-generated method stub
		
	}
protected void activate(ComponentContext context)
{
	Dictionary<String, String> properties=context.getProperties();
	nameProperty=PropertiesUtil.toString(properties.get("Common.Service.Name"), "no name defined");
	log.debug("Name property is {}",nameProperty);
	log.debug("Name property is {}",PropertiesUtil.toString(properties.get("Common.Service.Name2"), "no name defined"));
}
	
}