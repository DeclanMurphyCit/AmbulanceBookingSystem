package com.abs.service;


import com.abs.domain.Ward;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Declan on 07/04/2015.
 */
@Service
public interface WardDAO {

    public void setDataSource(DataSource dataSource);

    public List<Ward> getAllWards();
}
