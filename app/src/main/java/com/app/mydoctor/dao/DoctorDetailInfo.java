package com.app.mydoctor.dao;

import java.io.Serializable;

/**
 * Created by Rakshith on 10/27/2015.
 */
public class DoctorDetailInfo implements Serializable {

    String doctor_id;
    String doctor_name;


    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }


}
