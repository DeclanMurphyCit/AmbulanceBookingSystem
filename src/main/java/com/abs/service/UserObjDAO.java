package com.abs.service;

import com.abs.domain.UserObj;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by Declan on 03/02/2015.
 */

@Service
public interface UserObjDAO {

    public void setDataSource(DataSource dataSource);

    public UserObj getUser(Integer id);

    public UserObj getUserByUsername(String username);

    public int createUserGetId(String username, String password, String firstname,String lastname,String authority);
}
