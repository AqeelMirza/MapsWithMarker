package com.qadertest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class HomeAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private Activity activity;
    ArrayList<Restaurant_ResponseModel> restaurant_responseModelArrayList;

    public HomeAdapter(Activity a, ArrayList<Restaurant_ResponseModel> restaurant_responseModelArrayList) {
        this.restaurant_responseModelArrayList = restaurant_responseModelArrayList;
        activity = a;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return restaurant_responseModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.home_listview_items, null);

        TextView name = (TextView) vi.findViewById(R.id.name_tv);
        TextView cat = (TextView) vi.findViewById(R.id.category_tv);


        // Setting all values in listview
        name.setText(restaurant_responseModelArrayList.get(i).getName());
        cat.setText(restaurant_responseModelArrayList.get(i).getCategory());

        return vi;
    }
}
