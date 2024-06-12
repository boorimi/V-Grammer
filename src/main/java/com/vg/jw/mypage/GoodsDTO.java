package com.vg.jw.mypage;

public class GoodsDTO {
	private int g_m_pk;
	private String g_category;
	private int g_count;
	
	public GoodsDTO() {
		// TODO Auto-generated constructor stub
	}

	public GoodsDTO(int g_m_pk, String g_category, int g_count) {
		super();
		this.g_m_pk = g_m_pk;
		this.g_category = g_category;
		this.g_count = g_count;
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

	@Override
	public String toString() {
		return "GoodsDTO [g_m_pk=" + g_m_pk + ", g_category=" + g_category + ", g_count=" + g_count + "]";
	}
	
	
	
	
}
