package com.haodong.structure2.structure.mysort;

import com.haodong.structure2.structure.MyUtil;

/**
 * created by linghaoDo on 2020-03-03
 * description:
 * <p>
 * version:
 */
public class InsertSort {
    public static void sort(int a[]){
        if (a==null||a.length==0)
            return;
        for (int i=1;i<a.length;i++){
          for (int j=i;j>0&& MyUtil.less(a[j],a[j-1]);j--){
             MyUtil.swap(a,j,j-1);
          }
        }
    }

}
