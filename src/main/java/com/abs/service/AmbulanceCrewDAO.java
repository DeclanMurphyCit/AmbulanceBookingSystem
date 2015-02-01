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

    public int createAmbulanceCrewGetId(int ambCompanyId,int userId);

    public void deleteAmbulanceCrew(int id);

    public AmbulanceCrew getCrew(int id);

    public List<AmbulanceCrew> getAllCrews();

    public int countRows();
}
