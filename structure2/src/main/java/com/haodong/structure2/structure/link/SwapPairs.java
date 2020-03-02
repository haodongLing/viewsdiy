package com.haodong.structure2.structure.link;

/**
 * created by linghaoDo on 2019-12-27
 * description:
 * <p>
 * version:
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SwapPairs {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        while (dummyHead.next != null && dummyHead.next.next != null) {
            ListNode p = dummyHead;
            ListNode node1 = dummyHead.next;
            ListNode node2 = node1.next;
            ListNode next = node2.next; //最差为null
            node2.next = node1;
            node1.next = next;
            p.next = node2;
            p = node1;
        }
        return dummyHead.next;

    }
}