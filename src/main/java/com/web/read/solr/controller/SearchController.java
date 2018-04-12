package com.web.read.solr.controller;

import java.io.UnsupportedEncodingException;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.web.read.common.bean.ReadResult;
import com.web.read.common.utils.ExceptionUtil;
import com.web.read.solr.bean.SearchResult;
import com.web.read.solr.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
						  
	
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public ReadResult search(@RequestParam("q") String queryString,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "60") Integer rows) throws Exception{
		
		if (StringUtils.isEmpty(queryString)) {
			return ReadResult.build(400, "空搜索条件！");
		}
		SearchResult searchResult = null;
		try {
			//get请求汉字乱码
			//解决get请求的汉字乱码问题
			queryString = new String(queryString.getBytes("iso-8859-1"), "utf-8");
			
			System.out.println("read-search8083>>>>查询条件为"+queryString);
			searchResult = searchService.search(queryString, page, rows);
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return ReadResult.build(500, ExceptionUtil.getStackTrace(e));
		} catch (SolrServerException e) {
			e.printStackTrace();
			return ReadResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return ReadResult.ok(searchResult);
	}
	
}
