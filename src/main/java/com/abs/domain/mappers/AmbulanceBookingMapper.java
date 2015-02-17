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
        Integer id = rs.getInt("id");
        Integer ambCompanyId = rs.getInt("ambCompanyId");
        Integer patientId = rs.getInt("patientId");
        Integer createdBy = rs.getInt("createdBy");
        Integer destination = rs.getInt("destination");
        Integer origin = rs.getInt("origin");
        boolean cardiac = rs.getBoolean("cardiac");
        boolean urgent = rs.getBoolean("urgent");
        boolean approved = rs.getBoolean("approved");
        double cost = rs.getDouble("cost");
        String dateCreated = rs.getString("creationDateTime");
        dateCreated = dateCreated.substring(0, dateCreated.length()-3);
        String dateOfTransfer = rs.getString("transferDateTime");

        AmbulanceBooking a = new AmbulanceBooking(id, patientId,ambCompanyId, createdBy,
        destination, origin, cardiac,urgent, approved, cost, dateCreated, dateOfTransfer);

        return a;
    }
}
