package com.abs.repository;

import com.abs.domain.AmbulanceCrew;
import com.abs.domain.mappers.AmbulanceCrewMapper;
import com.abs.service.AmbulanceCrewDAO;
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
import java.util.List;

/**
 * Created by Declan on 29/01/2015.
 */

@Repository
public class AmbulanceCrewDAOImpl extends JdbcDaoSupport implements AmbulanceCrewDAO {

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
    public Integer createAmbulanceCrewGetId(Integer ambCompanyId, Integer userId) {
        String SQL = "INSERT INTO ambulancecrew (ambCompanyId, userId, archived) "
                + "VALUES(?, ?, false)";

        Object[] params=new Object[]{ ambCompanyId, userId};
        PreparedStatementCreatorFactory psc=new PreparedStatementCreatorFactory(SQL);
        psc.addParameter(new SqlParameter("ambCompanyId", Types.INTEGER));
        psc.addParameter(new SqlParameter("userId", Types.INTEGER));

        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(psc.newPreparedStatementCreator(params), holder);

        String key=holder.getKey().toString();
        System.out.println("Created ambulance crew with id: " + key );
        return Integer.parseInt(key);
    }

    @Override
    @Transactional
    public void deleteAmbulanceCrew(Integer id) {
        String SQL = "update ambulancecrew set archived = 'y' where id = ?";
        getJdbcTemplate().update(SQL, new Object[] {id});
        System.out.println("Archived ambulance crew where id: " + id );
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public AmbulanceCrew getCrew(Integer id) {
        String SQL = "select * from ambulancecrew where id = ? and archived = 'n'";
        AmbulanceCrew ac = (AmbulanceCrew) getJdbcTemplate().queryForObject(SQL,
                new Object[]{id}, new AmbulanceCrewMapper());
        return ac;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<AmbulanceCrew> getAllCrews() {
        String SQL = "select * from ambulancecrew where archived = 'n'";
        List<AmbulanceCrew> acList = getJdbcTemplate().query(SQL,
                new AmbulanceCrewMapper());
        return acList;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Integer countRows() {
        String SQL = "select count(id) from ambulancecrew";
        Integer rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
        return rows;
    }
}
