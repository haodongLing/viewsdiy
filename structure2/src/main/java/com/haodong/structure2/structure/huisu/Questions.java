package com.haodong.structure2.structure.huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: tangyuan
 * Time : 2022/2/18
 * Description:
 */
public class Questions {

    /**
     * 22. 括号生成
     * https://leetcode-cn.com/problems/generate-parentheses/
     * @param n
     *  第一步 绘制树
     *  第二步 修剪
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res=new ArrayList<>();
        if (n==0){
            return res;
        }
        String track="";
        backTrack(n,n,track,res);

        return res;
    }

    public void backTrack(int left,int right,String track,List<String> res){
        if (right<left){
            return ;
        }
        // 数量小于 0 肯定是不合法的
        if (left < 0 || right < 0) return;
        // 当所有括号都恰好用完时，得到一个合法的括号组合
        if (left == 0 && right == 0) {
            res.add(track);
            return;
        }
        backTrack(left-1,right,track+"(",res);
        backTrack(left,right-1,track+")",res);
    }

    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) return res;
        dfs(n, "", res, 0, 0);
        return res;
    }

    private void dfs(int n, String path, List<String> res, int open, int close) {
        if (open > n || close > open) return;

        if (path.length() == 2 * n) {
            res.add(path);
            return;
        }

        dfs(n, path + "(", res, open + 1, close);
        dfs(n, path + ")", res, open, close + 1);
    }
}
