package com.abs.domain;

import java.util.Date;

/**
 * Created by Declan on 20/01/2015.
 */

public class AmbulanceBooking {

    private Integer bookingId;
    private Integer patientId;
    private AmbulanceCompany ambCompany;
    private Integer ambCompanyId;
    private Integer ambCrewId;
    private Integer createdBy; //User
    private Integer destination;
    private Integer origin;
    private boolean cardiac;
    private boolean urgent;
    private boolean approved;
    private Integer approvedBy;
    private double cost;
    private String dateCreated;
    private String dateOfTransfer;
    private Integer status;


    public AmbulanceBooking(Integer bookingId, Integer patientId,Integer ambCompanyId,Integer ambCrewId, Integer createdBy,
                            Integer destination, Integer origin, boolean cardiac, Integer status,
                            boolean urgent, boolean approved, Integer approvedBy, double cost, String dateCreated, String dateOfTransfer) {
        this.bookingId = bookingId;
        this.patientId = patientId;
        this.ambCompanyId = ambCompanyId;
        this.ambCrewId = ambCrewId;
        this.createdBy = createdBy;
        this.destination = destination;
        this.origin = origin;
        this.cardiac = cardiac;
        this.status = status;
        this.urgent = urgent;
        this.approved = approved;
        this.approvedBy = approvedBy;
        this.cost = cost;
        this.dateCreated = dateCreated;
        this.dateOfTransfer = dateOfTransfer;
    }

    public AmbulanceBooking(Integer bookingId, Integer patientId,Integer ambCompanyId,Integer ambCrewId, Integer createdBy,
                            Integer destination, Integer origin, boolean cardiac,
                            boolean urgent/*, Date dateCreated, Date dateOfTransfer*/) { //TODO Add date functionality
        this.bookingId = bookingId;
        this.patientId = patientId;
        this.ambCompanyId = ambCompanyId;
        this.ambCrewId = ambCrewId;
        this.createdBy = createdBy;
        this.destination = destination;
        this.origin = origin;
        this.cardiac = cardiac;
        this.urgent = urgent;
        /*this.dateCreated = dateCreated;
        this.dateOfTransfer = dateOfTransfer;*/
    }

    public AmbulanceBooking(){}

    public AmbulanceCompany getAmbCompany() {
        return ambCompany;
    }

    public void setAmbCompany(AmbulanceCompany ambCompany) {
        this.ambCompany = ambCompany;
    }

    public Integer getAmbCompanyId() {
        return ambCompanyId;
    }

    public void setAmbCompanyId(Integer ambCompanyId) {
        this.ambCompanyId = ambCompanyId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public Integer getAmbCrewId() {
        return ambCrewId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public Integer getDestination() {
        return destination;
    }

    public Integer getOrigin() {
        return origin;
    }

    public boolean isCardiac() {
        return cardiac;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public boolean isApproved() {
        return approved;
    }

    public double getCost() {
        return cost;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public void setAmbCrewId(Integer ambCrewId) {
        this.ambCrewId = ambCrewId;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public void setCardiac(boolean cardiac) {
        this.cardiac = cardiac;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateOfTransfer(String dateOfTransfer) {
        this.dateOfTransfer = dateOfTransfer;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
