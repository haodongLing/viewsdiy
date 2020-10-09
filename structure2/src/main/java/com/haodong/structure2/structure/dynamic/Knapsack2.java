package com.haodong.structure2.structure.dynamic;

/**
 * created by linghaoDo on 2020/9/30
 * description: 01背包问题，递推方式解决
 * <p>
 * version:
 */
public class Knapsack2 {
    public int knapsack2(int[] w, int[] v, int C) {
        if (w == null || v == null || w.length != v.length)
            throw new IllegalArgumentException("Invalid w or v");

        if (C < 0)
            throw new IllegalArgumentException("C must be greater or equal to zero.");
        int n = w.length;// 有几个元素
        if (n == 0 || C == 0) {
            return 0;

        }
        int[][] memo = new int[n][C + 1];
        for (int j = 0; j <= C; j++) {
            memo[0][j] = (j >= w[0] ? v[0] : 0);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                /*第一种策略，不考虑第i个物品*/
                memo[i][j] = memo[i - 1][j];
                /*第二种策略，考虑第i个物品*/
                memo[i][j] = Math.max(memo[i][j], v[i] + memo[i - 1][j - w[i]]);
            }
        }
        return memo[n - 1][C];
    }
}
