package com.myproject.services.core.use;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;

public class Headernavigation extends WCMUse {
	Map<String,List<String>> nav = new HashMap<String,List<String>>();

	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
	String navPath = getCurrentStyle().get("navigation",String.class);
	if(null!=navPath)
		{
		Page parentPage = getResourceResolver().resolve(navPath).adaptTo(Page.class);
		if(null!=parentPage)
			{
				Iterator<Page> Children = parentPage.listChildren();
				while(Children.hasNext())
				{
					Page nextPage = Children.next();
					List<String> props = new ArrayList<String>();
					props.add(nextPage.getTitle());
					nav.put(nextPage.getPath(), props);
				}
			}
		}
	}
	public Map<String,List<String>> getNav(){
		return nav;
	}

}
