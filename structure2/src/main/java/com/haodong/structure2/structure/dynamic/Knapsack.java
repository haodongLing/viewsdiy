package com.haodong.structure2.structure.dynamic;

/**
 * created by linghaoDo on 2020/9/30
 * description: 01 背包问题
 * <p>
 * version:
 */
public class Knapsack {
    private int[][] memo;

    public int knapsack01(int[] w, int[] v, int C) {
        if (w == null || v == null || w.length != v.length)
            throw new IllegalArgumentException("Invalid w or v");

        if (C < 0)
            throw new IllegalArgumentException("C must be greater or equal to zero.");

        int n = w.length;
        if (n == 0 || C == 0)
            return 0;
        memo = new int[n][C + 1];
        return bestValue(w, v, n - 1, C);
    }

    // 用 [0...index]的物品,填充容积为c的背包的最大价值
    private int bestValue(int[] w, int[] v, int index, int c) {
        if (c <= 0 || index <= 0) {
            return 0;
        }
        if (memo[index][c] != -1) {
            return memo[index][c];
        }
        int res = bestValue(w, v, index - 1, c);
        /*如果当前背包能放下w[index]*/
        if (c >= w[index]) {
            /*比较两种策略： 选用w[index]和不选用 w[index]*/
            res = Math.max(res, v[index] + bestValue(w, v, index - 1, c - w[index]));
        }
        return memo[index][c] = res;
    }

}
