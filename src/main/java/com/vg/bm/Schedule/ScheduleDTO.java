package com.vg.bm.Schedule;

public class ScheduleDTO {

	private String pk;
	private String m_pk;
	private String id;
	private String date;
	private String time;
	private String title;

	public ScheduleDTO() {
		// TODO Auto-generated constructor stub
	}

	public ScheduleDTO(String pk, String m_pk, String id, String date, String time, String title) {
		super();
		this.pk = pk;
		this.m_pk = m_pk;
		this.id = id;
		this.date = date;
		this.time = time;
		this.title = title;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ScheduleDTO [pk=" + pk + ", m_pk=" + m_pk + ", id=" + id + ", date=" + date + ", time=" + time
				+ ", title=" + title + "]";
	}

}
