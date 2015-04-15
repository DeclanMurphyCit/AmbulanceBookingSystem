package com.abs.service;

import com.abs.domain.AmbulanceCrew;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Declan on 28/01/2015.
 */

@Service
public interface AmbulanceCrewDAO {
    public void setDataSource(DataSource dataSource);

    public Integer createAmbulanceCrewGetId(Integer ambCompanyId,Integer userId,String ambRegNumber);

    public void deleteAmbulanceCrew(Integer id);

    public AmbulanceCrew getCrew(Integer id);

    public AmbulanceCrew getCrewUserId(Integer userId);

    public List<AmbulanceCrew> getAllCrews();

    public List<AmbulanceCrew> getAllCrewsFromAmbCompany(Integer ambCompId);

    public Integer countRows();
}
