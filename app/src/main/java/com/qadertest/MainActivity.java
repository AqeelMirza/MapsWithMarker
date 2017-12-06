package com.qadertest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ArrayList<Restaurant_ResponseModel> restaurant_responseModelArrayList;
    Restaurant_ResponseModel restaurant_responseModel;
    ListView listView;
    ProgressDialog pDialog;
    String Branch="Commit from branch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.home_listview);

        webServiceCall();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(MainActivity.this, MapsActivity.class);
                in.putParcelableArrayListExtra("arrayData", restaurant_responseModelArrayList);
                startActivity(in);

            }
        });
    }


    void webServiceCall() {


        String url = "http://sandbox.bottlerocketapps.com/BR_iOS_CodingExam_2015_Server/restaurants.json";

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest jsonObjReq = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("", response.toString());
                        pDialog.hide();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray restaurants_array = jsonObject.getJSONArray("restaurants");
                            restaurant_responseModelArrayList = new ArrayList<>();
                            for (int i = 0; i < restaurants_array.length(); i++) {
                                restaurant_responseModel = new Restaurant_ResponseModel();

                                JSONObject rest_obj = restaurants_array.getJSONObject(i);

                                String name = rest_obj.getString("name");
                                String cat = rest_obj.getString("category");

                                JSONObject loc_obj = rest_obj.getJSONObject("location");
                                String lat = loc_obj.getString("lat");
                                String lon = loc_obj.getString("lng");

                                restaurant_responseModel.setName(name);
                                restaurant_responseModel.setCategory(cat);
                                restaurant_responseModel.setLat(lat);
                                restaurant_responseModel.setLon(lon);

                                restaurant_responseModelArrayList.add(restaurant_responseModel);
                            }

                            Activity activity = MainActivity.this;
                            HomeAdapter homeAdapter = new HomeAdapter(activity, restaurant_responseModelArrayList);
                            listView.setAdapter(homeAdapter);

                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                // hide the progress dialog
                pDialog.hide();
                Toast.makeText(MainActivity.this, "Error Occurred.Please try again later.", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
