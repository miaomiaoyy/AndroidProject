package com.example.miaomiao.voiceapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.miaomiao.voiceapp.function.ShoppingCart;
import com.example.miaomiao.voiceapp.model.Cart;
import com.example.miaomiao.voiceapp.model.Product;

public class ShoppingCartActivity extends AppCompatActivity {
    private static final String TAG = "ShoppingCartActivity";

    ListView list;
    Button bClear;
    Button bShop;
    TextView tvTotalPrice;

//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shopping_cart);
//        WebView webview = new WebView(this);
//        setContentView(webview);
//
//        //webview.loadUrl("http://google.com");
//
//    }
//
//}
//


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_shopping_cart);

            list = (ListView) findViewById(R.id.list);
            LayoutInflater layoutInflater = getLayoutInflater();

            final ShoppingCart cart = new ShoppingCart();
            Product product = new Product(1,"HelloKitty", 200,
                    "Lovely cake",
                    "http://www.littlebcakes.com/wp-content/uploads/2013/08/Hello-Kitty-Birthday-Cake-Images.jpg");


            bShop.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ShoppingCartActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    List<Cart> cartItems = getCartItems(cart);
                    Product product = cartItems.get(position-1).getProduct();
                    Log.d(TAG, "Viewing product: " + product.getName());
                    bundle.putSerializable("product", product);
                    Intent intent = new Intent(ShoppingCartActivity.this, CakeActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        private List<Cart> getCartItems(ShoppingCart cart) {
            List<Cart> cartItems = new ArrayList<Cart>();
            Log.d(TAG, "Current shopping cart: " + cart);

            Map<Product, Integer> itemMap = cart.getItemWithQuantity();

            for (Entry<Product, Integer> entry : itemMap.entrySet()) {
                Cart cartItem = new Cart();
                cartItem.setProduct((Product) entry.getKey());
                cartItem.setQuantity(entry.getValue());
                cartItems.add(cartItem);
            }

            Log.d(TAG, "Cart item list: " + cartItems);
            return cartItems;
        }
    }


