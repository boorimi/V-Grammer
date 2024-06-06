package com.vg.kw.main;

public class GetStreamIdDTO {

	private String pk;
	private String address;
	private String icon;
	
	public GetStreamIdDTO() {
		// TODO Auto-generated constructor stub
	}

	public GetStreamIdDTO(String pk, String address, String icon) {
		super();
		this.pk = pk;
		this.address = address;
		this.icon = icon;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	
}
