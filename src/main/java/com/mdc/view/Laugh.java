package com.mdc.view;

import java.sql.Timestamp;

public class Laugh {
	private long id;
	private String tagid;
	private String headimgurl;
	private String nickname;
	private String userid;
	private String sex;
	private String content;
	private String up;
	private String down;
	private Timestamp createtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTagid() {
		return tagid;
	}

	public void setTagid(String tagid) {
		this.tagid = tagid;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUp() {
		return up;
	}

	public void setUp(String up) {
		this.up = up;
	}

	public String getDown() {
		return down;
	}

	public void setDown(String down) {
		this.down = down;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Laugh [id=" + id + ", tagid=" + tagid + ", headimgurl=" + headimgurl + ", nickname=" + nickname
				+ ", userid=" + userid + ", sex=" + sex + ", content=" + content + ", up=" + up + ", down=" + down
				+ ", createtime=" + createtime + "]";
	}

}
