package com.abs.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Declan on 20/01/2015.
 */

@PlanningSolution
@XStreamAlias("PlanningSol")
public class PlanningSol implements Solution <SimpleScore> {

    private List<AmbulanceCompany> acList;
    private List<AmbulanceBooking> abList;
    private SimpleScore score;

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

    public SimpleScore getScore() {
        return score;
    }

    public void setScore(SimpleScore score) {
        this.score = score;
    }

    public Collection<? extends Object> getProblemFacts() {

        List<Object> facts = new ArrayList<Object>();
        facts.addAll(acList);
        return facts;
    }
}
