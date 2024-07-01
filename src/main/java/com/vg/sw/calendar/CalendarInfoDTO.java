package com.vg.sw.calendar;

public class CalendarInfoDTO {
    private String m_pk; // 멤버의 pk
    private String id; // 공휴일의 id
    private String title;
    private String start;
    private String end; // end 필드 추가
    private String imagePath; // imagePath 필드 추가

    public CalendarInfoDTO() {}

    public CalendarInfoDTO(String m_pk, String id, String title, String start, String end, String imagePath) {
        this.m_pk = m_pk;
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.imagePath = imagePath;
    }

    // Getters and Setters
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

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "CalendarInfoDTO [m_pk=" + m_pk + ", id=" + id + ", title=" + title + ", start=" + start + ", end=" + end + ", imagePath=" + imagePath + "]";
    }
}
