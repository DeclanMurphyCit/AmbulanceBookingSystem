package com.abs.domain;

/**
 * Created by Declan on 20/04/2015.
 */
public class CrewUser {

    AmbulanceCrew ambulanceCrew;
    UserObj userObj;

    public AmbulanceCrew getAmbulanceCrew() {
        return ambulanceCrew;
    }

    public void setAmbulanceCrew(AmbulanceCrew ambulanceCrew) {
        this.ambulanceCrew = ambulanceCrew;
    }

    public UserObj getUserObj() {
        return userObj;
    }

    public void setUserObj(UserObj userObj) {
        this.userObj = userObj;
    }
}
