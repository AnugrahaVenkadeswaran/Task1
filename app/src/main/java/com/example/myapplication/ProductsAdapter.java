package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.db.AppData;
import com.example.myapplication.db.AddProducts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder>{


    private final ArrayList<products> productsArrayList;
    private Context context;


    public ProductsAdapter(ArrayList<products> productsArrayList, Context context) {
        this.productsArrayList = productsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        products products1 = productsArrayList.get(position);

        holder.titleT.setText(products1.getTitle());
        holder.priceT.setText(products1.getPrice());
        holder.categoryT.setText(products1.getCategory());

        Picasso.get().load(products1.getImage()).into(holder.imageT);
    }

    @Override
    public int getItemCount() {

        return productsArrayList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleT, priceT, categoryT;
        private ImageView imageT;

        private Button addCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleT = itemView.findViewById(R.id.title);
            priceT = itemView.findViewById(R.id.pPrice);
            categoryT = itemView.findViewById(R.id.pCategory);
            imageT = itemView.findViewById(R.id.pImage);
            addCart=itemView.findViewById(R.id.addcart);

            addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addProducts(titleT.getText().toString(),priceT.getText().toString(),imageT.getContext().toString());
                }
            });
        }


    }

    private void addProducts(String title, String price, String image) {
        AppData db  = AppData.getDBInstance(this.context.getApplicationContext());

        AddProducts addProducts = new AddProducts();
        addProducts.title = title;
        addProducts.price = price;
        addProducts.image = image;
        db.userDAO().insertUser(addProducts);

        finish(0);

    }

    private void finish(int i) {
    }


}