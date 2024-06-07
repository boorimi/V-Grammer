package com.vg.bm.Member;

public class AddressDTO {
	String a_pk;
	String a_category;
	String a_address;
	
	public AddressDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getA_pk() {
		return a_pk;
	}

	public void setA_pk(String a_pk) {
		this.a_pk = a_pk;
	}


	public String getA_category() {
		return a_category;
	}

	public void setA_category(String a_category) {
		this.a_category = a_category;
	}

	public String getA_address() {
		return a_address;
	}

	public void setA_address(String a_address) {
		this.a_address = a_address;
	}

	public AddressDTO(String a_pk,String a_category, String a_address) {
		super();
		this.a_pk = a_pk;
		this.a_category = a_category;
		this.a_address = a_address;
	}

	@Override
	public String toString() {
		return "AddressDTO [a_pk=" + a_pk + ", a_category=" + a_category + ", a_address="
				+ a_address + "]";
	}
	
	
}
