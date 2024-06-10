package com.vg.kw.main;

public class GetRecommendVtuberDTO {

	private String pk;
	private String archive_pk;
	private String address_pk;
	private String m_pk;
	private String category;
	private String address;
	private String title;
	private String videoId;
	private String icon;
	private String name;

	public GetRecommendVtuberDTO() {
		// TODO Auto-generated constructor stub
	}

	
	public String getPk() {
		return pk;
	}


	public void setPk(String pk) {
		this.pk = pk;
	}


	public GetRecommendVtuberDTO(String pk, String archive_pk, String address_pk, String m_pk, String category,
			String address, String title, String videoId, String icon, String name) {
		super();
		this.pk = pk;
		this.archive_pk = archive_pk;
		this.address_pk = address_pk;
		this.m_pk = m_pk;
		this.category = category;
		this.address = address;
		this.title = title;
		this.videoId = videoId;
		this.icon = icon;
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getArchive_pk() {
		return archive_pk;
	}

	public void setArchive_pk(String archive_pk) {
		this.archive_pk = archive_pk;
	}

	public String getAddress_pk() {
		return address_pk;
	}

	public void setAddress_pk(String address_pk) {
		this.address_pk = address_pk;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}


	@Override
	public String toString() {
		return "GetRecommendVtuberDTO [pk=" + pk + ", archive_pk=" + archive_pk + ", address_pk=" + address_pk
				+ ", m_pk=" + m_pk + ", category=" + category + ", address=" + address + ", title=" + title
				+ ", videoId=" + videoId + ", icon=" + icon + ", name=" + name + "]";
	}


}
