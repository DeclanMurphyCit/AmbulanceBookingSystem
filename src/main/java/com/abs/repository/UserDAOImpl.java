package com.abs.repository;

import com.abs.domain.User;
import com.abs.domain.mappers.UserMapper;
import com.abs.service.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by Declan on 03/02/2015.
 */
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {

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
    public User getUser(Integer id) {
        String SQL = "select * from patient where id = ? and archived = 'n'";
        User p = (User) getJdbcTemplate().queryForObject(SQL,
                new Object[]{id}, new UserMapper());
        return p;
    }
}