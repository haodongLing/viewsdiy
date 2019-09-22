package com.haodong.structure2.structure.mysearch;

/**
 * created by linghaoDo on 2019-09-22
 * <p>
 * description: 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] test = new int[]{1, 4, 7, 6, 3};
        twoSum(test, 8);
    }

    public static int[] twoSum(int[] numbers, int target) {
        int[] chaArr = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {

            chaArr[i] = target - numbers[i];
            if (i > 0) {
                for (int j = 0; j <= i; j++) {
                    if (chaArr[j] == numbers[i]) {
                        System.out.println(numbers[j] + "---" + numbers[i]);
                        if (i != j)
                            return new int[]{numbers[j], numbers[i]};
                    }
                }
            }


        }
        throw new NullPointerException(" no solution");
    }

    /**
     * 解法2
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] numbers, int target) {
        int[] chaArr = new int[numbers.length];
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target)
                return new int[]{l + 1, r + 1};
            else if (numbers[l] + numbers[r] > target)
                r--;
            else
                l++;
        }
        throw new IllegalArgumentException(" no solution");
    }
}
