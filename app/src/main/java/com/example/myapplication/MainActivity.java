package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.db.AddProducts;
import com.example.myapplication.db.AppData;
import com.example.myapplication.db.UserDAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<products> productsArrayList;
    private ProductsAdapter productsAdapter;
    private RecyclerView productsRV;
    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;
    int page = 1, limit = 5;
    private static int cart_count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsArrayList = new ArrayList<>();
        productsRV = findViewById(R.id.idRVUsers);
        loadingPB = findViewById(R.id.idPBLoading);
        nestedSV = findViewById(R.id.idNestedSV);
     


        productsAdapter = new ProductsAdapter(productsArrayList, getApplicationContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(productsRV.getContext(), linearLayoutManager.getOrientation());

        productsRV.setHasFixedSize(true);
        productsRV.setLayoutManager(linearLayoutManager);
        productsRV.addItemDecoration(dividerItemDecoration);
        productsRV.setAdapter(productsAdapter);
        getDataFromAPI(page, limit);

        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {

                    page++;
                    loadingPB.setVisibility(View.VISIBLE);
                    getDataFromAPI(page, limit);
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                //fetchAddcart();
                startActivity(new Intent(getApplicationContext(),AddProduct.class));
                return true;
            case R.id.action_settings:
               // startSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fetchAddcart() {
        AppData db= Room.databaseBuilder(getApplicationContext(),AppData.class,"room_db").allowMainThreadQueries().build();
        UserDAO userDao=db.userDao();

        List<AddProducts> addProducts=userDao.getAllUsers();
        String str="";
        for(AddProducts addProduct :addProducts)


       str= str+"\t"+addProduct.getId()+""+addProduct.getPrice()+""+addProduct.getTitle();


    }


    private void getDataFromAPI(int page, int limit) {
        if (page > limit) {

            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();
            loadingPB.setVisibility(View.GONE);
            return;
        }
        String url = "https://fakestoreapi.com/products";
        //JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
             //   new Response.Listener<JSONArray>(){
       // JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
      // JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
              // new Response.Listener<JSONArray>()
        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                // Log.i("Response", String.valueOf(response));
                                productsArrayList.add(new products(jsonObject.getString("title"), jsonObject.getString("price"), jsonObject.getString("category"), jsonObject.getString("image"),jsonObject.getString("description")));
                                productsAdapter = new ProductsAdapter(productsArrayList, MainActivity.this);
                                productsRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                productsRV.setAdapter(productsAdapter);
                                productsAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        }
                        },new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("Volley", error.toString());
                                    //  progressDialog.dismiss();
                                }
                            });

                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                            request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                         DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            requestQueue.add(request);
                        }



    /*private void getDataFromAPI(int page, int limit) {
        if (page > limit) {

            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();
            loadingPB.setVisibility(View.GONE);
            return;
        }

        String url = "https://fakestoreapi.com/products/5";

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(JSONObject response) {
                try {


                    JSONArray dataArray = response.getJSONArray("");

                    for (int i = 1; i < dataArray.length(); i++) {
                        JSONObject jsonObject = dataArray.getJSONObject(i);

                        Log.i("Response", String.valueOf(response));
                        productsArrayList.add(new products( jsonObject.getString("title"), jsonObject.getString("price"), jsonObject.getString("category"), jsonObject.getString("avatar")));
                        //productsArrayList.add(new products(jsonObject.getString("title"), jsonObject.getString("price"), jsonObject.getString("category"), jsonObject.getString("image")));

                        productsAdapter = new ProductsAdapter(productsArrayList, MainActivity.this);
                        productsRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));                        productsRV.setAdapter(productsAdapter);
                        productsAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handling on error listener method.
                Toast.makeText(MainActivity.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonObjectRequest);
    }*/
                    }