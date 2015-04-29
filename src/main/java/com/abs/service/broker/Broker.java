package com.abs.service.broker;

import com.abs.domain.AmbulanceCompany;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Declan on 16/04/2015.
 */

@Service
public interface Broker {

    public AmbulanceCompany getOptimalCompany(List<AmbulanceCompany> ambulanceCompanyList, Boolean isCardiac,Boolean urgent);

    public AmbulanceCompany getSecondOptimalCompany(List<AmbulanceCompany> ambulanceCompanyList,
                                                    Boolean isCardiac,Boolean urgent, Integer cancelledAmbCompId);


}
