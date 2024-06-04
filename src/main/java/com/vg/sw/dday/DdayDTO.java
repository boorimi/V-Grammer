package com.vg.sw.dday;

public class DdayDTO {
    private int m_id;
    private String m_name;
    private String m_debut;

    public DdayDTO(int m_id, String m_name, String m_debut) {
        this.m_id = m_id;
        this.m_name = m_name;
        this.m_debut = m_debut;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
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
}
