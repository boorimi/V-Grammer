package com.vg.bm.Member;

public class HashTagDTO {
	String h_pk;
	String h_category;
	String h_tag;
	
	public HashTagDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getH_pk() {
		return h_pk;
	}

	public void setH_pk(String h_pk) {
		this.h_pk = h_pk;
	}

	public String getH_category() {
		return h_category;
	}

	public void setH_category(String h_category) {
		this.h_category = h_category;
	}

	public String getH_tag() {
		return h_tag;
	}

	public void setH_tag(String h_tag) {
		this.h_tag = h_tag;
	}

	public HashTagDTO(String h_pk, String h_category, String h_tag) {
		super();
		this.h_pk = h_pk;
		this.h_category = h_category;
		this.h_tag = h_tag;
	}

	@Override
	public String toString() {
		return "HashTagDTO [h_pk=" + h_pk + ", h_category=" + h_category + ", h_tag=" + h_tag + "]";
	}
	
	
	
}
