package com.abs.service;

import com.abs.domain.AmbulanceCompany;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Declan on 28/01/2015.
 */

@Service
public interface AmbulanceCompanyDAO {
    public void setDataSource(DataSource dataSource);

    public Integer createAmbulanceCompanyGetId(Integer userId, String name, BigDecimal cost, boolean cardiac, String timeActive, String timeInactive);

    public void deleteAmbulanceCompany(Integer id);

    public AmbulanceCompany getCompany(Integer id);

    public AmbulanceCompany getCompanyUserId(Integer id);

    public List<AmbulanceCompany> getAllCompanies();
    
    public Integer countRows();
}