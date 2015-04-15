package com.abs.repository;

import com.abs.domain.Ward;
import com.abs.domain.mappers.WardMapper;
import com.abs.service.WardDAO;
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
 * Created by Declan on 07/04/2015.
 */

@Repository
public class WardDAOImpl extends JdbcDaoSupport implements WardDAO {

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

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<Ward> getAllWards() {
        String SQL = "select * from ward";
        List<Ward> wardList = getJdbcTemplate().query(SQL, new Object[]{}, new WardMapper());
        return wardList;
    }
}