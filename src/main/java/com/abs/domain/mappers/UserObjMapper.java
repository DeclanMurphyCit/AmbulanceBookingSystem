package com.abs.domain.mappers;

import com.abs.domain.UserObj;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Declan on 28/01/2015.
 */
public class UserObjMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String username = rs.getString("username");

        UserObj u = new UserObj(id,username,firstname,lastname);
        return u;
    }
}
