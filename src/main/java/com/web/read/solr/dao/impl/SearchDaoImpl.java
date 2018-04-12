package com.web.read.solr.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.read.solr.bean.Item;
import com.web.read.solr.bean.SearchResult;
import com.web.read.solr.dao.SearchDao;

@Component
public class SearchDaoImpl implements SearchDao {

	@Autowired
	private SolrServer solrServer;

	@Override
	public SearchResult search(SolrQuery query) throws SolrServerException {
		// 返回值对象
		SearchResult result = new SearchResult();
		// 根据查询条件查询索引库
		QueryResponse queryResponse = solrServer.query(query);
		// 取查询结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		// 取查询结果总数量
		result.setRecordCount(solrDocumentList.getNumFound());
		// 商品列表
		List<Item> itemList = new ArrayList<>();
		// 取高亮显示
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		
		//  第一个String的key代表条目的id值
		//  第二个String的key代表条目的字段的高亮信息
		// 取商品列表
		for (SolrDocument solrDocument : solrDocumentList) {
			// 创建一商品对象
			Item item = new Item();
			item.setId((String) solrDocument.get("id"));//ID
			// 取高亮显示的结果
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title = "";
			if (list != null && list.size() > 0) {
				title = list.get(0);
			} else {
				title = (String) solrDocument.get("item_title");
			}
			item.setTitle(title);//标题；如果有高亮，则显示高亮的，否则显示非高亮的
			item.setImage((String) solrDocument.get("item_image"));//图片的路径
			item.setPrice((long) solrDocument.get("item_price"));//商品价格
			item.setSell_point((String) solrDocument.get("item_sell_point"));//买点
			item.setCategory_name((String) solrDocument.get("item_category_name"));//分类名称
			// 添加的商品列表
			itemList.add(item);
		}
		result.setItemList(itemList);
		return result;
	}

}
