package com.mdc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdc.dao.ILaughDao;
import com.mdc.service.ILaughService;
import com.mdc.util.PageUtil;
import com.mdc.view.Laugh;

@Service
@Transactional
public class LaughServiceImpl implements ILaughService {
	@Autowired
	private ILaughDao laughDao;

	@Override
	public int save(Laugh laugh) throws Exception {
		// TODO Auto-generated method stub
		return laughDao.save(laugh);
	}

	@Override
	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return laughDao.getAllMapList(map);
	}

	@Override
	public List<Laugh> getList(PageUtil<Laugh> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return laughDao.getList(page, map);
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return laughDao.delete(id);
	}

	@Override
	public int update(Laugh laugh) throws Exception {
		// TODO Auto-generated method stub
		return laughDao.update(laugh);
	}

	@Override
	public int Laugh() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
