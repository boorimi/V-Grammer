package com.vg.ds.announcement;

public class AnnouncementDTO {

	private String pk;
	private String twitterId;
	private String title;
	private String text;
	private String date;

	public AnnouncementDTO() {
		// TODO Auto-generated constructor stub
	}

	public AnnouncementDTO(String pk, String twitterId, String title, String text, String date) {
		super();
		this.pk = pk;
		this.twitterId = twitterId;
		this.title = title;
		this.text = text;
		this.date = date;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "AnnouncementDTO [pk=" + pk + ", twitterId=" + twitterId + ", title=" + title + ", text=" + text
				+ ", date=" + date + "]";
	}

}
