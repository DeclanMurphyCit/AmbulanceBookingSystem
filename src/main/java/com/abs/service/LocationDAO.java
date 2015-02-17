package com.abs.service;

import com.abs.domain.Location;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Declan on 28/01/2015.
 */

@Service
public interface LocationDAO {
    public void setDataSource(DataSource dataSource);

    public Integer createLocationGetId(String name);

    public void deleteLocation(Integer id);

    public Location getLocation(Integer id);

    public List<Location> getAllLocations();

    public Integer countRows();
}
