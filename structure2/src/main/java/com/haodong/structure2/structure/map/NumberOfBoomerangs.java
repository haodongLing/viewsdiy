package com.haodong.structure2.structure.map;

import java.util.HashMap;

/**
 * created by linghaoDo on 2019-11-05
 * description:
 * <p>
 * version:
 */
public class NumberOfBoomerangs {
    public static void main(String[] args) {
    }

    /**
     * 计算两个点位的距离
     *
     * @param pa
     * @param pb
     * @return
     */
    private int dis(int[] pa, int pb[]) {
        return (pa[0] - pb[0]) * (pa[0] - pb[0]) +
                (pa[1] - pb[1]) * (pa[1] - pb[1]);
    }


    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Integer, Integer> record = new HashMap<>();// 每一个枢纽都开了一个查找表
            for (int j = 0; j < points.length; i++) {
                // 计算距离时，不进行开根运算，以保证精度。
                int dis = dis(points[i], points[j]); // 计算两点之间的距离
                if (record.containsKey(dis))
                    record.put(dis, record.get(dis) + 1);
                else
                    record.put(dis, 1);
            }
            for (Integer dis : record.keySet()) {
                res += record.get(dis) * (record.get(dis) -1);
            }
        }
        return res;
    }


}
