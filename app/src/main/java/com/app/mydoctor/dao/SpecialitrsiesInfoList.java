package com.app.mydoctor.dao;

import java.util.ArrayList;

/**
 * Created by Rakshith on 12/14/2015.
 */
public class SpecialitrsiesInfoList {
    public ArrayList<SpecialitrsiesInfo> getOfferDetailInfo() {
        return specialitrsiesInfo;
    }

    public void setOfferDetailInfo(ArrayList<DoctorDetailInfo> offerDetailInfo) {
        this.specialitrsiesInfo = specialitrsiesInfo;
    }

    ArrayList<SpecialitrsiesInfo> specialitrsiesInfo=new ArrayList<SpecialitrsiesInfo>();
}
