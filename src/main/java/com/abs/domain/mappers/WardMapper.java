package com.abs.domain.mappers;

import com.abs.domain.Ward;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Declan on 07/04/2015.
 */
public class WardMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        Ward w = new Ward(id,name);
        return w;
    }
}
