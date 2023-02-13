package com.example.myapplication;

import static com.example.myapplication.R.id.dpImage;
import static com.example.myapplication.R.id.dpPrice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.db.AddProducts;
import com.example.myapplication.db.AppData;
import com.example.myapplication.db.UserDAO;
import com.squareup.picasso.Picasso;

public class DetailedProduct extends AppCompatActivity {

    products products=null;
    public TextView txtPrice,txtTitle,txtDes;
    ImageView imagedp;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_product);

        imagedp = findViewById(R.id.dpImage);
        txtPrice = findViewById(R.id.dpPrice);
        txtTitle = findViewById(R.id.dpTitle);
        txtDes = findViewById(R.id.dpDescr);

        Button addcart = (Button) findViewById(R.id.addcart);

        final Object obj=getIntent().getSerializableExtra("Products");
        if(obj instanceof products){
            products=(products)obj;
        }
        //  Picasso.get().load(products.getImage()).into(holder.imageT);
        if(products!=null) {
            Picasso.get().load(products.getImage()).into(imagedp);
            txtPrice.setText(products.getPrice());
            txtTitle.setText(products.getTitle());
            txtDes.setText(products.getDescription());


        }
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new addcart().start();
                Toast.makeText(getApplicationContext(), "product added", Toast.LENGTH_SHORT).show();
            }
        });
        //txtPrice.setText(products.getPrice());

    }
    class addcart extends Thread{
        public void run(){
            super.run();
            AppData db= Room.databaseBuilder(getApplicationContext(),AppData.class,"AddProducts").build();
            UserDAO userDao=db.userDao();
            userDao.insertUser(new AddProducts(1,txtPrice.getText().toString(), txtTitle.getText().toString()));
            //txtPrice.setText("");
            //txtTitle.setText("");
           //Toast.makeText(getApplicationContext(), "product added", Toast.LENGTH_SHORT).show();
        }
    }
}