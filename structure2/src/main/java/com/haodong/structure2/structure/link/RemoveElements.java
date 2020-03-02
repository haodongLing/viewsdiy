package com.haodong.structure2.structure.link;

/**
 * created by linghaoDo on 2019-12-10
 * description:
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 * <p>
 * version:
 */


public class RemoveElements {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
//        ListNode cur = dummyHead;
        while (dummyHead.next != null) {
            if (dummyHead.next.val == val) {
                final ListNode deleteNode = dummyHead.next;
                dummyHead.next = deleteNode.next;
                deleteNode.next = null;

            } else
                dummyHead = dummyHead.next;
        }
        return dummyHead.next;
    }

    public ListNode mergeToLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeToLists(l1.next, l2.next);
            return l1;
        } else {
            l2.next = mergeToLists(l1, l2.next);
            return l2;
        }
    }
}
