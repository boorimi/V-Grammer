package com.vg.ds.trade;

public class TradeCommentsDTO {

	private String pk;
	private String mTwitterId;
	private String mId;
	private String mNickname;
	private String sTwitterId;
	private String sId;
	private String sNickname;
	private String text;
	private String date;
	private String yesno;
	private String t_pk;

	public TradeCommentsDTO(String pk, String mTwitterId, String mId, String mNickname, String sTwitterId, String sId,
			String sNickname, String text, String date, String yesno, String t_pk) {
		super();
		this.pk = pk;
		this.mTwitterId = mTwitterId;
		this.mId = mId;
		this.mNickname = mNickname;
		this.sTwitterId = sTwitterId;
		this.sId = sId;
		this.sNickname = sNickname;
		this.text = text;
		this.date = date;
		this.yesno = yesno;
		this.t_pk = t_pk;
	}

	public TradeCommentsDTO() {
		// TODO Auto-generated constructor stub
	}


	public String getT_pk() {
		return t_pk;
	}

	public void setT_pk(String t_pk) {
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

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
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

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
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

	public String getYesno() {
		return yesno;
	}

	public void setYesno(String yesno) {
		this.yesno = yesno;
	}

	@Override
	public String toString() {
		return "TradeCommentsDTO [pk=" + pk + ", mTwitterId=" + mTwitterId + ", mId=" + mId + ", mNickname=" + mNickname
				+ ", sTwitterId=" + sTwitterId + ", sId=" + sId + ", sNickname=" + sNickname + ", text=" + text
				+ ", date=" + date + ", yesno=" + yesno + ", t_pk=" + t_pk + "]";
	}

	

}
