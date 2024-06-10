package com.vg.kw.main;

public class YoutubeIDDTO {

	private String pk;
	private String m_pk;
	private String category;
	private String address;

	public YoutubeIDDTO() {
		// TODO Auto-generated constructor stub
	}

	public YoutubeIDDTO(String pk, String m_pk, String category, String address) {
		super();
		this.pk = pk;
		this.m_pk = m_pk;
		this.category = category;
		this.address = address;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "YoutubeIDDTO [pk=" + pk + ", m_pk=" + m_pk + ", category=" + category + ", address=" + address + "]";
	}

}
