package com.abs.domain;

public class AmbulanceCrew {
	
	private Integer id;
	private Integer ambCompanyId;
	private Integer userId;
	private boolean active;
	private String ambRegNum;
	
	public AmbulanceCrew(Integer id,Integer ambCompanyId, Integer userId, boolean active, String ambRegNum) {
		super();
		this.id = id;
		this.ambCompanyId = ambCompanyId;
		this.userId = userId;
		this.active = active;
		this.ambRegNum = ambRegNum;
	}
	
	public AmbulanceCrew(Integer ambCompanyId, Integer userId,boolean active,String ambRegNum) {
		super();
		this.ambCompanyId = ambCompanyId;
		this.userId = userId;
		this.active = active;
		this.ambRegNum = ambRegNum;
	}
	
	public AmbulanceCrew(){}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAmbCompanyId() {
		return ambCompanyId;
	}
	public void setAmbCompanyId(Integer ambCompanyId) {
		this.ambCompanyId = ambCompanyId;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getAmbRegNum() {
		return ambRegNum;
	}
	public void setAmbRegNum(String ambRegNum) {
		this.ambRegNum = ambRegNum;
	}
}