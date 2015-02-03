package com.abs.service;

import com.abs.domain.User;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Declan on 03/02/2015.
 */

@Service
public interface UserDAO {

    public void setDataSource(DataSource dataSource);

    public User getUser(int id);
}
