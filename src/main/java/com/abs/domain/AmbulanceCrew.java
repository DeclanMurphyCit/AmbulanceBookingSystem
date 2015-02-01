package com.abs.domain;

public class AmbulanceCrew {
	
	private int id;
	private int ambCompanyId;
	private int userId;
	private boolean active;
	
	public AmbulanceCrew(int id,int ambCompanyId, int userId, boolean active) {
		super();
		this.id = id;
		this.ambCompanyId = ambCompanyId;
		this.userId = userId;
		this.active = active;
	}
	
	public AmbulanceCrew(int ambCompanyId, int userId,boolean active) {
		super();
		this.ambCompanyId = ambCompanyId;
		this.userId = userId;
		this.active = active;
	}
	
	public AmbulanceCrew(){}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAmbCompanyId() {
		return ambCompanyId;
	}
	public void setAmbCompanyId(int ambCompanyId) {
		this.ambCompanyId = ambCompanyId;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}