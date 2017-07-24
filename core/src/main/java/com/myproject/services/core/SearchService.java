package com.myproject.services.core;

import org.apache.sling.api.resource.ResourceResolver;

import com.myproject.services.core.SearchPOJO;
import com.day.cq.search.result.SearchResult;

/**
 * 
 *
 *
 */
public interface SearchService {
	public  SearchResult getSearchResult(SearchPOJO pojo, ResourceResolver resolver);
}