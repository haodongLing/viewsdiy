package com.haodong.structure2.structure.mytree;

import com.haodong.structure2.structure.TreeNode;

/**
 * created by linghaoDo on 2020/9/27
 * description:
 * <p>
 * version:
 */
public class Question235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (true) {
            if (p.val < node.val && q.val < node.val) {
                node = node.left;
            } else if (p.val > node.val && q.val > node.val) {
                node = node.right;
            } else {
                break;
            }
        }
        return node;
    }
}
