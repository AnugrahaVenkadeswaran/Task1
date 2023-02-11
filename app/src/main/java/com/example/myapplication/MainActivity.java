package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
       // MenuItem menuItem = menu.findItem(R.id.cart_action);
     //   menuItem.setIcon(Converter.convertLayoutToImage(MainActivity.this,cart_count,R.drawable.cart));
        MenuItem menuItem2 = menu.findItem(R.id.notification_action);
        menuItem2.setIcon(Converter.convertLayoutToImage(MainActivity.this,2, R.drawable.cart));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


    private void getDataFromAPI(int page, int limit) {
        if (page > limit) {

            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();
            loadingPB.setVisibility(View.GONE);
            return;
        }
        String url = "https://fakestoreapi.com/products/";
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
                                productsArrayList.add(new products(jsonObject.getString("title"), jsonObject.getString("price"), jsonObject.getString("category"), jsonObject.getString("image")));
                                productsAdapter = new ProductsAdapter(productsArrayList, MainActivity.this);
                                productsRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                productsRV.setAdapter(productsAdapter);
                                productsAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }


                /*    @Override
                   public void onResponse(JSONArray response) {


                Log.i(String.valueOf(response),"jdnasdsdn");
                for (int i =1; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        productsArrayList.add(new products( jsonObject.getString("title"),
                                jsonObject.getString("price"),
                                jsonObject.getString("category"),
                                jsonObject.getString("image")));
                      /*  products products1 = new products();
                        products1.setTitle(jsonObject.getString("title"));
                        products1.setCategory(jsonObject.getString("category"));
                        products1.setPrice(jsonObject.getString("price"));
                        products1.setImage(jsonObject.getString("image"));*/
                       /* productsAdapter = new ProductsAdapter(productsArrayList, MainActivity.this);
                        productsRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        productsAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

               // progressDialog.dismiss();*/

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