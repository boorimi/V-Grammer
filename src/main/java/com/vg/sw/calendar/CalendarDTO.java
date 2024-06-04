package com.vg.sw.calendar;

public class CalendarDTO {

	private boolean isLeapYear;
	private int[] lastDay;
	private int sum;
	private int weekDay;

	// 생성자
	public CalendarDTO() {
	}

	// 게터와 세터
	public boolean isLeapYear() {
		return isLeapYear;
	}

	public void setLeapYear(boolean leapYear) {
		this.isLeapYear = leapYear;
	}

	public int[] getLastDay() {
		return lastDay;
	}

	public void setLastDay(int[] lastDay) {
		this.lastDay = lastDay;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}
	
	
}

