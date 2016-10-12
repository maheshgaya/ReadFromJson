package com.maheshgaya.android.readfromjson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mahesh Gaya on 10/11/16.
 *
 */
public class MainFragment extends Fragment {
    private City[] mCities = {}; //intialize
    private CityAdapter mCityAdapter; //use adapter to populate ListView
    private ListView mListView;

    public MainFragment(){
        //empty constructor required
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true); //this preserves the state of the fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mCityAdapter = new CityAdapter(getActivity(), mCities);
        mListView = (ListView) rootView.findViewById(R.id.listview_main);
        mListView.setAdapter(mCityAdapter);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        //get cities
        updateCities();
    }

    public void updateCities(){
        FetchCityTask fetchCityTask = new FetchCityTask();
        fetchCityTask.execute();
    }

    /**
     * Get the cities in a background thread
     * This improves UX
     */
    //AsyncTask<params, progress, result>
    public class FetchCityTask extends AsyncTask<Void, Void, City[]>{

        private final String LOG_TAG = FetchCityTask.class.getSimpleName();
        @Override
        protected City[] doInBackground(Void... voids) {
            //get the cities from CityJsonReader
            List<City> cityList = new CityJsonReader(getActivity()).getCityList();
            //copy cityList into an Array
            City[] cities = cityList.toArray(new City[cityList.size()]);

            return cities;
        }

        @Override
        protected void onPostExecute(City[] results) {
            //if the results from the JSON is not empty, populate the ListView
            if (results != null){
                mCities = results;
                try {
                    mCityAdapter = new CityAdapter(getActivity(), mCities);
                    mCityAdapter.notifyDataSetChanged(); //callback to update the data in the adapter
                    mListView.setAdapter(mCityAdapter); //attach the adapter to the listView
                } catch (UnsupportedOperationException e) {
                    Log.e(LOG_TAG, "onPostExecute: ", e);
                    e.printStackTrace();
                }

            }
        }
    }
}
