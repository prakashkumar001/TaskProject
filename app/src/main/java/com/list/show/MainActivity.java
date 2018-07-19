package com.list.show;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.list.show.adapter.DataListAdapter;
import com.list.show.network.FileUploader;
import com.list.show.network.NetworkUtils;
import com.list.show.network.WSUtils;
import com.list.show.pojo.Entity;
import com.list.show.pojo.UserId;


import org.joda.time.Period;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

RecyclerView dataListView;
DataListAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //to intialise a view

        intialise();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        if(NetworkUtils.isNetworkAvailable(MainActivity.this)){
            //CONNECTED
            loadHeroList();


        }else {
            Toast.makeText(MainActivity.this,"No Internet Connection.. Please enable Internet",Toast.LENGTH_SHORT).show();

        }




        }




    void intialise()
    {
        dataListView=(RecyclerView)findViewById(R.id.dataList);
    }




    private void loadHeroList() {

        //making the progressbar visible

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://35.164.28.104:8080/alertmobile/getallpopularalerts?userId=623&latitude=13.0827&longitude=80.2707",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        List<Entity> rootList =new ArrayList<>();

                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray heroArray = obj.getJSONArray("_entity");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                Entity entity=new Entity();
                                JSONObject object=heroObject.getJSONObject("userId");
                               String id= object.getString("userId");
                               String fname=object.getString("firstName");
                                entity.setUserId(new UserId(Integer.parseInt(id),fname));
                               entity.setCreatedOn(heroObject.getString("created_On"));
                               entity.setFileUrl(heroObject.getString("fileUrl"));
                               entity.setAddress(heroObject.getString("address"));
                               int commentCount= heroObject.getInt("commentCount");
                               entity.setCommentCount(commentCount);



                                rootList.add(entity);
                             
                            }

                            adapter=new DataListAdapter(rootList);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                            dataListView.setLayoutManager(linearLayoutManager);
                            dataListView.setItemAnimator(new DefaultItemAnimator());
                            dataListView.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
                            dataListView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer 044f910c-f202-482c-9119-72509e6f838a");

                return params;
            }
        };

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);



    }

}
