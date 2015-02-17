package com.abs.service;

import com.abs.domain.Patient;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Declan on 28/01/2015.
 */

@Service
public interface PatientDAO {

    public void setDataSource(DataSource dataSource);

/*    public Integer createPatientGetId(String name);*/

/*    public void deletePatient(Integer id);*/

    public Patient getPatient(Integer id);

    public List<Patient> getAllPatientsFromWard(Integer wardId);
    
/*    public Integer countRows();*/

}
