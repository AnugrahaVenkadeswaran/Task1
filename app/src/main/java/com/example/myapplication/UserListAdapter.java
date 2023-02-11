package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.db.AddProducts;

import java.util.List;

public class UserListAdapter extends  RecyclerView.Adapter<UserListAdapter.MyViewHolder>{
    private Context context;
    private List<AddProducts> addProductsList1;


    public UserListAdapter(Context context) {
        this.context = context;
    }

    public void setUserList(List<AddProducts> addProductsList1) {
        this.addProductsList1 = addProductsList1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.MyViewHolder holder, int position) {

        holder.pPrice.setText(this.addProductsList1.get(position).getPrice());
        holder.pTitle.setText(this.addProductsList1.get(position).getTitle());
      //  holder.pImage.setImageResource(this.productsList1.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return  this.addProductsList1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView pTitle,pPrice;
        ImageView pImage;

        public MyViewHolder(View view) {
            super(view);
            pTitle = view.findViewById(R.id.prodTitle);
            pPrice = view.findViewById(R.id.prodPrice);
            pImage=view.findViewById(R.id.pImage);

        }
    }
}
