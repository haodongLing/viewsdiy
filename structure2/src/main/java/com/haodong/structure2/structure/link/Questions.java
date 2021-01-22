package com.haodong.structure2.structure.link;

/**
 * created by linghaoDo on 2020-04-07
 * description:
 * <p>
 * version:
 */
public class Questions {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        int current = 0;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode first = dummyHead;
        ListNode second = head;
        while (second.next != null) {
            current = second.val;
            if (current == second.next.val) {
                second = second.next;
                while (second.next != null) {
                    if (second.next.val == current) {
                        second = second.next;
                    } else break;
                }
                second = second.next;
                first.next = second;
                if (second == null)
                    break;
            } else {
                first = second;
                second = second.next;
            }
        }
        return dummyHead.next;

    }

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 24
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode p = dummyHead; // 指针，作为位置指引

        if (head == null || head.next == null) {
            return head;
        }
        while (p.next != null && p.next.next != null) {
            ListNode cur = p.next;
            ListNode next = p.next.next;
            ListNode nextNext = next.next;

            next.next = cur;
            cur.next = nextNext;
            p.next = next;
            p = cur;

        }

        return dummyHead.next;

    }

    public ListNode insertionSortList(ListNode head) {
        ListNode p = head;
        if (head == null) {
            return head;

        }
        return new ListNode(0);

    }

    public void printCommonPart(ListNode head1, ListNode head2) {

        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                head1 = head1.next;
            } else if (head1.val == head2.val) {
                System.out.println(" " + head1.val);
                head1 = head1.next;
                head2 = head2.next;
            } else if (head1.val > head2.val) {
                head2 = head2.next;
            }
        }

    }

    public ListNode removeLastKthNode(ListNode head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;

        }
        ListNode cur = head;
        while (cur != null) {
            lastKth--;
            cur = cur.next;

        }
        if (lastKth == 0) {
            head = head.next;
        }
        if (lastKth < 0) {
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }

    public ListNode reversePart(ListNode head, int from, int to) {
        int len = 0;
        ListNode node1 = head;
        ListNode fPre = null;
        ListNode tPos = null;
        while (node1 != null) {
            len++;
            fPre = len == from - 1 ? node1 : fPre;
            tPos = len == to + 1 ? node1 : tPos;
            node1 = node1.next;
        }
        if (from > to || from < 1 || to > len) {
            return head;
        }
        node1 = fPre == null ? head : fPre.next;
        ListNode node2 = node1.next;
        node1.next = tPos;
        ListNode next = null;
        while (node2 != tPos) {
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }
        return node1;
    }
}
