package com.abs.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.util.Date;

/**
 * Created by Declan on 20/01/2015.
 */

@PlanningEntity
@XStreamAlias("AmbulanceBooking")
public class AmbulanceBooking {

    private int bookingId;
    private int patientId;
    private AmbulanceCompany ambCompany;
    private int ambCompanyId;
    private int ambCrewId;
    private int createdBy; //User
    private int destination;
    private int origin;
    private boolean cardiac;
    private boolean urgent;
    private boolean approved;
    private double cost;
    private Date dateCreated;
    private Date dateOfTransfer;


    public AmbulanceBooking(int bookingId, int patientId,int ambCompanyId, int createdBy,
                            int destination, int origin, boolean cardiac,
                            boolean urgent, Date dateCreated, Date dateOfTransfer) {
        this.bookingId = bookingId;
        this.patientId = patientId;
        this.ambCompanyId = ambCompanyId;
        this.createdBy = createdBy;
        this.destination = destination;
        this.origin = origin;
        this.cardiac = cardiac;
        this.urgent = urgent;
        this.dateCreated = dateCreated;
        this.dateOfTransfer = dateOfTransfer;
    }

    public AmbulanceBooking(int bookingId, int patientId,int ambCompanyId, int createdBy,
                            int destination, int origin, boolean cardiac,
                            boolean urgent/*, Date dateCreated, Date dateOfTransfer*/) { //TODO Add date functionality
        this.bookingId = bookingId;
        this.patientId = patientId;
        this.ambCompanyId = ambCompanyId;
        this.createdBy = createdBy;
        this.destination = destination;
        this.origin = origin;
        this.cardiac = cardiac;
        this.urgent = urgent;
        /*this.dateCreated = dateCreated;
        this.dateOfTransfer = dateOfTransfer;*/
    }

    public AmbulanceBooking(){}

    @PlanningVariable(valueRangeProviderRefs = {"acRange"})
    public AmbulanceCompany getAmbCompany() {
        return ambCompany;
    }

    public void setAmbCompany(AmbulanceCompany ambCompany) {
        this.ambCompany = ambCompany;
    }

    public int getAmbCompanyId() {
        return ambCompanyId;
    }

    public void setAmbCompanyId(int ambCompanyId) {
        this.ambCompanyId = ambCompanyId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getAmbCrewId() {
        return ambCrewId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public int getDestination() {
        return destination;
    }

    public int getOrigin() {
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setAmbCrewId(int ambCrewId) {
        this.ambCrewId = ambCrewId;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public void setOrigin(int origin) {
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

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateOfTransfer(Date dateOfTransfer) {
        this.dateOfTransfer = dateOfTransfer;
    }
}
