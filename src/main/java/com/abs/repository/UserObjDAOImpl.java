package com.abs.repository;

import com.abs.domain.UserObj;
import com.abs.domain.mappers.UserObjMapper;
import com.abs.service.UserObjDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Types;

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

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public int createUserGetId(String username, String password, String firstname, String lastname,String authority) {

        String SQL = "INSERT INTO user (username, password, enabled,firstname,lastname) "
                + "VALUES(?,?,1,?,?)";

        Object[] params=new Object[]{username, password,firstname,lastname };
        PreparedStatementCreatorFactory psc=new PreparedStatementCreatorFactory(SQL);
        psc.addParameter(new SqlParameter("username", Types.VARCHAR));
        psc.addParameter(new SqlParameter("password", Types.VARCHAR));
        psc.addParameter(new SqlParameter("firstname", Types.VARCHAR));
        psc.addParameter(new SqlParameter("lastname", Types.VARCHAR));
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(psc.newPreparedStatementCreator(params), holder);
        String key=holder.getKey().toString();

        SQL= "INSERT INTO userrole (userId,authority) VALUES(?,?)";

        Object[] paramsRole =new Object[]{key, authority};
        PreparedStatementCreatorFactory pscRole =new PreparedStatementCreatorFactory(SQL);
        pscRole.addParameter(new SqlParameter("userId", Types.INTEGER));
        pscRole.addParameter(new SqlParameter("authority", Types.VARCHAR));
        getJdbcTemplate().update(pscRole.newPreparedStatementCreator(paramsRole));
        return Integer.parseInt(key);
    }
}