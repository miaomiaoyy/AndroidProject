//package com.example.miaomiao.recyclerviewexample;
//
//import android.content.Intent;
//
//import java.util.Arrays;
//import java.util.jar.Manifest;
//
///**
// * Created by miaomiao on 3/6/18.
// */
//
//public class getOptimal {
//    int getOpt(int[] cards, int start, int end) {
//
//        int j = cards.length - 1;
//        int max = Integer.MIN_VALUE;
//        int[][] dp = new int[4][cards.length / 2];
//        for (int i = 0; i < cards.length; i++) {
//            for (int k1 = 0; k1 < cards.length / 2; k1++) {
//                for (int k2 = 0; k2 < cards.length / 2; k2++) {
//                    dp[0][i] += Math.max(cards[i + k1 + k2], cards[j - k1 - k2]);
//                    dp[1][i] += Math.max(cards[i + k2], cards[j - k2]);
//                    max = Math.max(max, dp[0][i]);
//                }
//            }
//        }
//        return max;
//    }
//
//    static boolean changeCoin(int[] coins, int v) {
//        if(v == 0) {
//            return true;
//        }
//
//        boolean[][] dp = new boolean[coins.length][v];
//
//        for(int j = 0 ; j < v; j++) {
//            dp[0][j] = false;
//        }
//        for(int i = 0; i < coins.length; i++) {
//            dp[i][0] = true;
//            dp[i][i] = true;
//        }
//        for (int i = 0; i < coins.length; i++) {
//            for (int j = 0; j < v; j++) {
//                dp[i][j] = dp[i][j - coins[i]];
//            }
//        }
//        return dp[coins.length][v];
//    }
//
//    static boolean changCoinOnce(int[] coins, int v) {
//        if(v == 0) {
//            return true;
//        }
//        boolean[][] dp = new boolean[coins.length + 1][v + 1];
//        for(int j = 0 ; j < v; j++) {
//            dp[0][j] = false;
//        }
//        for(int i = 0; i < coins.length; i++) {
//            dp[i][0] = true;
//            dp[i][i] = true;
//        }
//        for(int i = 1; i <= coins.length; i++) {
//            for(int j = coins[i - 1]; j <= v; j++) {
//                dp[i][j] = dp[i - 1][j - coins[i]];
//            }
//        }
//        return dp[coins.length][v];
//    }
//
//        public int coinChange2(int[] coins, int amount) {
//            if(amount < 1) {
//                return 0;
//            }
//            int[] dp = new int[amount + 1];
//
//            Arrays.fill(dp, Integer.MAX_VALUE);
//            dp[0] = 0;
//            for(int coin : coins) {
//                for(int i = coin; i <= amount; i++) {
//                    if(dp[i - coin] != Integer.MAX_VALUE) {
//                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
//                    }
//                }
//            }
//            return dp[amount] == Integer.MAX_VALUE? -1: dp[amount];
//        }
//
//    public static void main(String[] args) {
//        int[] coins = {2,5,10,1};
//        System.out.print(changCoinOnce(coins, 23));
//        System.out.print(changeCoin(coins,23));
//    }
//    }
//
//
//
//
