package com.vg.sw.calendar;

import java.sql.Date;

public class CalenderInfoDTO {
	private String m_pk;
	private String m_name;
	private Date m_debut;
	public CalenderInfoDTO() {
		// TODO Auto-generated constructor stub
	}
	public CalenderInfoDTO(String m_pk, String m_name, Date m_debut) {
		super();
		this.m_pk = m_pk;
		this.m_name = m_name;
		this.m_debut = m_debut;
	}
	public Date getM_debut() {
		return m_debut;
	}
	public void setM_debut(Date m_debut) {
		this.m_debut = m_debut;
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
	@Override
	public String toString() {
		return "CalenderInfoDTO [m_pk=" + m_pk + ", m_name=" + m_name + ", m_debut=" + m_debut + "]";
	}
	 
	
}
