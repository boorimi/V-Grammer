package com.vg.sw.dday;

public class DdayDTO {
    private int id;
    private String m_name;
    private String m_debut;

    public DdayDTO(int id, String name, String debut) {
        this.id = id;
        this.m_name = name;
        this.m_debut = debut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        this.m_name = name;
    }

    public String getDebut() {
        return m_debut;
    }

    public void setDebut(String debut) {
        this.m_debut = debut;
    }
}
