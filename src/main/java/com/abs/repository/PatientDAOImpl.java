package com.abs.repository;

import com.abs.domain.Patient;
import com.abs.domain.mappers.LocationMapper;
import com.abs.domain.mappers.PatientMapper;
import com.abs.service.PatientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Declan on 03/02/2015.
 */

@Repository
public class PatientDAOImpl extends JdbcDaoSupport implements PatientDAO {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Autowired
    public void setTxTemplate(TransactionTemplate txTemplate) {
        this.transactionTemplate = txTemplate;
    }

/*    @Override
    @Transactional
    public Integer createPatientGetId(String name) {
        return 0;
    }*/

/*
    @Override
    public void deletePatient(Integer id) {

    }
*/

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Patient getPatient(Integer id) {
        String SQL = "select * from patient where id = ? and archived = 'n'";
        Patient p = (Patient) getJdbcTemplate().queryForObject(SQL,
                new Object[]{id}, new PatientMapper());
        return p;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<Patient> getAllPatientsFromWard(Integer wardId) {
        String SQL = "select * from patient where archived = 'n' and wardId = ?";
        List<Patient> patList = getJdbcTemplate().query(SQL,
                new Object[]{wardId}, new PatientMapper());
        return patList;
    }
}