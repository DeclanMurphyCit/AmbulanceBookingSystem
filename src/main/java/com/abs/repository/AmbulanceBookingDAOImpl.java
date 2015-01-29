package com.abs.repository;

import com.abs.domain.AmbulanceBooking;
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
    public int createAmbulanceBookingGetId(int patientId, int createdBy, int destination, int origin, boolean cardiac, boolean urgent, String dateOfTransfer) {

            String SQL = "INSERT INTO ambulancebooking (patientId, createdBy, destination, "
                    + "origin, cardiac, urgent, creationDateTime, transferDateTime"
                    + "VALUES(?, ?, ?, ?, ?, ?, getDate(),?))";

            Object[] params=new Object[]{ firstName, lastName, studentNumber,email,phoneNumber,addressLine1,addressLine2};
            PreparedStatementCreatorFactory psc=new PreparedStatementCreatorFactory(SQL);
            psc.addParameter(new SqlParameter("firstName", Types.VARCHAR));
            psc.addParameter(new SqlParameter("lastName", Types.VARCHAR));
            psc.addParameter(new SqlParameter("studentNumber", Types.VARCHAR));
            psc.addParameter(new SqlParameter("email", Types.VARCHAR));
            psc.addParameter(new SqlParameter("phoneNumber", Types.VARCHAR));
            psc.addParameter(new SqlParameter("addressLine1", Types.VARCHAR));
            psc.addParameter(new SqlParameter("addressLine2", Types.VARCHAR));

            KeyHolder holder = new GeneratedKeyHolder();
            getJdbcTemplate().update(psc.newPreparedStatementCreator(params), holder);

            System.out.println("holder:"+holder.getKey().toString());
            String key=holder.getKey().toString();
            return Integer.parseInt(key);
        }

    @Override
    public void deleteAmbulanceBooking(int id) {

    }

    @Override
    public AmbulanceBooking getBooking(int id) {
        return null;
    }

    @Override
    public List<AmbulanceBooking> getAllBookings() {
        return null;
    }

    @Override
    public void setAmbulanceCompany(int id, int ambulanceCompanyId) {

    }

    @Override
    public void setApproval(int id, boolean approval) {

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public int countRows() {
        String SQL = "select count(id) from ambulancebooking";
        int rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
        return rows;
    }
}