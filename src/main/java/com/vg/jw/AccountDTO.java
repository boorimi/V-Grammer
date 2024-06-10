package com.vg.jw;

public class AccountDTO {
	//계정정보 마토메 Bean
	private String u_id;
	private String u_pw;
	private long u_twitter_id;
	private String u_nickname;
	private int u_yesno;
	
	public AccountDTO() {
		// TODO Auto-generated constructor stub
	}

	public AccountDTO(String u_id, String u_pw, long u_twitter_id, String u_nickname, int u_yesno) {
		super();
		this.u_id = u_id;
		this.u_pw = u_pw;
		this.u_twitter_id = u_twitter_id;
		this.u_nickname = u_nickname;
		this.u_yesno = u_yesno;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getU_pw() {
		return u_pw;
	}

	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}

	public long getU_twitter_id() {
		return u_twitter_id;
	}

	public void setU_twitter_id(long u_twitter_id) {
		this.u_twitter_id = u_twitter_id;
	}

	public String getU_nickname() {
		return u_nickname;
	}

	public void setU_nickname(String u_nickname) {
		this.u_nickname = u_nickname;
	}

	public int getU_yesno() {
		return u_yesno;
	}

	public void setU_yesno(int u_yesno) {
		this.u_yesno = u_yesno;
	}

	@Override
	public String toString() {
		return "AccountDTO [u_id=" + u_id + ", u_pw=" + u_pw + ", u_twitter_id=" + u_twitter_id + ", u_nickname="
				+ u_nickname + ", u_yesno=" + u_yesno + "]";
	}
	
	
	

}
