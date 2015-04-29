package com.abs.service.broker;

import com.abs.domain.AmbulanceCompany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Declan on 16/04/2015.
 */
public class BrokerImpl implements Broker {

    public BrokerImpl(){}

    @Override
    public AmbulanceCompany getOptimalCompany(List<AmbulanceCompany> ambulanceCompanyList, Boolean isCardiac, Boolean isUrgent) {

        List<AmbulanceCompany> ambCompListCopy = new ArrayList<AmbulanceCompany>();

        AmbulanceCompany optimal;
        for(AmbulanceCompany ac : ambulanceCompanyList)
        {
            if(isCardiac == true) {
                if(ac.isCardiac() == true) ambCompListCopy.add(ac);
            } else if(isCardiac == false) {
                ambCompListCopy.add(ac);
            }


            /*if(isUrgent == true){
                //TODO Get ambulance company who takes the shortest amount of time on average
            }*/
        }

        AmbulanceCompany[] acList = ambCompListCopy.toArray(new AmbulanceCompany[ambCompListCopy.size()]);
        for(AmbulanceCompany a: acList)
            System.out.println(a.getCost());

        Arrays.sort(acList);

        for(AmbulanceCompany a: acList)
            System.out.println(a.getCost());

        optimal = acList[0];

        return optimal;
    }

    @Override
    public AmbulanceCompany getSecondOptimalCompany(List<AmbulanceCompany> ambulanceCompanyList,
                                                    Boolean isCardiac, Boolean isUrgent,Integer cancelledAmbCompId) {

        List<AmbulanceCompany> ambCompListCopy = new ArrayList<AmbulanceCompany>();
        AmbulanceCompany optimal;
        for(AmbulanceCompany ac : ambulanceCompanyList)
        {
            if(isCardiac == true) {

                if(ac.isCardiac() == true && ac.getId() != cancelledAmbCompId) ambCompListCopy.add(ac);
            } else if(isCardiac == false  && ac.getId() != cancelledAmbCompId) {
                ambCompListCopy.add(ac);
            }
            /*if(isUrgent == true){
                //TODO Get ambulance company who takes the shortest amount of time on average
            }*/
        }

        AmbulanceCompany[] acList = ambCompListCopy.toArray(new AmbulanceCompany[ambCompListCopy.size()]);
        Arrays.sort(acList);
        optimal = acList[0];

        return optimal;
    }
}
