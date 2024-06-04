package com.vg.sw.dday;

public class DdayDTO {
    private int m_pk;
    private String m_name;
    private String m_debut;
    private long daysUntilDday; // 새로운 필드 추가

    public DdayDTO(int m_pk, String m_name, String m_debut, long daysUntilDday) {
        this.m_pk = m_pk;
        this.m_name = m_name;
        this.m_debut = m_debut;
        this.daysUntilDday = daysUntilDday;
    }

    // 기존 getter와 setter
    public int getM_pk() {
        return m_pk;
    }

    public void setM_pk(int m_pk) {
        this.m_pk = m_pk;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_debut() {
        return m_debut;
    }

    public void setM_debut(String m_debut) {
        this.m_debut = m_debut;
    }

    public long getDaysUntilDday() {
        return daysUntilDday;
    }

    public void setDaysUntilDday(long daysUntilDday) {
        this.daysUntilDday = daysUntilDday;
    }
}
