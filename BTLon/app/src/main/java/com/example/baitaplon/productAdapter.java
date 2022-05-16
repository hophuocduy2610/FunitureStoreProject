package com.example.baitaplon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ViewHolder> {
    private ArrayList<Product> productArrayList;
    private Context context;
    int lastPos = -1;
    private ProductClickInterface productClickInterface;

    public productAdapter(ArrayList<Product> productArrayList, Context context, ProductClickInterface productClickInterface) {
        this.productArrayList = productArrayList;
        this.context = context;
        this.productClickInterface = productClickInterface;
    }

    @NonNull
    @Override
    public productAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Product product = productArrayList.get(position);
        holder.productName.setText(product.getName());
        holder.manufacture.setText(product.getManufacture());
        holder.price.setText(product.getPrice());
        Picasso.get().load(product.getImage()).into(holder.img);
        setAnimation(holder.itemView, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productClickInterface.onProductClick(position);
            }
        });
    }
    private void setAnimation(View itemView, int position){
        if(position > lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }
    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView productName,manufacture, price;
        private ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.textviewProductName);
            manufacture = itemView.findViewById(R.id.textViewManufacture);
            price = itemView.findViewById(R.id.textViewPrice);
            img = itemView.findViewById(R.id.imageViewProduct);
        }
    }

    public interface ProductClickInterface{
        void onProductClick(int position);
    }
}
