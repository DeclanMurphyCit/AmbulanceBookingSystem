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

    public int createAmbulanceBookingGetId(int patientId, int createdBy, int destination,
                                           int origin, boolean cardiac,boolean urgent, String dateOfTransfer);

    public void deleteAmbulanceBooking(int id);

    public AmbulanceBooking getBooking(int id);

    public List<AmbulanceBooking> getAllBookings();

    public void setAmbulanceCompany(int id, int ambulanceCompanyId);

    public void setApproval(int id, boolean approval);

    public int countRows();
}
