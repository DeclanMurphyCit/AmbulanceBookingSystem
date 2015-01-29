package com.abs.domain.mappers;

import com.abs.domain.AmbulanceBooking;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


/**
 * Created by Declan on 28/01/2015.
 */
public class AmbulanceBookingMapper  implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        //TODO Add in patientId or patient object?
        int ambCompanyId = rs.getInt("ambCompanyId");
        int patientId = rs.getInt("patientId");
        int createdBy = rs.getInt("createdBy");
        int destination = rs.getInt("destination");
        int origin = rs.getInt("origin");
        boolean cardiac = rs.getBoolean("cardiac");
        boolean urgent = rs.getBoolean("urgent");
        boolean approved = rs.getBoolean("approved");
        double cost = rs.getDouble("cost");

        AmbulanceBooking a = new AmbulanceBooking(id, patientId,ambCompanyId, createdBy,
        destination, origin, cardiac,
        urgent/*, dateCreated, dateOfTransfer*/);//TODO Add date functionality

        return a;
    }
}
