package com.web.read.solr.server_test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrTest {

	@Test
	public void adddDocument() throws SolrServerException, IOException{
		SolrServer ss = new HttpSolrServer("http://192.168.10.10:8080/solr");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "测试商品2");
		document.addField("item_price", 54321);
		// 把文档对象写入索引库
		ss.add(document);
		// 提交
		ss.commit();
	}
	
	@Test
	public void deleteDocument() throws Exception {
		// 创建一连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.10.10:8080/solr");
		 solrServer.deleteById("test001");
//		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}
	
}
