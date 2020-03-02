package com.haodong.structure2.structure.link;

/**
 * created by linghaoDo on 2019-09-15
 * <p>
 * description: 链表的反转
 * 搞清楚题意，是需要改变数据的结构还是仅仅遍历输出数据
 */
public class ReverseLink {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode head2 = head;
        for (int i = 1; i < 5; i++) {
            head.next = new ListNode(i);
            head = head.next;
        }
        Solution solution = new Solution();
        ListNode listNode3 = solution.revserseBetween(head2, 1, 3);
        while (listNode3 != null) {
            System.out.print("val->" + listNode3.val+"\n");
            listNode3 = listNode3.next;
        }
    }

    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static class Solution {
        public ListNode reserveList(ListNode head) {
            ListNode pre = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }

        private boolean stop;
        private ListNode left;

        public void recurseAndReverse(ListNode right, int m, int n) {
            if (n == 1) {
                return;
            }
            right = right.next;
            if (m > 1) {
                this.left = this.left.next;
            }
            this.recurseAndReverse(right, m - 1, n - 1);

            if (this.left == right || right.next == this.left) {
                this.stop = true;
            }
            if (!this.stop) {
                int t = this.left.val;
                this.left.val = right.val;
                right.val = t;
                this.left = this.left.next;
            }
        }

        public ListNode revserseBetween(ListNode head, int m, int n) {
            this.left = head;
            this.stop = false;
            this.recurseAndReverse(head, m, n);
            return head;
        }
    }


}
