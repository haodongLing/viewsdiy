package com.haodong.structure2.structure.questions;

/**
 * created by linghaoDo on 2020/6/4
 * description:
 * <p>
 * version:
 */
public class New21Point {
    public static double solution(int N, int K, int W) {
        double[] dp = new double[K + W];
        double s = 0;
        for (int i = K; i < K + W; ++i) {
            dp[i] = i <= N ? 1 : 0;
            s += dp[i];
        }
        for (int i = K - 1; i >= 0; --i) {
            dp[i] = s / W;
            s = s - dp[i + W] + dp[i];
        }
        return dp[0];
    }
    public static void main(String[] args){
        System.out.println("result-->"+solution(21,17,10));
    }
}
