package com.vg.sw.calendar;

import java.sql.Date;

public class CalenderInfoDTO {
	private String m_pk;
	private String title;
	private String start;
	
	
	
	public CalenderInfoDTO() {
		// TODO Auto-generated constructor stub
	}



	public CalenderInfoDTO(String m_pk, String title, String start) {
		super();
		this.m_pk = m_pk;
		this.title = title;
		this.start = start;
	}



	public String getM_pk() {
		return m_pk;
	}



	public void setM_pk(String m_pk) {
		this.m_pk = m_pk;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getStart() {
		return start;
	}



	public void setStart(String start) {
		this.start = start;
	}



	@Override
	public String toString() {
		return "CalenderInfoDTO [m_pk=" + m_pk + ", title=" + title + ", start=" + start + "]";
	}
	 
	
}
