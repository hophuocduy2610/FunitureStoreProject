package com.example.furniturestore;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String name;
    private String manufacture;
    private String price;
    private String image;
    private String productID;

    public Product(String name, String manufacture, String price, String image, String productID) {
        this.name = name;
        this.manufacture = manufacture;
        this.price = price;
        this.image = image;
        this.productID = productID;
    }

    public Product() {
    }

    protected Product(Parcel in) {
        name = in.readString();
        manufacture = in.readString();
        price = in.readString();
        image = in.readString();
        productID = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(manufacture);
        dest.writeString(price);
        dest.writeString(image);
        dest.writeString(productID);
    }
}
