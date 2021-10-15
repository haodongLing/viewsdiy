package com.haodong.structure2.structure.questions;

import android.os.Build;
import android.util.ArraySet;

import java.util.HashMap;
import java.util.Set;

import androidx.annotation.RequiresApi;

/**
 * Author: tangyuan
 * Time : 2021/9/8
 * Description:
 */
public class Q {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public int findMulti(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr must not be null ");
        }
//        HashMap<String,Object>str=new HashMap<>();
        Set<Integer> set = new ArraySet<>();
        for (int i : arr) {
            if (set.contains(i)) {
                return i;
            }
        }
        return -1;
    }
}
