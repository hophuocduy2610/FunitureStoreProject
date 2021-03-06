package com.example.furniturestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class product_page extends AppCompatActivity implements productAdapter.ProductClickInterface {
    private RecyclerView productView;
    private Button btn_add;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<Product> productList;
    private ConstraintLayout productPage;
    private ConstraintLayout proItem;
    private productAdapter productAdapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        productView = findViewById(R.id.recyclerView);
        productPage = findViewById(R.id.productPage);
        proItem = findViewById(R.id.productItem);
        btn_add = findViewById(R.id.btn_addProduct);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("products");
        productList = new ArrayList<>();
        productAdapt = new productAdapter(productList, this, this);
        productView.setLayoutManager(new LinearLayoutManager(this));
        productView.setAdapter(productAdapt);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(product_page.this, add_page.class));
            }
        });

        getAllProducts();
    }

    private void getAllProducts(){
        productList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                productList.add(snapshot.getValue(Product.class));
                productAdapt.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                productAdapt.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                productAdapt.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                productAdapt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Product product = snapshot.getValue(Product.class);
                    productList.add(product);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(product_page.this, "L???y danh s??ch kh??ng th??nh c??ng", Toast.LENGTH_SHORT).show();
            }
        });*/
    }


    @Override
    public void onProductClick(int position) {
        displayItem(productList.get(position));
    }

    private void displayItem(Product product){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.product_item, proItem);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView productName = layout.findViewById(R.id.textviewProductName);
        TextView manufacture = layout.findViewById(R.id.textViewManufacture);
        TextView price = layout.findViewById(R.id.textViewPrice);
        ImageView imgProduct = layout.findViewById(R.id.imageViewProduct);
        Button btn_detail = layout.findViewById(R.id.btn_details);
        Button btn_edit = layout.findViewById(R.id.btn_edit);

        productName.setText(product.getName());
        manufacture.setText(product.getManufacture());
        price.setText(product.getPrice().toString());
        Picasso.get().load(product.getImage()).into(imgProduct);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(product_page.this, edit_page.class);
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });

        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });
    }
}