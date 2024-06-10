package com.vg.jw;

public class AccountDTO {
	// 계정정보 마토메 Bean

	private long u_twitter_id;
	private String u_nickname;
	private String u_screenName;
	private String u_profile_img;

	public AccountDTO() {
		// TODO Auto-generated constructor stub
	}

	public AccountDTO(long u_twitter_id, String u_nickname, String u_screenName, String u_profile_img) {
		super();
		this.u_twitter_id = u_twitter_id;
		this.u_nickname = u_nickname;
		this.u_screenName = u_screenName;
		this.u_profile_img = u_profile_img;
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

	public String getU_screenName() {
		return u_screenName;
	}

	public void setU_screenName(String u_screenName) {
		this.u_screenName = u_screenName;
	}

	public String getU_profile_img() {
		return u_profile_img;
	}

	public void setU_profile_img(String u_profile_img) {
		this.u_profile_img = u_profile_img;
	}

	@Override
	public String toString() {
		return "AccountDTO [u_twitter_id=" + u_twitter_id + ", u_nickname=" + u_nickname + ", u_screenName="
				+ u_screenName + ", u_profile_img=" + u_profile_img + "]";
	}

}
