package com.abs.repository;

import com.abs.domain.AmbulanceCompany;
import com.abs.domain.mappers.AmbulanceCompanyMapper;
import com.abs.service.AmbulanceCompanyDAO;
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
import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;

/**
 * Created by Declan on 29/01/2015.
 */

@Repository
public class AmbulanceCompanyDAOImpl  extends JdbcDaoSupport implements AmbulanceCompanyDAO {

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
    @Transactional
    public Integer createAmbulanceCompanyGetId(Integer userId,String name, BigDecimal cost, boolean cardiac,
                                           String timeActive, String timeInactive) {

        String SQL = "INSERT INTO ambulancecompany (userId, name, cost, cardiac, timeActive, timeInactive, archived) "
                + "VALUES(?, ?, ?, ?, ?, ?, false)";

        Object[] params=new Object[]{userId, name, cost, cardiac,timeActive,timeInactive};
        PreparedStatementCreatorFactory psc=new PreparedStatementCreatorFactory(SQL);

        psc.addParameter(new SqlParameter("userId", Types.INTEGER));
        psc.addParameter(new SqlParameter("name", Types.VARCHAR));
        psc.addParameter(new SqlParameter("cost", Types.DOUBLE));
        psc.addParameter(new SqlParameter("cardiac", Types.BOOLEAN));
        psc.addParameter(new SqlParameter("timeActive", Types.VARCHAR));
        psc.addParameter(new SqlParameter("timeInactive", Types.VARCHAR));

        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(psc.newPreparedStatementCreator(params), holder);

        String key=holder.getKey().toString();
        System.out.println("Created ambulance company with id: " + key );
        return Integer.parseInt(key);
    }

    @Override
    @Transactional
    public void deleteAmbulanceCompany(Integer id) {
        String SQL = "update ambulancecompany set archived = 'y' where id = ?";
        getJdbcTemplate().update(SQL, new Object[] {id});
        System.out.println("Archived ambulance company where id: " + id );
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public AmbulanceCompany getCompany(Integer id) {
        String SQL = "select * from ambulancecompany where id = ? and archived = 'n'";
        AmbulanceCompany ac = (AmbulanceCompany) getJdbcTemplate().queryForObject(SQL,
                new Object[]{id}, new AmbulanceCompanyMapper());
        return ac;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public AmbulanceCompany getCompanyUserId(Integer userId) {
        String SQL = "select * from ambulancecompany where userId = ? and archived = 'n'";
        AmbulanceCompany ac = (AmbulanceCompany) getJdbcTemplate().queryForObject(SQL,
                new Object[]{userId}, new AmbulanceCompanyMapper());
        return ac;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<AmbulanceCompany> getAllCompanies() {
        String SQL = "select * from ambulancecompany where archived = 'n'";
        List<AmbulanceCompany> acList = getJdbcTemplate().query(SQL,
                new AmbulanceCompanyMapper());
        return acList;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Integer countRows() {
        String SQL = "select count(id) from ambulancecompany";
        Integer rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
        return rows;
    }
}