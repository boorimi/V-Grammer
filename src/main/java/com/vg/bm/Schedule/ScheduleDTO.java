package com.vg.bm.Schedule;

public class ScheduleDTO {

	private String s_pk;
	private String s_m_pk;
	private String s_u_t_id;
	private String s_date;
	private String s_time;
	private String s_title;
	private String m_name;

	public ScheduleDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getS_pk() {
		return s_pk;
	}

	public void setS_pk(String s_pk) {
		this.s_pk = s_pk;
	}

	public String getS_m_pk() {
		return s_m_pk;
	}

	public void setS_m_pk(String s_m_pk) {
		this.s_m_pk = s_m_pk;
	}

	public String getS_u_t_id() {
		return s_u_t_id;
	}

	public void setS_u_t_id(String s_u_t_id) {
		this.s_u_t_id = s_u_t_id;
	}

	public String getS_date() {
		return s_date;
	}

	public void setS_date(String s_date) {
		this.s_date = s_date;
	}

	public String getS_time() {
		return s_time;
	}

	public void setS_time(String s_time) {
		this.s_time = s_time;
	}

	public String getS_title() {
		return s_title;
	}

	public void setS_title(String s_title) {
		this.s_title = s_title;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public ScheduleDTO(String s_pk, String s_m_pk, String s_u_t_id, String s_date, String s_time, String s_title,
			String m_name) {
		super();
		this.s_pk = s_pk;
		this.s_m_pk = s_m_pk;
		this.s_u_t_id = s_u_t_id;
		this.s_date = s_date;
		this.s_time = s_time;
		this.s_title = s_title;
		this.m_name = m_name;
	}

	@Override
	public String toString() {
		return "ScheduleDTO [s_pk=" + s_pk + ", s_m_pk=" + s_m_pk + ", s_u_t_id=" + s_u_t_id + ", s_date=" + s_date
				+ ", s_time=" + s_time + ", s_title=" + s_title + ", m_name=" + m_name + "]";
	}
	
	
}
