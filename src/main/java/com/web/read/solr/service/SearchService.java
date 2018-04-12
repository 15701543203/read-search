package com.web.read.solr.service;

import org.apache.solr.client.solrj.SolrServerException;

import com.web.read.solr.bean.SearchResult;

public interface SearchService {
	

	/**
	 * 搜索
	 * Description: 
	 * @param queryString 查询条件
	 * @param page 几页
	 * @param rows 几条
	 * @return
	 * @throws SolrServerException
	 */
	SearchResult search(String queryString, int page, int rows) throws SolrServerException;
	
}
