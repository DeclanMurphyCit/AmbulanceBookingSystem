package com.abs.broker;

import com.abs.domain.AmbulanceBooking;
import com.abs.domain.AmbulanceCompany;
import com.abs.domain.PlanningSol;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Declan on 22/01/2015.
 */
public class BrokerDataGenerator {

    protected Random r;

    public PlanningSol createPlanningSol(int numCompanies) {

        PlanningSol planningSol = new PlanningSol();
        planningSol.setAbList(createBooking());
        planningSol.setAcList(createCompaniesList(numCompanies));
        return planningSol;
    }

    public List<AmbulanceBooking> createBooking()
    {
        List<AmbulanceBooking> ambBookingList = new ArrayList<AmbulanceBooking>(1);
        AmbulanceBooking ab = new AmbulanceBooking();
        ab.setBookingId(0);
        ab.setCardiac(true);
        ab.setCost(1500.00);

        AmbulanceCompany ac = new AmbulanceCompany();
        ac.setPrice(9999.00);
        ab.setAmbCompany(ac);

        ambBookingList.add(ab);

        return ambBookingList;
    }

    public List<AmbulanceCompany> createCompaniesList(int numCompanies)
    {
        List<AmbulanceCompany> ambCompanyList = new ArrayList<AmbulanceCompany>(numCompanies);
        r = new Random();

        for(int i = 0; i < numCompanies ; i++)
        {
            AmbulanceCompany ac = new AmbulanceCompany();
            ac.setId(i);
            //ac.setCapacity(r.nextInt(3)+1);
            ac.setCardiac(r.nextBoolean());
            ac.setName("BOOKING-"+i);
            ac.setPrice(750 + (2250) * r.nextDouble());
            ambCompanyList.add(ac);
        }

        return ambCompanyList;

    }
}
