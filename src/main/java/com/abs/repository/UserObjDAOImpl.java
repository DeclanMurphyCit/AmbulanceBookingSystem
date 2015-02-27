package com.abs.repository;

import com.abs.domain.UserObj;
import com.abs.domain.mappers.UserObjMapper;
import com.abs.service.UserObjDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by Declan on 03/02/2015.
 */
@Repository
public class UserObjDAOImpl extends JdbcDaoSupport implements UserObjDAO {

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
    public UserObj getUser(Integer id) {
        String SQL = "select * from user where id = ? and enabled = true";
        UserObj p = (UserObj) getJdbcTemplate().queryForObject(SQL,
                new Object[]{id}, new UserObjMapper());
        return p;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public UserObj getUserByUsername(String username) {
        String SQL = "select * from user where username = ? and enabled = true";
        UserObj p = (UserObj) getJdbcTemplate().queryForObject(SQL,
                new Object[]{username}, new UserObjMapper());
        return p;
    }


}