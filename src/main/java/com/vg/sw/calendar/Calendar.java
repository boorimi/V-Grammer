package com.vg.sw.calendar;

public class Calendar {
    private static boolean isLeapYear;
    private static int[] lastDay;
    private static int sum;
    private static int weekDay;

    // 생성자
    public Calendar() {
    }

    // 게터와 세터
    public static boolean isLeapYear() {
        return isLeapYear;
    }

    public static void setLeapYear(boolean leapYear) {
        isLeapYear = leapYear;
    }

    public static int[] getLastDay() {
        return lastDay;
    }

    public static void setLastDay(int[] lastDay) {
        Calendar.lastDay = lastDay;
    }

    public static int getSum() {
        return sum;
    }

    public static void setSum(int sum) {
        Calendar.sum = sum;
    }

    public static int getWeekDay() {
        return weekDay;
    }

    public static void setWeekDay(int weekDay) {
        Calendar.weekDay = weekDay;
    }

    // 년도를 넘겨받아 윤년/평년을 판단해 윤년이면 true, 평년이면 false를 리턴하는 메서드
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0);
    }

    // 년, 월을 넘겨받아 그 달의 마지막 날짜를 리턴하는 메서드
    public static int lastDay(int year, int month) {
        int[] m = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        m[1] = isLeapYear(year) ? 29 : 28;
        return m[month - 1];
    }

    // 년, 월, 일을 넘겨받아 1년 1월 1일부터 지나온 날짜의 합계를 리턴하는 메서드
    public static int totalDay(int year, int month, int day) {
        int sum = (year - 1) * 365 + (year - 1) / 4 - (year - 1) / 100 + (year - 1) / 400;
        for (int i = 1; i < month; i++) {
            sum += lastDay(year, i);
        }
        return sum + day;
    }

    // 년, 월, 일을 넘겨받아 요일을 숫자로 리턴하는 메서드, 일요일(0), 월요일(1)....토요일(6)
    public static int weekDay(int year, int month, int day) {
        return totalDay(year, month, day) % 7;
    }
}
