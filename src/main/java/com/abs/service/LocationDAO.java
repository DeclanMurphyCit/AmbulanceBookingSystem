package com.abs.service;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by Declan on 28/01/2015.
 */

@Service
public interface LocationDAO {
    public void setDataSource(DataSource dataSource);

    public int countRows();
}
