package com.abs.service;

import com.abs.domain.AmbulanceBooking;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Declan on 28/01/2015.
 */

@Service
public interface AmbulanceBookingDAO {
    public void setDataSource(DataSource dataSource);

    public Integer createAmbulanceBookingGetId(Integer patientId, Integer createdBy, Integer destination,
                                           Integer origin, boolean cardiac,boolean urgent, String dateOfTransfer);

    public void deleteAmbulanceBooking(Integer id);

    public AmbulanceBooking getBooking(Integer id);

    public List<AmbulanceBooking> getAllBookings();

    public void setAmbulanceCompany(Integer id, Integer ambulanceCompanyId);

    public List<AmbulanceBooking> getAllUnapprovedBookings();

    public List<AmbulanceBooking> getNewBookingsForAmbCompany(Integer ambCompanyId);

    public List<AmbulanceBooking> getUnassignedBookingsForAmbCompany(Integer ambCompId);

    public List<AmbulanceBooking> getAllAmbCrewBookings(Integer ambCrewId);

    public void setApproval(Integer id, boolean approval,Integer approvedBy);

    public void setStatus(Integer bookingId,Integer status);

    public void setAmbulanceCrew(Integer bookingId, Integer crewId);

    public Integer countRows();
}
