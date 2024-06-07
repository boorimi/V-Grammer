package com.vg.ds.trade;

import java.util.Arrays;

public class TradeDTO {

	private String pk;
	private String id;
	private String twitterId;
	private String nickname;
	private String text;
	private String date;
	private String yesno;
	private String[] category;

	public TradeDTO() {
		// TODO Auto-generated constructor stub
	}

	public TradeDTO(String pk, String id, String twitterId, String nickname, String text, String date, String yesno,
			String[] category) {
		super();
		this.pk = pk;
		this.id = id;
		this.twitterId = twitterId;
		this.nickname = nickname;
		this.text = text;
		this.date = date;
		this.yesno = yesno;
		this.category = category;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String[] getCategory() {
		return category;
	}

	public void setCategory(String[] category) {
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getYesno() {
		return yesno;
	}

	public void setYesno(String yesno) {
		this.yesno = yesno;
	}

	@Override
	public String toString() {
		return "TradeDTO [pk=" + pk + ", id=" + id + ", twitterId=" + twitterId + ", nickname=" + nickname + ", text="
				+ text + ", date=" + date + ", yesno=" + yesno + ", category=" + Arrays.toString(category) + "]";
	}

}
