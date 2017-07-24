package com.myproject.services.core;

import java.util.ArrayList;
import java.util.List;



/**
 * 
 *
 *
 */
public class SearchPOJO {
	
	private String searchTerm = "";
	private List<String> filterPaths = new ArrayList<String>();
	private List<String> blockedPaths = new ArrayList<String>();
	
	public void setBlockedPaths(List<String> blockedPaths) {
		this.blockedPaths = blockedPaths;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public void setFilterPaths(List<String> filterPaths) {
		this.filterPaths = filterPaths;
	}

	public List<String> getBlockedPaths() {
		return blockedPaths;
	}

	public String getSearchTerm() {
		return searchTerm;
	}
	
	public List<String> getFilterPaths() {
		return filterPaths;
	}
}
