package com.web.read.solr.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.read.common.bean.ReadResult;
import com.web.read.common.utils.ExceptionUtil;
import com.web.read.solr.bean.Item;
import com.web.read.solr.mapper.ItemMapper;
import com.web.read.solr.service.ItemSolrService;

@Service
public class ItemSolrServiceImpl implements ItemSolrService {

	@Autowired
	private ItemMapper itemMapper;
	
	
	@Autowired
	private SolrServer solrServer;

	@Override
	public ReadResult importItemsToSolr() {

		List<Item> itemList = itemMapper.getItemList();

		try {
			for (Item item : itemList) {
				
				System.out.println(item);
				
				SolrInputDocument sid = new SolrInputDocument();
				sid.setField("id", item.getId());
				sid.setField("item_title", item.getTitle());
				sid.setField("item_sell_point", item.getSell_point());
				sid.setField("item_price", item.getPrice());
				sid.setField("item_image", item.getImage());
				sid.setField("item_category_name", item.getCategory_name());
				sid.setField("item_desc", item.getItem_desc());

				solrServer.add(sid);
			}
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
			return ReadResult.build(500, ExceptionUtil.getStackTrace(e));
		} catch (IOException e) {
			e.printStackTrace();
			return ReadResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return ReadResult.ok();
	}

}
