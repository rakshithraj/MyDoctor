package com.app.mydoctor;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import com.app.mydoctor.Adapter.CategoryAdapter;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView mRecyclerView;
    ViewPager pager;
    FragmentPagerAdapter adapter;
    String[] types = {"specialist", "doctor"};
    ImageView speciality, doctor;
   final int SPECIALISTIES=0,DOCTOR=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapter = new CategoryAdapter(getSupportFragmentManager(), types);
        intialize();

    }


    private void intialize() {
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                if (DOCTOR==position){
                    doctor.setImageResource(R.mipmap.doctor_selected);
                    speciality.setImageResource(R.mipmap.specialities);
                }else{
                    doctor.setImageResource(R.mipmap.doctors);
                    speciality.setImageResource(R.mipmap.speciality_selected);
                }
            }
        });
        speciality = (ImageView) findViewById(R.id.speciality);
        speciality.setOnClickListener(this);
        doctor = (ImageView) findViewById(R.id.doctor);
        doctor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.doctor:
                doctor.setImageResource(R.mipmap.doctor_selected);
                speciality.setImageResource(R.mipmap.specialities);
                pager.setCurrentItem(DOCTOR);
                break;
            case R.id.speciality:
                doctor.setImageResource(R.mipmap.doctors);
                speciality.setImageResource(R.mipmap.speciality_selected);
                pager.setCurrentItem(SPECIALISTIES);
                break;
        }
    }
}
