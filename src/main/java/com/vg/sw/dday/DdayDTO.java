package com.vg.sw.dday;

import java.time.LocalDate;

public class DdayDTO {
	private int id;
    private String name;
    private String eventType; // "데뷔" 또는 "생일"
    private String eventDate;
    private LocalDate localEventDate;
    private long daysUntilDday;

    public DdayDTO(int id, String name, String eventType, String eventDate, LocalDate localEventDate, long daysUntilDday) {
        this.id = id;
        this.name = name;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.localEventDate = localEventDate;
        this.daysUntilDday = daysUntilDday;
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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public LocalDate getLocalEventDate() {
        return localEventDate;
    }

    public void setLocalEventDate(LocalDate localEventDate) {
        this.localEventDate = localEventDate;
    }

    public long getDaysUntilDday() {
        return daysUntilDday;
    }

    public void setDaysUntilDday(long daysUntilDday) {
        this.daysUntilDday = daysUntilDday;
    }
}
