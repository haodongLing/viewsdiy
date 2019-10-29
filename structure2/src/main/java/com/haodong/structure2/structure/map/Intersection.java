package com.haodong.structure2.structure.map;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * created by linghaoDo on 2019-10-08
 * <p>
 * description: 两个数组的交集
 */
public class Intersection {
    public static void main(String[] args) {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int aNums1 : nums1) {
            treeSet.add(aNums1);
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int bNnums : nums2) {
            if (treeSet.contains(bNnums)) {
                arrayList.add(bNnums);
                treeSet.remove(bNnums);
            }

        }
        int[] solution = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            solution[i] = arrayList.get(i);
        }
        return solution;

    }
}
