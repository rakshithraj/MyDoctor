package com.app.mydoctor.Fragement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.volley.NetworkResponse;

import com.app.mydoctor.Adapter.DoctorAdapter;
import com.app.mydoctor.AppInterface.AdapterInterface;
import com.app.mydoctor.AppInterface.ServerResponse;
import com.app.mydoctor.AppUtility.EndlessRecyclerOnScrollListener;
import com.app.mydoctor.DoctorDetailedActivity;
import com.app.mydoctor.R;
import com.app.mydoctor.WebService.ConnectWebService;
import com.app.mydoctor.dao.DoctorDetailInfo;
import com.app.mydoctor.dao.DoctorDetailInfoList;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public final class DoctorFragment extends Fragment implements ServerResponse, AdapterInterface {
    private static final String KEY_CONTENT = "DoctorFragment:Content";
    RecyclerView mRecyclerView;
    //ProgressBar progressBar;
    Context context;
    ArrayList<DoctorDetailInfo> myDataset = new ArrayList<DoctorDetailInfo>();
    DoctorAdapter mAdapter;
    private Handler handler;
    DoctorDetailInfoList newsDetailInfoList;
    View view;
    ;
    String url;
    String dep = "";
    int start = 0;
    int limit = 10;
    public boolean loading = false;

    final static String IMAGE = "image";
    final static String HEADLINE = "headline";
    final static String NEWS = "news";

    ConnectWebService connectWebService;

    public static DoctorFragment newInstance() {
        DoctorFragment fragment = new DoctorFragment();
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.category_adapter_page, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         connectWebService = new ConnectWebService();
        initialize();
        if (myDataset.size() <= 0) {
            if (!getLoading()) {

                if (newsDetailInfoList == null) {

                    getData();

                } else {
                   // progressBar.setVisibility(View.GONE);
                    setData();
                }
            }
        }// else
           // progressBar.setVisibility(View.GONE);

    }

    private void getData() {

       /* if(!Utitlity.isNetworkAvailable_UI(this.getActivity()))
             return;


        myDataset.add(null);
        mAdapter.notifyItemInserted(myDataset.size());


        url = AppConfig.OFFER_URL + "start=" + start + "&end=" + (start + limit);

        connectWebService.setOnServerResponse(this);
        connectWebService.stringGetRequest(url, this.getActivity(), false);

        setLoading(true);
*/

    }

    private void setData() {


        myDataset.addAll(0, newsDetailInfoList.getOfferDetailInfo());
        mAdapter.notifyDataSetChanged();


       //progressBar.setVisibility(View.GONE);

    }
   // RecyclerView mPtrFrame;
    private void initialize() {
        //progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        handler = new Handler();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);



        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {

                loadMore();
            }
        });
        //dumy dat
        DoctorDetailInfo doctorDetailInfo;
        doctorDetailInfo=new DoctorDetailInfo();
        doctorDetailInfo.setDoctor_name("rakshith");
        myDataset.add(doctorDetailInfo);
        doctorDetailInfo=new DoctorDetailInfo();
        doctorDetailInfo.setDoctor_name("rakshith");
        myDataset.add(doctorDetailInfo);
        doctorDetailInfo=new DoctorDetailInfo();
        doctorDetailInfo.setDoctor_name("rakshith");
        myDataset.add(doctorDetailInfo);
        doctorDetailInfo=new DoctorDetailInfo();
        doctorDetailInfo.setDoctor_name("rakshith");
        myDataset.add(doctorDetailInfo);
        //dumy dat

        mAdapter = new DoctorAdapter(myDataset, this);
        mRecyclerView.setAdapter(mAdapter);



    }











    private void loadMore() {
        if (getLoading())
            return;

        getData();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //   outState.putString(KEY_CONTENT, mContent);
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onServerResponse(String result) {


        if (myDataset.size() > 0) {
            myDataset.remove(myDataset.size() - 1);
            mAdapter.notifyItemRemoved(myDataset.size());
        }

        //progressBar.setVisibility(View.GONE);

        result = formatResult(result);


        if (result != null) {
            result = "{ \"offerDetailInfo\":" + result + "}";
            Gson gson = new Gson();

            try {
                newsDetailInfoList = gson.fromJson(result, DoctorDetailInfoList.class);
            } catch (JsonSyntaxException e) {

            }
            if (newsDetailInfoList != null) {
                start = start + newsDetailInfoList.getOfferDetailInfo().size();


                setData();
                newsDetailInfoList = null;


            }

        }



    }


    private String formatResult(String result) {
        String temp;

        Pattern pattern = Pattern.compile("\"Image\":\\{(.*?)\"\\}");
        Matcher matcher = pattern.matcher(result);

        while (matcher.find()) {
            // Log.d(QuickNewsConstant.TAG, matcher.group());
            temp = matcher.group();
            temp = temp.replace("{", "[{");
            temp = temp.replace("}", "}]");
            result = result.replace(matcher.group().toString(), temp);


        }


        pattern = Pattern.compile("\"Video\":\\{(.*?)\"\\}");
        matcher = pattern.matcher(result);


        while (matcher.find()) {
            // Log.d(QuickNewsConstant.TAG, matcher.group());
            temp = matcher.group();
            temp = temp.replace("{", "[{");
            temp = temp.replace("}", "}]");
            result = result.replace(matcher.group(), temp);

        }


        return result;
    }

    @Override
    public void onServerError(String result) {
       // progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    @Override
    public boolean getLoading() {
        return loading;
    }

    @Override
    public void parseNetworkResponse(NetworkResponse response) {

    }


    @Override
    public void onItem(int position, Object object) {

          Intent intent=new Intent(this.getActivity(),DoctorDetailedActivity.class);
           this.getActivity().startActivity(intent);
    }
}
