package com.web.read.solr.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import com.web.read.solr.bean.SearchResult;

public interface SearchDao {

	/**
	 * 
	 * Description: 
	 * @param query
	 * @return
	 * @throws SolrServerException 
	 */
	SearchResult search(SolrQuery query) throws SolrServerException;
	
}

	
