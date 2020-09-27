package com.haodong.structure2.structure.dynamic;

/**
 * created by linghaoDo on 2020/9/25
 * description:
 * <p>
 * version:
 */
public class Question343 {
    public int integerBreak(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n should be greatr than zero");
        }
        int[] memo = new int[n + 1];
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 求解memo[i]
            for (int j = 1; j <= i - 1; j++)
                // 把 i分割为 j i-j
                memo[i] = max3(memo[i], j * (i - j), j * memo[i - j]);
        }
        return memo[n];
    }

    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
}
