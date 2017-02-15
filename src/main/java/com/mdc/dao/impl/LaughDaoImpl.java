package com.mdc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mdc.dao.ILaughDao;
import com.mdc.util.PageUtil;
import com.mdc.view.Laugh;

@Repository
public class LaughDaoImpl implements ILaughDao {

	private JdbcTemplate jdbctemplate;

	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	public void initJdbc(DataSource dataSource) {
		namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Autowired
	public void init(DataSource dataSource) {
		jdbctemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Laugh laugh) throws Exception {
		// TODO Auto-generated method stub
		int test = this.jdbctemplate.update(
				"insert into laugh (tagid,headimgurl,nickname,userid,sex,content,up,down,createtime) values (?,?,?,?,?,?,?,?,?)",
				laugh.getTagid(), laugh.getHeadimgurl(), laugh.getNickname(), laugh.getUserid(), laugh.getSex(),
				laugh.getContent(), laugh.getUp(), laugh.getDown(), laugh.getCreatetime());
		return test;
	}

	@Override
	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select id,tagid,headimgurl,nickname,userid,sex,content,up,down,createtime from laugh where 1=1 ");
		List<Map<String, Object>> actors = this.namedJdbcTemplate.query(sb.toString(), new HashMap<String, Object>(),
				new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", rs.getString("id"));
						map.put("tagid", rs.getString("tagid"));
						map.put("headimgurl", rs.getString("headimgurl"));
						map.put("nickname", rs.getString("nickname"));
						map.put("userid", rs.getString("userid"));
						map.put("sex", rs.getString("sex"));
						map.put("content", rs.getString("content"));
						map.put("up", rs.getString("up"));
						map.put("down", rs.getString("down"));
						map.put("createtime", rs.getTimestamp("createtime"));
						return map;
					}
				});
		return actors;
	}

	@Override
	public List<Laugh> getList(PageUtil<Laugh> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringBuilder countsb = new StringBuilder();
		Map<String, Object> searchMap = new HashMap<String, Object>();
		Map<String, Object> countMap = new HashMap<String, Object>();
		sb.append("select id,tagid,headimgurl,nickname,userid,sex,content,up,down,createtime from laugh where 1=1 ");
		countsb.append("select count(id) from laugh where 1=1");
		sb.append(" order by createtime desc ");
		sb.append("limit :start,:end ");
		searchMap.put("start", page.getFirst() - 1);
		searchMap.put("end", page.getPageSize());

		List<Laugh> actors = this.namedJdbcTemplate.query(sb.toString(), searchMap, new RowMapper<Laugh>() {
			public Laugh mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Laugh laugh = new Laugh();
				laugh.setId(rs.getLong("id"));
				laugh.setTagid(rs.getString("tagid"));
				laugh.setHeadimgurl(rs.getString("headimgurl"));
				laugh.setNickname(rs.getString("nickname"));
				laugh.setUserid(rs.getString("userid"));
				laugh.setSex(rs.getString("sex"));
				laugh.setContent(rs.getString("content"));
				laugh.setUp(rs.getString("up"));
				laugh.setDown(rs.getString("down"));
				laugh.setCreatetime(rs.getTimestamp("createtime"));
				return laugh;
			}
		});
		Integer total = this.namedJdbcTemplate.queryForObject(countsb.toString(), countMap, Integer.class);
		page.setTotalCount(total);
		return actors;
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("delete from laugh where id=?", id);
	}

	@Override
	public int update(Laugh laugh) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"update laugh set tagid=?,headimgurl=?,nickname=?,userid=?,sex=?,content=?,up=?,down=?,createtime=? where id=?",
				laugh.getTagid(), laugh.getHeadimgurl(), laugh.getNickname(), laugh.getUserid(), laugh.getSex(),
				laugh.getContent(), laugh.getUp(), laugh.getDown(), laugh.getCreatetime());
	}

	@Override
	public int countKateByTagid(String tagid) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder countsb = new StringBuilder();
		Map<String, Object> countMap = new HashMap<String, Object>();
		countsb.append(" select count(id) from laugh where tagid=:tagid ");
		countMap.put("tagid", tagid);
		Integer total = this.namedJdbcTemplate.queryForObject(countsb.toString(), countMap, Integer.class);
		return total;
	}

}
