package com.vg.jw.mypage;

import com.google.gson.Gson;

public class GoodsDTO {
	// view에 뭘쓸지
	// db tbl
	// 필요에 따라 join
	private long u_twitter_id;
	private int g_m_pk;
	private String g_category;
	private int g_count;
	private String i_icon;
	private String m_name;
	private String m_personalcolor;

	public GoodsDTO() {
		// TODO Auto-generated constructor stub
	}
	

	public GoodsDTO(long u_twitter_id, int g_m_pk, String g_category, int g_count, String i_icon, String m_name,
			String m_personalcolor) {
		super();
		this.u_twitter_id = u_twitter_id;
		this.g_m_pk = g_m_pk;
		this.g_category = g_category;
		this.g_count = g_count;
		this.i_icon = i_icon;
		this.m_name = m_name;
		this.m_personalcolor = m_personalcolor;
	}

	

	public long getU_twitter_id() {
		return u_twitter_id;
	}


	public void setU_twitter_id(long u_twitter_id) {
		this.u_twitter_id = u_twitter_id;
	}


	public int getG_m_pk() {
		return g_m_pk;
	}


	public void setG_m_pk(int g_m_pk) {
		this.g_m_pk = g_m_pk;
	}


	public String getG_category() {
		return g_category;
	}


	public void setG_category(String g_category) {
		this.g_category = g_category;
	}


	public int getG_count() {
		return g_count;
	}


	public void setG_count(int g_count) {
		this.g_count = g_count;
	}


	public String getI_icon() {
		return i_icon;
	}


	public void setI_icon(String i_icon) {
		this.i_icon = i_icon;
	}


	public String getM_name() {
		return m_name;
	}


	public void setM_name(String m_name) {
		this.m_name = m_name;
	}


	public String getM_personalcolor() {
		return m_personalcolor;
	}


	public void setM_personalcolor(String m_personalcolor) {
		this.m_personalcolor = m_personalcolor;
	}


	@Override
	public String toString() {
		return "GoodsDTO [u_twitter_id=" + u_twitter_id + ", g_m_pk=" + g_m_pk + ", g_category=" + g_category
				+ ", g_count=" + g_count + ", i_icon=" + i_icon + ", m_name=" + m_name + ", m_personalcolor="
				+ m_personalcolor + "]";
	}


	public String toJSON() {
		Gson g = new Gson();
		return g.toJson(this);
	}

}
