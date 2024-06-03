package com.vg.bm.Member;

public class MemberDTO {
	private String m_pk;
	private String m_name;
	private String m_gen;
	private String m_birth;
	private String m_debut;
	private String m_mother_name;
	private String m_mother_twitter;
	
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

	public MemberDTO(String m_pk, String m_name, String m_gen, String m_birth, String m_debut, String m_mother_name,
			String m_mother_twitter) {
		super();
		this.m_pk = m_pk;
		this.m_name = m_name;
		this.m_gen = m_gen;
		this.m_birth = m_birth;
		this.m_debut = m_debut;
		this.m_mother_name = m_mother_name;
		this.m_mother_twitter = m_mother_twitter;
	}

	@Override
	public String toString() {
		return "MemberDTO [m_pk=" + m_pk + ", m_name=" + m_name + ", m_gen=" + m_gen + ", m_birth=" + m_birth
				+ ", m_debut=" + m_debut + ", m_mother_name=" + m_mother_name + ", m_mother_twitter=" + m_mother_twitter
				+ "]";
	}
	
	
}
