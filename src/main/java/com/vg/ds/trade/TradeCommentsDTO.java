package com.vg.ds.trade;

public class TradeCommentsDTO {

	private String pk;
	private String mTwitterId;
	private String mNickname;
	private String sTwitterId;
	private String sNickname;
	private String text;
	private String date;
	private String t_pk;

	public TradeCommentsDTO() {
		// TODO Auto-generated constructor stub
	}

	public TradeCommentsDTO(String pk, String mTwitterId, String mNickname, String sTwitterId, String sNickname,
			String text, String date, String t_pk) {
		super();
		this.pk = pk;
		this.mTwitterId = mTwitterId;
		this.mNickname = mNickname;
		this.sTwitterId = sTwitterId;
		this.sNickname = sNickname;
		this.text = text;
		this.date = date;
		this.t_pk = t_pk;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getmTwitterId() {
		return mTwitterId;
	}

	public void setmTwitterId(String mTwitterId) {
		this.mTwitterId = mTwitterId;
	}

	public String getmNickname() {
		return mNickname;
	}

	public void setmNickname(String mNickname) {
		this.mNickname = mNickname;
	}

	public String getsTwitterId() {
		return sTwitterId;
	}

	public void setsTwitterId(String sTwitterId) {
		this.sTwitterId = sTwitterId;
	}

	public String getsNickname() {
		return sNickname;
	}

	public void setsNickname(String sNickname) {
		this.sNickname = sNickname;
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

	public String getT_pk() {
		return t_pk;
	}

	public void setT_pk(String t_pk) {
		this.t_pk = t_pk;
	}

	@Override
	public String toString() {
		return "TradeCommentsDTO [pk=" + pk + ", mTwitterId=" + mTwitterId + ", mNickname=" + mNickname
				+ ", sTwitterId=" + sTwitterId + ", sNickname=" + sNickname + ", text=" + text + ", date=" + date
				+ ", t_pk=" + t_pk + "]";
	}

}
