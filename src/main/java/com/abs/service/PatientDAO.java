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

/*    public int createPatientGetId(String name);*/

/*    public void deletePatient(int id);*/

    public Patient getPatient(int id);

    public List<Patient> getAllPatientsFromWard(int wardId);
    
/*    public int countRows();*/

}
