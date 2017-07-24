package com.myproject.services.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class SSaatt implements BundleActivator{

	@Override
	public void start(BundleContext context) throws Exception {
		
		context.getBundle().start();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
