package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.db.AppData;
import com.example.myapplication.db.AddProducts;
import com.example.myapplication.db.UserDAO;

import java.util.List;

public class AddProduct extends AppCompatActivity {

    Button newProd;
    TextView cartTitle,cartPrice;
    RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        newProd=(Button) findViewById(R.id.addNewProduct);

        getroomdata();


        newProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(AddProduct.this, MainActivity.class));
            }
        });

    }

    private void getroomdata() {
        AppData db= Room.databaseBuilder(getApplicationContext(),AppData.class,"room_db").allowMainThreadQueries().build();
        UserDAO userDao=db.userDao();

        recyclerView=(RecyclerView) findViewById(R.id.rvProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<AddProducts> addProducts=userDao.getAllUsers();
        UserListAdapter userListAdapter=new UserListAdapter(addProducts);
        recyclerView.setAdapter(userListAdapter);

    }


    public void onBackPressed(){
        Intent intent=new Intent(AddProduct.this,MainActivity.class);
        startActivity(intent);
    }
}