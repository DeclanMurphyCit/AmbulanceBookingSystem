package com.abs.repository;

import com.abs.domain.AmbulanceBooking;
import com.abs.domain.mappers.AmbulanceBookingMapper;
import com.abs.service.AmbulanceBookingDAO;
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
 * Created by Declan on 28/01/2015.
 */

@Repository
public class AmbulanceBookingDAOImpl extends JdbcDaoSupport implements AmbulanceBookingDAO {

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
    public int createAmbulanceBookingGetId(int patientId, int createdBy, int destination, int origin,
                                           boolean cardiac, boolean urgent, String dateOfTransfer) {

        String SQL = "INSERT INTO ambulancebooking (patientId, createdBy, destination, "
                + "origin, cardiac, urgent, creationDateTime, transferDateTime, archived) "
                + "VALUES(?, ?, ?, ?, ?, ?, GETDATE(),?,false)";

        Object[] params=new Object[]{ patientId, createdBy, destination,origin,cardiac,urgent,dateOfTransfer};
        PreparedStatementCreatorFactory psc=new PreparedStatementCreatorFactory(SQL);
        psc.addParameter(new SqlParameter("patientId", Types.INTEGER));
        psc.addParameter(new SqlParameter("createdBy", Types.INTEGER));
        psc.addParameter(new SqlParameter("destination", Types.INTEGER));
        psc.addParameter(new SqlParameter("origin", Types.INTEGER));
        psc.addParameter(new SqlParameter("cardiac", Types.BOOLEAN));
        psc.addParameter(new SqlParameter("urgent", Types.BOOLEAN));
        psc.addParameter(new SqlParameter("dateOfTransfer", Types.DATE));

        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(psc.newPreparedStatementCreator(params), holder);

        String key=holder.getKey().toString();
        System.out.println("Created ambulance booking with id: " + key );
        return Integer.parseInt(key);
    }

    @Override
    @Transactional
    public void deleteAmbulanceBooking(int id) {
        String SQL = "update ambulancebooking set archived = true where id = ?";
        getJdbcTemplate().update(SQL, new Object[] {id});
        System.out.println("Archived ambulance booking where id: " + id );
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public AmbulanceBooking getBooking(int id) {
        String SQL = "select * from ambulancebooking where id = ? and archived = false";
        AmbulanceBooking ab = (AmbulanceBooking) getJdbcTemplate().queryForObject(SQL,
                new Object[]{id}, new AmbulanceBookingMapper());
        return ab;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public List<AmbulanceBooking> getAllBookings() {
        String SQL = "select * from ambulancebooking where archived = false";
        List<AmbulanceBooking> abList = getJdbcTemplate().query(SQL,
                new AmbulanceBookingMapper());
        return abList;
    }

    @Override
    public void setAmbulanceCompany(int id, int ambulanceCompanyId) {
        String SQL = "update ambulancebooking set ambCompanyId = ? where id = ?";
        getJdbcTemplate().update(SQL, new Object[] {ambulanceCompanyId,id});
    }

    @Override
    public void setApproval(int id, boolean approval) {
        String SQL = "update ambulancebooking set approval = ? where id = ?";
        getJdbcTemplate().update(SQL, new Object[] {approval,id});
    }

    //TODO Add update for admin modification

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public int countRows() {
        String SQL = "select count(id) from ambulancebooking";
        int rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
        return rows;
    }
}