package com.mdc.service;

import java.util.List;
import java.util.Map;

import com.mdc.util.PageUtil;
import com.mdc.view.Laugh;

public interface ILaughService {
	public int save(Laugh laugh) throws Exception;

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception;

	public List<Laugh> getList(PageUtil<Laugh> page, Map<String, Object> map) throws Exception;

	public int delete(String id) throws Exception;

	public int update(Laugh laugh) throws Exception;
	
	public int Laugh()throws Exception;
	
	public int countKateByTagid(String tagid)throws Exception;
}
