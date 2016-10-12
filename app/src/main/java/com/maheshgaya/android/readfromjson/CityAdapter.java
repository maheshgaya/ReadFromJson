package com.maheshgaya.android.readfromjson;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mahesh Gaya on 10/11/16.
 * This adapter displays the text from an array to the ListView
 */
public class CityAdapter extends BaseAdapter {
    private final String TAG = CityAdapter.class.getSimpleName();
    private Activity mContext;
    private List<City> mCityList;
    private static LayoutInflater mInflater = null;

    public CityAdapter(Activity context, City[] cities){
        mContext = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCityList = Arrays.asList(cities);
    }
    @Override
    public int getCount() {
        return mCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCityList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.listview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final City city = mCityList.get(position);

        viewHolder.txtCityName.setText(city.getCityName());
        viewHolder.txtCountyName.setText(city.getCounty());
        String longitude = mContext.getResources().getString(R.string.longitude) +
                city.getLongitude();
        viewHolder.txtLongitude.setText(longitude);
        String latitude = mContext.getResources().getString(R.string.latitude) +
                city.getLatitude();
        viewHolder.txtLatitude.setText(latitude);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, city.getCityName() + " tapped!", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    /**
     * WHY THIS?
     * Finding the ids in R.java (i.e. R.id.list_item_city_name) is tedious for the compiler,
     * especially in the case of ListView, because sometimes there can be over 1000 elements
     * in the R.java file and the compiler will have to search for that each time
     * it has a new child in the list view.
     *
     * In this case, it is better to have a static class that has already queried all the resources that you
     * need and it just stores them in memory. You can just them as many times as needed within the scope of
     * where it is defined.
     *
     * What is R?
     * R.java is a file that is built when you run your Android app. It is basically a list of constants.
     * R stores all the resources that you define here in the xml files as integer constants.
     * This speeds up the process of querying for the resources and removes ambiguity for the compiler
     */
    public static class ViewHolder{
        TextView txtCityName;
        TextView txtCountyName;
        TextView txtLongitude;
        TextView txtLatitude;
        private ViewHolder(View view){
            txtCityName = (TextView) view.findViewById(R.id.list_item_city_name);
            txtCountyName = (TextView) view.findViewById(R.id.list_item_county_name);
            txtLongitude = (TextView) view.findViewById(R.id.list_item_longitude);
            txtLatitude = (TextView) view.findViewById(R.id.list_item_latitude);
        }

    }
}
