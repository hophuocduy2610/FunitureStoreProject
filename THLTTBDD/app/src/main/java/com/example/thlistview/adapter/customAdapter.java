package com.example.thlistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.thlistview.R;
import com.example.thlistview.model.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class customAdapter extends BaseAdapter {
    private Context context;
    private int idLayout;
    private List<Product> lstProduct;

    public customAdapter(@NonNull Context context, int idLayout, @NonNull List<Product> products) {
        this.context = context;
        this.idLayout = idLayout;
        this.lstProduct = products;
    }

    @Override
    public int getCount() {
        if (lstProduct.size() != 0 && !lstProduct.isEmpty()){
            return lstProduct.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(idLayout, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.productImage);
        TextView tvTitle = convertView.findViewById(R.id.title);
        TextView tvShop = convertView.findViewById(R.id.shop);
        TextView tvShopName = convertView.findViewById(R.id.tenShop);
        Button btnChat = convertView.findViewById(R.id.chat);

        final ConstraintLayout constraintLayout = convertView.findViewById(R.id.idConstraint);
        final Product product = lstProduct.get(position);

        if (lstProduct != null && !lstProduct.isEmpty()) {
            tvShopName.setText(product.getName());
            int idProduct = product.getId();
            switch (idProduct) {
                case 1:
                    imageView.setImageResource(R.drawable.ca_nau_lau);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.do_choi_dang_mo_hinh);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.ga_bo_toi);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.hieu_long_con_tre);
                    break;
                case 5:
                    imageView.setImageResource(R.drawable.lanh_dao_gian_don);
                    break;
                case 6:
                    imageView.setImageResource(R.drawable.xa_can_cau);
                    break;
                default:
                    break;
            }
        }
        return convertView;
    }
}
