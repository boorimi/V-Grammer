package com.vg.ds.trade;

import java.util.ArrayList;
import java.util.Arrays;

public class TradeDTO {

	private String pk;
	private String twitterId;
	private String screenName;
	private String nickname;
	private String text;
	private String date;
	private String[] category;
	private ArrayList<TradeCommentsDTO> comments;

	public TradeDTO() {
		// TODO Auto-generated constructor stub
	}

	public TradeDTO(String pk, String twitterId, String screenName, String nickname, String text, String date,
			String[] category, ArrayList<TradeCommentsDTO> comments) {
		super();
		this.pk = pk;
		this.twitterId = twitterId;
		this.screenName = screenName;
		this.nickname = nickname;
		this.text = text;
		this.date = date;
		this.category = category;
		this.comments = comments;
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

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
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

	public String[] getCategory() {
		return category;
	}

	public void setCategory(String[] category) {
		this.category = category;
	}

	public ArrayList<TradeCommentsDTO> getComments() {
		return comments;
	}

	public void setComments(ArrayList<TradeCommentsDTO> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "TradeDTO [pk=" + pk + ", twitterId=" + twitterId + ", screenName=" + screenName + ", nickname="
				+ nickname + ", text=" + text + ", date=" + date + ", category=" + Arrays.toString(category)
				+ ", comments=" + comments + "]";
	}

}
