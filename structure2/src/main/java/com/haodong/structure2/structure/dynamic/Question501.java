package com.haodong.structure2.structure.dynamic;

/**
 * created by linghaoDo on 2020/9/24
 * description:
 * <p>
 * version:
 */
public class Question501 {
    class Solution {
        private TreeNode pre = null;
        private int[] result;
        private int resultCount = 0;
        private int maxCount = 0;
        private int currCount = 0;

        public int[] findMode(TreeNode root) {
            findAndFill(root);
            return this.result;
        }

        private void findAndFill(TreeNode root) {
            if (root == null) {
                return;
            }
            findAndFill(root.left);
            if (this.pre != null && this.pre.val == root.val) {
                this.currCount++;
            } else {
                this.currCount = 1;
            }
            if (this.currCount > this.maxCount) {
                this.maxCount = this.currCount;
                this.resultCount = 1;
            } else if (this.currCount == this.maxCount) {
                if (this.result != null) {
                    this.result[this.resultCount] = root.val;
                }
                this.resultCount++;
            }
            this.pre = root;
            findAndFill(root.right);
        }

    }
}
