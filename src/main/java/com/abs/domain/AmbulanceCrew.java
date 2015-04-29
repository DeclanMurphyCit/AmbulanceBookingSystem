package com.abs.domain;

public class AmbulanceCrew {
	
	private Integer id;
	private Integer ambCompanyId;
	private Integer userId;
	private boolean active;
	private String crewIdentifier;
	
	public AmbulanceCrew(Integer id,Integer ambCompanyId, Integer userId, boolean active, String crewIdentifier) {
		super();
		this.id = id;
		this.ambCompanyId = ambCompanyId;
		this.userId = userId;
		this.active = active;
		this.crewIdentifier = crewIdentifier;
	}
	
	public AmbulanceCrew(Integer ambCompanyId, Integer userId,boolean active,String crewIdentifier) {
		super();
		this.ambCompanyId = ambCompanyId;
		this.userId = userId;
		this.active = active;
		this.crewIdentifier = crewIdentifier;
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
	public String getcrewIdentifier() {
		return crewIdentifier;
	}
	public void setcrewIdentifier(String crewIdentifier) {
		this.crewIdentifier = crewIdentifier;
	}
}