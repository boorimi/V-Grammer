package com.vg.sw.dday;

public class DdayDTO implements Comparable<DdayDTO> {
    private int id;
    private String name;
    private String debutDate;
    private String birthDate;
    private long daysUntilDebutDday;
    private long daysUntilBirthDday;

    // Constructor and getters/setters

    public DdayDTO(int id, String name, String debutDate, String birthDate, long daysUntilDebutDday, long daysUntilBirthDday) {
        this.id = id;
        this.name = name;
        this.debutDate = debutDate;
        this.birthDate = birthDate;
        this.daysUntilDebutDday = daysUntilDebutDday;
        this.daysUntilBirthDday = daysUntilBirthDday;
    }

    // Getters and Setters

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

    public String getDebutDate() {
        return debutDate;
    }

    public void setDebutDate(String debutDate) {
        this.debutDate = debutDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public long getDaysUntilDebutDday() {
        return daysUntilDebutDday;
    }

    public void setDaysUntilDebutDday(long daysUntilDebutDday) {
        this.daysUntilDebutDday = daysUntilDebutDday;
    }

    public long getDaysUntilBirthDday() {
        return daysUntilBirthDday;
    }

    public void setDaysUntilBirthDday(long daysUntilBirthDday) {
        this.daysUntilBirthDday = daysUntilBirthDday;
    }

    // Comparable implementation to sort by nearest D-Day
    @Override
    public int compareTo(DdayDTO other) {
        long thisNearestDday = Math.min(daysUntilDebutDday, daysUntilBirthDday);
        long otherNearestDday = Math.min(other.daysUntilDebutDday, other.daysUntilBirthDday);
        int compareResult = Long.compare(otherNearestDday, thisNearestDday); // 내림차순 정렬을 위해 반대로 비교

        if (compareResult == 0) {
            compareResult = Long.compare(other.daysUntilBirthDday, this.daysUntilBirthDday); // 생일 기준 내림차순 정렬
        }

        return compareResult;
    }
}
