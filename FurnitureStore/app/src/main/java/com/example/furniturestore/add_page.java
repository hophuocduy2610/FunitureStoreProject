package com.example.furniturestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class add_page extends AppCompatActivity {
    private EditText productName;
    private EditText manufacture;
    private EditText price;
    private EditText imageLink;
    private Button btn_add;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String productID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);

        productName = findViewById(R.id.editTextProductName);
        manufacture = findViewById(R.id.editTextManufacture);
        price = findViewById(R.id.editTextPrice);
        imageLink = findViewById(R.id.editTextImageLink);
        btn_add = findViewById(R.id.btn_add);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("products");

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = productName.getText().toString();
                String manu = manufacture.getText().toString();
                String pri = price.getText().toString();
                String img = imageLink.getText().toString();
                productID = name;
                Product product = new Product(name, manu, pri, img, productID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(productID).setValue(product);
                        Toast.makeText(add_page.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(add_page.this, product_page.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(add_page.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}