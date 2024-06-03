package com.vg.ds.trade;

public class TradeDTO {

	private String pk;
	private String twitterId;
	private String id;
	private String nickname;
	private String text;
	private String date;
	private String yesno;

	public TradeDTO() {
		// TODO Auto-generated constructor stub
	}

	public TradeDTO(String pk, String twitterId, String id, String nickname, String text, String date, String yesno) {
		super();
		this.pk = pk;
		this.twitterId = twitterId;
		this.id = id;
		this.nickname = nickname;
		this.text = text;
		this.date = date;
		this.yesno = yesno;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		return "TradeDTO [pk=" + pk + ", twitterId=" + twitterId + ", id=" + id + ", nickname=" + nickname + ", text="
				+ text + ", date=" + date + ", yesno=" + yesno + "]";
	}

}
