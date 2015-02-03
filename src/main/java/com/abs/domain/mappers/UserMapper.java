package com.abs.domain.mappers;

import com.abs.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Declan on 28/01/2015.
 */
public class UserMapper  implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String username = rs.getString("username");

        User u = new User(id,username,firstname,lastname);
        return u;
    }
}
