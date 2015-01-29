package com.abs.domain.mappers;

import com.abs.domain.AmbulanceCompany;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Declan on 28/01/2015.
 */
public class AmbulanceCompanyMapper  implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        double cost = rs.getDouble("cost");
        boolean cardiac = rs.getBoolean("cardiac");
        String timeActive = rs.getString("timeActive");
        String timeInactive = rs.getString("timeInactive");

        AmbulanceCompany ac = new AmbulanceCompany(id,name,cost,timeActive,
                 timeInactive,cardiac);

        return ac;
    }
}
