package com.example.baitaplon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class edit_page extends AppCompatActivity {
    private EditText productName;
    private EditText manufacture;
    private EditText price;
    private EditText imageLink;
    private Button btn_edit;
    private Button btn_delete;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String productID;
    private  Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);

        productName = findViewById(R.id.editTextProductName);
        manufacture = findViewById(R.id.editTextManufacture);
        price = findViewById(R.id.editTextPrice);
        imageLink = findViewById(R.id.editTextImageLink);
        btn_edit = findViewById(R.id.btn_edite);
        btn_delete = findViewById(R.id.btn_delete);
        firebaseDatabase = FirebaseDatabase.getInstance();
        product = getIntent().getParcelableExtra("product");
        if(product != null){
            productName.setText(product.getName());
            manufacture.setText(product.getManufacture());
            price.setText(product.getPrice());
            imageLink.setText(product.getImage());
            productID = product.getProductID();
        }
        databaseReference = firebaseDatabase.getReference("products").child(productID);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = productName.getText().toString();
                String manu = manufacture.getText().toString();
                String pri = price.getText().toString();
                String img = imageLink.getText().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("name", name);
                map.put("manufacture", manu);
                map.put("price", pri);
                map.put("image", img);
                map.put("productID", productID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.updateChildren(map);
                        Toast.makeText(edit_page.this, "???? c???p nh???t", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(edit_page.this, product_page.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(edit_page.this, "C???p nh???t th???t b???i", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProduct();
            }
        });
    }

    private  void deleteProduct(){
        databaseReference.removeValue();
        Toast.makeText(this, "X??a th??nh c??ng", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(edit_page.this, product_page.class));
    }
}