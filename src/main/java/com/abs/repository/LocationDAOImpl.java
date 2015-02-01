package com.abs.repository;

import com.abs.domain.Location;
import com.abs.domain.mappers.LocationMapper;
import com.abs.service.LocationDAO;
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
public class LocationDAOImpl extends JdbcDaoSupport implements LocationDAO {

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
    public int createLocationGetId(String name) {
        String SQL = "INSERT INTO location (name,archived) "
                + "VALUES(?, false)";

        Object[] params=new Object[]{ name};
        PreparedStatementCreatorFactory psc=new PreparedStatementCreatorFactory(SQL);
        psc.addParameter(new SqlParameter("name", Types.VARCHAR));

        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(psc.newPreparedStatementCreator(params), holder);

        String key=holder.getKey().toString();
        System.out.println("Created location: " + name + " with id: " + key );
        return Integer.parseInt(key);
    }

    @Override
    @Transactional
    public void deleteLocation(int id) {
        String SQL = "update location set archived = true where id = ?";
        getJdbcTemplate().update(SQL, new Object[] {id});
        System.out.println("Archived location where id: " + id );
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Location getLocation(int id) {
        String SQL = "select * from location where id = ? and archived = false";
        Location ac = (Location) getJdbcTemplate().queryForObject(SQL,
                new Object[]{id}, new LocationMapper());
        return ac;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<Location> getAllLocations() {
        String SQL = "select * from location where archived = false";
        List<Location> locList = getJdbcTemplate().query(SQL,
                new LocationMapper());
        return locList;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public int countRows() { //TODO Add where archived = false?
        String SQL = "select count(id) from location";
        int rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
        return rows;
    }
}
