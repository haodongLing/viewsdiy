package com.haodong.structure2.structure.link;

import androidx.appcompat.widget.ViewUtils;

/**
 * created by linghaoDo on 2019-12-10
 * description:
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * version:
 */
public class DeleteDuplicates {


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode fakeNode = new ListNode(0); // 值为多少无所谓
            fakeNode.next = head;
            ListNode pre;
            ListNode cur = fakeNode;
            while (cur != null) {
                pre = cur;
                cur = cur.next;
                while (cur != null && cur.next != null && cur.val == cur.next.val) {
                    int val = cur.val;
                    while (cur != null && cur.val == val) {
                        cur = cur.next;
                    }
                    pre.next = cur;
                }
            }
            return fakeNode.next;
        }
    }
}
