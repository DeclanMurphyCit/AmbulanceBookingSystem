package com.abs.repository;

import com.abs.domain.AmbulanceBooking;
import com.abs.domain.mappers.AmbulanceBookingMapper;
import com.abs.service.AmbulanceBookingDAO;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
    public Integer createAmbulanceBookingGetId(Integer patientId, Integer createdBy, Integer destination, Integer origin,
                                           boolean cardiac, boolean urgent, String dateOfTransfer) {

        DateTime dt = new DateTime();
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm");
        String now = dtf.print(dt);

        String SQL = "INSERT INTO ambulancebooking (patientId, createdBy, destination, "
                + "origin, cardiac, urgent, creationDateTime, transferDateTime, archived, approvedBy) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?,'n',-1)";

        Object[] params=new Object[]{ patientId, createdBy, destination,origin,cardiac,urgent,now,dateOfTransfer};
        PreparedStatementCreatorFactory psc=new PreparedStatementCreatorFactory(SQL);
        psc.addParameter(new SqlParameter("patientId", Types.INTEGER));
        psc.addParameter(new SqlParameter("createdBy", Types.INTEGER));
        psc.addParameter(new SqlParameter("destination", Types.INTEGER));
        psc.addParameter(new SqlParameter("origin", Types.INTEGER));
        psc.addParameter(new SqlParameter("cardiac", Types.BOOLEAN));
        psc.addParameter(new SqlParameter("urgent", Types.BOOLEAN));
        psc.addParameter(new SqlParameter("creationDateTime", Types.VARCHAR));
        psc.addParameter(new SqlParameter("dateOfTransfer", Types.VARCHAR));

        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(psc.newPreparedStatementCreator(params), holder);

        String key=holder.getKey().toString();
        return Integer.parseInt(key);
    }

    @Override
    @Transactional
    public void deleteAmbulanceBooking(Integer id) {
        String SQL = "update ambulancebooking set archived = 'y' where id = ?";
        getJdbcTemplate().update(SQL, new Object[] {id});
        System.out.println("Archived ambulance booking where id: " + id );
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public AmbulanceBooking getBooking(Integer id) {
        String SQL = "select * from ambulancebooking where id = ? and archived = 'n'";
        AmbulanceBooking ab = (AmbulanceBooking) getJdbcTemplate().queryForObject(SQL,
                new Object[]{id}, new AmbulanceBookingMapper());
        return ab;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public List<AmbulanceBooking> getAllBookings() {
        String SQL = "select * from ambulancebooking where archived = 'n'";
        List<AmbulanceBooking> abList = getJdbcTemplate().query(SQL,
                new AmbulanceBookingMapper());
        return abList;
    }

    @Override
    public void setAmbulanceCompany(Integer id, Integer ambulanceCompanyId) {
        String SQL = "update ambulancebooking set ambCompanyId = ? where id = ?";
        getJdbcTemplate().update(SQL, new Object[] {ambulanceCompanyId,id});
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public List<AmbulanceBooking> getAllUnapprovedBookings() {
        String SQL = "select * from ambulancebooking where archived = 'n' AND approvedBy = -1 ORDER BY urgent DESC, transferDateTime ASC";
        List<AmbulanceBooking> abList = getJdbcTemplate().query(SQL,
                new AmbulanceBookingMapper());
        return abList;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public List<AmbulanceBooking> getNewBookingsForAmbCompany(Integer ambCompId) {
        String SQL = "select * from ambulancebooking where status != 4 AND status != 5 " +
                "AND archived = 'n' AND approvedBy != -1 AND " +
                "approved = true AND ambCompanyId = ? ORDER BY urgent DESC, transferDateTime ASC";
        List<AmbulanceBooking> abList = getJdbcTemplate().query(SQL,new Object[]{ambCompId},
                new AmbulanceBookingMapper());
        return abList;
    }

    @Override
    public List<AmbulanceBooking> getAllAmbCrewBookings(Integer ambCrewId) {
        String SQL = "select * from ambulancebooking where archived = 'n' AND approvedBy != -1 AND " +
                "approved = true AND ambCrewId = ? AND (status = 2 OR status = 3) ORDER BY urgent DESC, transferDateTime ASC";
        List<AmbulanceBooking> abList = getJdbcTemplate().query(SQL,new Object[]{ambCrewId},
                new AmbulanceBookingMapper());
        return abList;
    }

    @Override
    public void setApproval(Integer id, boolean approved,Integer approvedBy) {
        String SQL;
        if(approved == true)
            SQL = "update ambulancebooking set approved = ?, approvedBy = ?, status = 1 where id = ?";
        else
            SQL = "update ambulancebooking set approved = ?, approvedBy = ?, status = 4 where id = ?";

        getJdbcTemplate().update(SQL, new Object[] {approved,approvedBy,id});
    }

    @Override
    public void setAmbulanceCrew(Integer bookingId,Integer crewId) {
        String SQL = "update ambulancebooking set ambCrewId = ? where id = ?";
        getJdbcTemplate().update(SQL, new Object[] {crewId,bookingId});
    }

    @Override
    public void setStatus(Integer bookingId,Integer status) {
        String SQL = "update ambulancebooking set status = ? where id = ?";
        getJdbcTemplate().update(SQL, new Object[] {status,bookingId});
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Integer countRows() {
        String SQL = "select count(id) from ambulancebooking";
        Integer rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
        return rows;
    }
}