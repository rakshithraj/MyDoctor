package com.app.mydoctor.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.mydoctor.Fragement.DoctorFragment;
import com.app.mydoctor.Fragement.SpecialitiesFragement;


/**
 * Created by Rakshith on 11/3/2015.
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    String shopCategory[];

    public CategoryAdapter(FragmentManager fm, String shopCategory[]) {
        super(fm);

        this.shopCategory=shopCategory;

    }

    @Override
    public Fragment getItem(int position) {

        if(position==0)
            return SpecialitiesFragement.newInstance();
        else
            return DoctorFragment.newInstance();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return shopCategory[position % shopCategory.length].toString().toUpperCase();
    }


    public String checkString(String str){
        while(str.length()<=5){
            str=" "+str+" ";
        }

       return str;
    }

    @Override
    public int getCount() {
        return shopCategory.length;
    }
}