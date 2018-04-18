package com.example.miaomiao.voiceapp.function;

import com.example.miaomiao.voiceapp.model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by miaomiao on 4/17/18.
 */

public class ShoppingCart {

    private Map<Product, Integer> shoppingCart = new HashMap<>();
    private int totalPrice = 0;
    private int totalQuantity = 0;

    public void addItem(Product product, int quantity) {
        if (shoppingCart.containsKey(product)) {
            shoppingCart.put(product, shoppingCart.get(product) + quantity);
        } else {
            shoppingCart.put(product, quantity);
        }

        totalPrice = totalPrice + (product.getPrice() * (Integer.valueOf(quantity)));
        totalQuantity += quantity;
    }


    public void removeItem(Product product, int quantity) {

        if (shoppingCart.containsKey(product)) {
            int curQuality = shoppingCart.get(product);
            curQuality -= quantity;
            if(curQuality < 0) {
                shoppingCart.remove(product);
            } else {
                shoppingCart.put(product, curQuality);
            }

            totalPrice = totalPrice - (product.getPrice() * curQuality);
            totalQuantity -= quantity;
        } else {
            throw new NoSuchElementException("You can't remove this item");
        }
    }


    public void updateItem(Product product, int quantity) {
        if (shoppingCart.containsKey(product)) {
            if(quantity < 1) {
                throw new ArrayIndexOutOfBoundsException("Can't set quatity less than 1");
            } else {
                int curQuality = shoppingCart.get(product);
                int productPrice = product.getPrice() * curQuality;
                shoppingCart.put(product, quantity);

                totalQuantity = totalQuantity - curQuality + quantity;
                totalPrice = totalPrice - (productPrice) + (product.getPrice() * quantity);
            }
        } else {
            throw new NoSuchElementException("You can't update this item");
        }
    }

    public Map<Product, Integer> getItemWithQuantity() {
        Map<Product, Integer> cartItemMap = new HashMap<Product, Integer>();
        cartItemMap.putAll(this.shoppingCart);
        return cartItemMap;
    }

    public Set<Product> getProducts() {
        return shoppingCart.keySet();
    }

}
