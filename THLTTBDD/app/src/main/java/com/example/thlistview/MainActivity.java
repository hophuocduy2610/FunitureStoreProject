package com.example.thlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.thlistview.adapter.customAdapter;
import com.example.thlistview.model.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> lstProduct;
    private ListView listView;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_item);

        listView = (ListView) findViewById(R.id.idListView);
        lstProduct= new ArrayList<>();
        lstProduct.add(new Product(1, "Ca nấu lẩu, nấu mì mini...", "Devang"));
        lstProduct.add(new Product(2, "1KG KHÔ GÀ BƠ TỎI", "LTD Food"));
        lstProduct.add(new Product(3, "Xe cần cẩu đa năng", "Thế giới đồ chơi"));
        lstProduct.add(new Product(4, "Đồ chơi dạng mô hình", "Thế giới đồ chơi"));
        lstProduct.add(new Product(5, "Lãnh đạo giản đơn", "Minh Long Book"));
        lstProduct.add(new Product(6, "Hiểu lòng con trẻ", "Minh Long Book"));

        customAdapter adapter = new customAdapter(this, R.layout.custom_item, lstProduct);
        setAdapter();
    }

    private  void setAdapter(){
        listView.setAdapter(adapter);
    }
}