package com.haodong.structure2.structure.mytree.question;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * created by linghaoDo on 2020/6/28
 * description:
 * <p>
 * version:
 */
class Qustions {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(2);
        treeNode.left.left = new TreeNode(5);
        maxDepth(treeNode);
    }

    /**
     * 翻转二叉树
     *
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {
        //递归函数的终止条件，节点为空时返回
        if (root == null) {
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

    public static void swap(TreeNode a, TreeNode b) {
        TreeNode c = a;
        a = b;
        b = c;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (q.val != p.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> strs = new ArrayList<>();
        if (root == null) {
            return strs;
        }
        if (root.right == null && root.left == null) {
            strs.add(String.valueOf(root.val));
        }
        List<String> leftS = binaryTreePaths(root.left);
        for (int i = 0; i < leftS.size(); i++) {
//            if (i == 0) {
//                left = leftS.get(i);
//            } else {
//                left = left + "->" + leftS.get(i);
//            }
            strs.add(String.valueOf(root.val) + "->" + leftS.get(i));
        }
        List<String> rightS = binaryTreePaths(root.right);
        for (int i = 0; i < rightS.size(); i++) {
//            if (i == 0) {
//                right = rightS.get(i);
//            } else {
//                right = right + "->" + rightS.get(i);
//            }
            strs.add(String.valueOf(root.val) + "->" + rightS.get(i));
        }
        return strs;

    }

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int res = findPath(root, sum);
        res += findPath(root.left, sum);
        res += findPath(root.right, sum);
        return res;

    }

    public static int findPath(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        int res = 0;
        if (node.val == sum) res += 1;
        res += findPath(node.left, sum - node.val);
        res += findPath(node.right, sum - node.val);
        return res;
    }

    /**
     * 链接：https://leetcode-cn.com/problems/palindrome-partitioning/solution/hui-su-you-hua-jia-liao-dong-tai-gui-hua-by-liweiw/
     * 来源：力扣（LeetCode）
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        Deque<String> stack = new ArrayDeque<>();

        return null;
    }

    /**
     * @param s
     * @param start 起始字符的索引
     * @param len   字符串 s 的长度，可以设置为全局变量
     * @param path  记录从根结点到叶子结点的路径
     * @param res   记录所有的结果
     */
    public static void backtracking(String s, int start, int len, Deque<String> path, List<List<String>> res) {
        if (start == len) {
            res.add(new ArrayList<String>(path));
            return;
        }
        for (int i = start; i < len; i++) {
            if (!checkPalindrome(s, start, i)) {
                continue;
            }
            path.addLast(s.substring(start, i + 1));
            backtracking(s, i + 1, len, path, res);
            path.removeLast();
        }

    }

    /**
     * 这一步的时间复杂度是 O(N)，因此，可以采用动态规划先把回文子串的结果记录在一个表格里
     *
     * @param str
     * @param left  子串的左边界，可以取到
     * @param right 子串的右边界，可以取到
     * @return
     */
    public static boolean checkPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private ArrayList<List<Integer>> res;
    private boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        used = new boolean[nums.length];
        LinkedList<Integer> p = new LinkedList<>();
        generatePermutation(nums, 0, p);

        return res;
    }

    private void generatePermutation(int[] nums, int index, LinkedList<Integer> p) {
        if (index == nums.length) {
            res.add((LinkedList<Integer>) p.clone());
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                p.addLast(nums[i]);
                generatePermutation(nums, index + 1, p);
                p.removeLast();
                used[i] = false;
            }
            return;
        }
    }

    /**
     * 77
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<List<Integer>>();
        if (n <= 0 || k <= 0 || k > n) return res;
        LinkedList<Integer> c = new LinkedList<>();
        generateCombinations(n, k, 1, c);
        return res;
    }

    private void generateCombinations(int n, int k, int start, LinkedList<Integer> c) {
        if (c.size() == k) {
            res.add((List<Integer>) c.clone());
            return;
        }
        for (int i = start; i <= n; i++) {
            c.addLast(i);
            generateCombinations(n, k, i + 1, c);
            c.removeLast();
        }
        return;
    }

    /*============================================*/

    /**
     * 构造最大二叉树
     * @param nums
     * @return
     */
    TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        // 找到数组中的最大值
        int maxVal = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                index = i;
            }
        }
        TreeNode root=new TreeNode(maxVal);
        root.left=constructMaximumBinaryTree(Arrays.copyOfRange(nums,0,index));
        root.right=constructMaximumBinaryTree(Arrays.copyOfRange(nums,index+1,nums.length-1));
        return root;
    }


    /**
     * 112 路径总和
     */
    class Solution1 {
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root==null){
                return false;
            }
                // 判断当前节点是否是叶子节点
            if (root.right==null&&root.left==null){
                return targetSum==0;
            }
            if (hasPathSum(root.left,targetSum-root.val)){
                return true;
            }
            if (hasPathSum(root.right,targetSum-root.val)){
                return true;
            }
            return false;
        }
    }

    /**
     * 111 到叶子节点的最小深度
     */
    class Soution2{
        public int minDepth(TreeNode root) {
            if (root==null){
                return 0;
            }
            if(root.left == null && root.right == null) {
                return 1;
            }
            int ans = Integer.MAX_VALUE;
            if(root.left != null) {
                ans = Math.min(minDepth(root.left), ans);
            }
            if(root.right != null) {
                ans = Math.min(minDepth(root.right), ans);
            }
            return ans + 1;
        }
    }

    /**
     * 257. 二叉树的所有路径
     */
    class Solution3 {

        List<String> res = new ArrayList<>();    // 存储结果
        LinkedList<String> path = new LinkedList<>();  // 存储单个路径

        public List<String> binaryTreePaths(TreeNode root) {
            traverse(root);
            return res;
        }

        // 二叉树的遍历框架
        public void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            // 前序遍历位置
            path.add(String.valueOf(root.val));
            // 到叶子节点就返回
            if (root.left == null && root.right == null) {
                for (int i=0;i<path.size();i++){
                    
                }
                // 添加到结果中
                res.add(String.join("->", path));
            }
            traverse(root.left);
            traverse(root.right);
            path.removeLast();
        }
    }

}
