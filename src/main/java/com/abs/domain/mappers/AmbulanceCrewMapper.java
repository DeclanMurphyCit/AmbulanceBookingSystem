package com.abs.domain.mappers;

import com.abs.domain.AmbulanceCrew;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Declan on 28/01/2015.
 */
public class AmbulanceCrewMapper   implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        Integer ambCompanyId = rs.getInt("ambCompanyId");
        boolean active = rs.getBoolean("active");
        AmbulanceCrew ac = new AmbulanceCrew(id,ambCompanyId,active);
        return ac;
    }
}