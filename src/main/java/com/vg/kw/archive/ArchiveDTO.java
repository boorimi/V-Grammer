package com.vg.kw.archive;

import java.sql.Date;

public class ArchiveDTO {
private int a_pk;
private int a_m_pk;
private Date a_date;
private Date a_time;
private String a_collabo;
private String a_collabomember;
private String a_category;
private String a_title;
private String a_thumbnail;

public ArchiveDTO() {
	// TODO Auto-generated constructor stub
}

public ArchiveDTO(int a_pk, int a_m_pk, Date a_date, Date a_time, String a_collabo, String a_collabomember,
		String a_category, String a_title, String a_thumbnail) {
	super();
	this.a_pk = a_pk;
	this.a_m_pk = a_m_pk;
	this.a_date = a_date;
	this.a_time = a_time;
	this.a_collabo = a_collabo;
	this.a_collabomember = a_collabomember;
	this.a_category = a_category;
	this.a_title = a_title;
	this.a_thumbnail = a_thumbnail;
}

public int getA_pk() {
	return a_pk;
}

public void setA_pk(int a_pk) {
	this.a_pk = a_pk;
}

public int getA_m_pk() {
	return a_m_pk;
}

public void setA_m_pk(int a_m_pk) {
	this.a_m_pk = a_m_pk;
}

public Date getA_date() {
	return a_date;
}

public void setA_date(Date a_date) {
	this.a_date = a_date;
}

public Date getA_time() {
	return a_time;
}

public void setA_time(Date a_time) {
	this.a_time = a_time;
}

public String getA_collabo() {
	return a_collabo;
}

public void setA_collabo(String a_collabo) {
	this.a_collabo = a_collabo;
}

public String getA_collabomember() {
	return a_collabomember;
}

public void setA_collabomember(String a_collabomember) {
	this.a_collabomember = a_collabomember;
}

public String getA_category() {
	return a_category;
}

public void setA_category(String a_category) {
	this.a_category = a_category;
}

public String getA_title() {
	return a_title;
}

public void setA_title(String a_title) {
	this.a_title = a_title;
}

public String getA_thumbnail() {
	return a_thumbnail;
}

public void setA_thumbnail(String a_thumbnail) {
	this.a_thumbnail = a_thumbnail;
}




	
	
}
