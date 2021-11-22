package com.haodong.structure2.structure.questions;

import android.os.Build;
import android.util.ArraySet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import androidx.annotation.RequiresApi;

/**
 * Author: tangyuan
 * Time : 2021/9/8
 * Description:
 */
public class Q {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }



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

    /**
     * 无重复字符的最长子串
     */
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int length = s.length();
            int max = 0;
            Map<Character, Integer> map = new HashMap<>();
            for (int end = 0, start = 0; end < length; end++) {
                Character cha = s.charAt(end);
                if (map.containsKey(cha)) {
                    start = Math.max(start, map.get(cha));
                }
                max = Math.max(max, end - start + 1);
                map.put(s.charAt(end), end + 1);

            }
            return max;
        }
    }

    /**
     * Valid Parentheses
     */
    class Solution2 {
        public boolean isValid(String s) {
            if (s == null) {
                return false;
            }
            if (s.equals("")) return true;
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == '{' || c == '[') {
                    /*1. 判断stack的size*/
                    stack.push(c);
                } else {
                    if (stack.size() > 0) {
                        char peekC = stack.peek();
                        if ((peekC == '(' && c != ')') || (peekC == '{' && c != '}') || (peekC == '[' && c != ']')) {
                            return false;
                        } else {
                            stack.pop();
                        }
                    } else {
                        return false;
                    }
                }

            }
            return stack.size() == 0;
        }
    }


    /**
     * 104 Definition for a binary tree node.
     * 二叉树的最大深度
     * 而查处的
     */
    class Solution3 {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            } else {
                int left = maxDepth(root.left);
                int right = maxDepth(root.right);
                return Math.max(left, right) + 1;
            }
        }
    }

    /**
     * 226 翻转二叉树
     * @return
     */
    class Solution4{
        public TreeNode invertTree(TreeNode root) {

            //递归函数的终止条件，节点为空时返回
            if(root==null) {
                return null;
            }
            //下面三句是将当前节点的左右子树交换
            TreeNode tmp = root.right;
            root.right = root.left;
            root.left = tmp;
            //递归交换当前节点的 左子树
            invertTree(root.left);
            //递归交换当前节点的 右子树
            invertTree(root.right);
            //函数返回时就表示当前这个节点，以及它的左右子树
            //都已经交换完了
            return root;
        }

    }




}
