package com.app.mydoctor.dao;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rakshith on 10/27/2015.
 */
public class DoctorDetailInfoList implements Serializable {


    public ArrayList<DoctorDetailInfo> getOfferDetailInfo() {
        return offerDetailInfo;
    }

    public void setOfferDetailInfo(ArrayList<DoctorDetailInfo> offerDetailInfo) {
        this.offerDetailInfo = offerDetailInfo;
    }

    ArrayList<DoctorDetailInfo> offerDetailInfo=new ArrayList<DoctorDetailInfo>();
}
