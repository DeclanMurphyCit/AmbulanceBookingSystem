package com.abs.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Declan on 20/01/2015.
 */

@PlanningSolution
@XStreamAlias("PlanningSol")
public class PlanningSol implements Solution <HardSoftScore> {

    private List<AmbulanceCompany> acList;
    private List <AmbulanceBooking> abList;
    private HardSoftScore score;

    @ValueRangeProvider(id = "acRange")
    public List<AmbulanceCompany> getAcList() {
        return acList;
    }

    public void setAcList(List<AmbulanceCompany> acList) {
        this.acList = acList;
    }

    @PlanningEntityCollectionProperty
    public List<AmbulanceBooking> getAbList() {
        return abList;
    }

    public void setAbList(List<AmbulanceBooking> abList) {
        this.abList = abList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    public Collection<? extends Object> getProblemFacts() {

        List<Object> facts = new ArrayList<Object>();
        facts.addAll(acList);
        return facts;
    }
}
