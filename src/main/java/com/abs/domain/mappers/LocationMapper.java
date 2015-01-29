package com.abs.domain.mappers;

import com.abs.domain.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Declan on 28/01/2015.
 */
public class LocationMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        Location l = new Location(id,name);
        return l;
    }
}