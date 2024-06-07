package com.vg.bm.Member;

public class ImageDTO {
	String i_m_pk;
	String i_icon;
	String i_img;
	String i_3side;
	String i_background;
	
	public ImageDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getI_m_pk() {
		return i_m_pk;
	}

	public void setI_m_pk(String i_m_pk) {
		this.i_m_pk = i_m_pk;
	}

	public String getI_icon() {
		return i_icon;
	}

	public void setI_icon(String i_icon) {
		this.i_icon = i_icon;
	}

	public String getI_img() {
		return i_img;
	}

	public void setI_img(String i_img) {
		this.i_img = i_img;
	}

	public String getI_3side() {
		return i_3side;
	}

	public void setI_3side(String i_3side) {
		this.i_3side = i_3side;
	}

	public String getI_background() {
		return i_background;
	}

	public void setI_background(String i_background) {
		this.i_background = i_background;
	}

	public ImageDTO(String i_m_pk, String i_icon, String i_img, String i_3side, String i_background) {
		super();
		this.i_m_pk = i_m_pk;
		this.i_icon = i_icon;
		this.i_img = i_img;
		this.i_3side = i_3side;
		this.i_background = i_background;
	}

	@Override
	public String toString() {
		return "ImageDTO [i_m_pk=" + i_m_pk + ", i_icon=" + i_icon + ", i_img=" + i_img + ", i_3side=" + i_3side
				+ ", i_background=" + i_background + "]";
	}
	
	
}
