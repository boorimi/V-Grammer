package com.vg.kw.main;

public class GetAllStreamDTO {

	private String pk;
	private String YchannelID;

	public GetAllStreamDTO() {
	}

	public GetAllStreamDTO(String pk, String ychannelID) {
		super();
		this.pk = pk;
		YchannelID = ychannelID;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getYchannelID() {
		return YchannelID;
	}

	public void setYchannelID(String ychannelID) {
		YchannelID = ychannelID;
	}

	@Override
	public String toString() {
		return "LiveStreamDTO [pk=" + pk + ", YchannelID=" + YchannelID + "]";
	}

}
