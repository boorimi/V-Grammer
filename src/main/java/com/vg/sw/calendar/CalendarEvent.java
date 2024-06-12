package com.vg.sw.calendar;

import java.sql.Date;

public class CalendarEvent {
    private int id;
    private String name;
    private Date debutDate;

    public CalendarEvent(int id, String name, Date debutDate) {
        this.id = id;
        this.name = name;
        this.debutDate = debutDate;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDebutDate() {
		return debutDate;
	}

	public void setDebutDate(Date debutDate) {
		this.debutDate = debutDate;
	}
    
}
