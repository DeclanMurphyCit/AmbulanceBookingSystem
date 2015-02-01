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

    public int createLocationGetId(String name);

    public void deleteLocation(int id);

    public Location getLocation(int id);

    public List<Location> getAllLocations();

    public int countRows();
}
