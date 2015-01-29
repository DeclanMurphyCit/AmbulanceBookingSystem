package com.abs.domain;

public class AmbulanceCrew {
	
	private int id;
	private int ambCompanyId;
	private boolean active;
	
	public AmbulanceCrew(int id,int ambCompanyId, boolean active) {
		super();
		this.id = id;
		this.ambCompanyId = ambCompanyId;
		this.active = active;
	}
	
	public AmbulanceCrew(int ambCompanyId,boolean active) {
		super();
		this.ambCompanyId = ambCompanyId;
		this.active = active;
	}
	
	public AmbulanceCrew(){}

	public int getAmbCompanyId() {
		return ambCompanyId;
	}
	public void setAmbCompanyId(int ambCompanyId) {
		this.ambCompanyId = ambCompanyId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}