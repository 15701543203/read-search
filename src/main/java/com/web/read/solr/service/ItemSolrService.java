package com.web.read.solr.service;

import com.web.read.common.bean.ReadResult;

public interface ItemSolrService {
	/**
	 * 把数据库数据导入到solr索引库
	 * Description: 
	 * @return
	 */
	ReadResult importItemsToSolr();
}
