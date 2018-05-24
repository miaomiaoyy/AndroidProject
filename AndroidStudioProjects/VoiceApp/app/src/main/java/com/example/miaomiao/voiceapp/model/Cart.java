package com.example.miaomiao.voiceapp.model;

/**
 * Created by miaomiao on 4/17/18.
 */

public class Cart {

        private Product product;
        private int quantity;

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }


    }
