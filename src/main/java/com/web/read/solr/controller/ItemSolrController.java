package com.web.read.solr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.read.common.bean.ReadResult;
import com.web.read.solr.service.ItemSolrService;

@Controller
@RequestMapping("/manager")
public class ItemSolrController {
	
	@Autowired
	private ItemSolrService itemSolrService;
	
	
	/**
	 * 
	 * Description: 
	 * @return
	 */
	@RequestMapping("/importall")
	@ResponseBody
	public ReadResult importItemsToSolr(){
		ReadResult result = itemSolrService.importItemsToSolr();
		return result;
	}

}
