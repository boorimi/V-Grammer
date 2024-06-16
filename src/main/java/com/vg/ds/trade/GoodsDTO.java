package com.vg.ds.trade;

import com.google.gson.Gson;

public class GoodsDTO {

	private String pk;
	private String u_t_id;
	private String m_pk;
	private String category;
	private String count;
	private String name;

	public GoodsDTO() {
		// TODO Auto-generated constructor stub
	}

	public GoodsDTO(String pk, String u_t_id, String m_pk, String category, String count, String name) {
		super();
		this.pk = pk;
		this.u_t_id = u_t_id;
		this.m_pk = m_pk;
		this.category = category;
		this.count = count;
		this.name = name;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getU_t_id() {
		return u_t_id;
	}

	public void setU_t_id(String u_t_id) {
		this.u_t_id = u_t_id;
	}

	public String getM_pk() {
		return m_pk;
	}

	public void setM_pk(String m_pk) {
		this.m_pk = m_pk;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GoodsDTO [pk=" + pk + ", u_t_id=" + u_t_id + ", m_pk=" + m_pk + ", category=" + category + ", count="
				+ count + ", name=" + name + "]";
	}
	
	public String toJSON() {

		Gson g = new Gson();
		return g.toJson(this);

	}

}