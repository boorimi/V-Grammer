package com.vg.bm.Member;

import java.util.ArrayList;

public class MemberDTO {
	private String m_pk;
	private String m_name;
	private String m_gen;
	private String m_birth;
	private String m_debut;
	private String m_mother_name;
	private String m_mother_twitter;
	private String m_introduce;
	private String i_icon;
	private String i_img;
	private String i_3side;
	private String i_background;
	private ArrayList<AddressDTO> address;
	private ArrayList<HashTagDTO> hashTag;

	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getM_pk() {
		return m_pk;
	}

	public void setM_pk(String m_pk) {
		this.m_pk = m_pk;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_gen() {
		return m_gen;
	}

	public void setM_gen(String m_gen) {
		this.m_gen = m_gen;
	}

	public String getM_birth() {
		return m_birth;
	}

	public void setM_birth(String m_birth) {
		this.m_birth = m_birth;
	}

	public String getM_debut() {
		return m_debut;
	}

	public void setM_debut(String m_debut) {
		this.m_debut = m_debut;
	}

	public String getM_mother_name() {
		return m_mother_name;
	}

	public void setM_mother_name(String m_mother_name) {
		this.m_mother_name = m_mother_name;
	}

	public String getM_mother_twitter() {
		return m_mother_twitter;
	}

	public void setM_mother_twitter(String m_mother_twitter) {
		this.m_mother_twitter = m_mother_twitter;
	}

	public String getM_introduce() {
		return m_introduce;
	}

	public void setM_introduce(String m_introduce) {
		this.m_introduce = m_introduce;
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

	public ArrayList<AddressDTO> getAddress() {
		return address;
	}

	public void setAddress(ArrayList<AddressDTO> address) {
		this.address = address;
	}

	public ArrayList<HashTagDTO> getHashTag() {
		return hashTag;
	}

	public void setHashTag(ArrayList<HashTagDTO> hashTag) {
		this.hashTag = hashTag;
	}

	public MemberDTO(String m_pk, String m_name, String m_gen, String m_birth, String m_debut, String m_mother_name,
			String m_mother_twitter, String m_introduce, String i_icon, String i_img, String i_3side,
			String i_background, ArrayList<AddressDTO> address, ArrayList<HashTagDTO> hashTag) {
		super();
		this.m_pk = m_pk;
		this.m_name = m_name;
		this.m_gen = m_gen;
		this.m_birth = m_birth;
		this.m_debut = m_debut;
		this.m_mother_name = m_mother_name;
		this.m_mother_twitter = m_mother_twitter;
		this.m_introduce = m_introduce;
		this.i_icon = i_icon;
		this.i_img = i_img;
		this.i_3side = i_3side;
		this.i_background = i_background;
		this.address = address;
		this.hashTag = hashTag;
	}

	@Override
	public String toString() {
		return "MemberDTO [m_pk=" + m_pk + ", m_name=" + m_name + ", m_gen=" + m_gen + ", m_birth=" + m_birth
				+ ", m_debut=" + m_debut + ", m_mother_name=" + m_mother_name + ", m_mother_twitter=" + m_mother_twitter
				+ ", m_introduce=" + m_introduce + ", i_icon=" + i_icon + ", i_img=" + i_img + ", i_3side=" + i_3side
				+ ", i_background=" + i_background + ", address=" + address + ", hashTag=" + hashTag + "]";
	}
	
	
}
