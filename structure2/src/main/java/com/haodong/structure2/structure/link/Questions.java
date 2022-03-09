package com.haodong.structure2.structure.link;

import java.util.Comparator;
import java.util.PriorityQueue;

import androidx.core.app.NavUtils;

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

    /**
     * 合并k个有序链表
     * @param l1
     * @param l2
     * @return
     */
    ListNode mergeTwoLists(ListNode l1,ListNode l2){
        ListNode dummy=new ListNode(-1),p=dummy;
        ListNode p1=l1,p2=l2;
        while (p1!=null&&p2!=null){
            if(p1.val>p2.val){
                p.next=p2;
                p2=p2.next;
            }else {
                p.next=p1;
                p1=p1.next;
            }
            p=p.next;
        }
        if (p1!= null){
           p.next=p1;
        }
        if (p2!=null){
            p.next=p2;
        }
        return dummy.next;

    }
    /**
     * 对k个有序链表排序
     */
//    ListNode mergeKLists(ListNode[] listNodes){
//        /*边界条件*/
//        if (listNodes==null||listNodes.length==0){
//            return null;
//        }
//        ListNode dummy=new ListNode(-1);
//        ListNode p=dummy;
////        PriorityQueue<ListNode>pq=new PriorityQueue<>(lists.length, (a, b)->(a.val - b.val));
//
//        return
//
//    }

    ListNode getIntersectionNode(ListNode headA, ListNode headB){
        ListNode p1=headA;
        ListNode p2=headB;
        while (p1!=p2){
            if (p1==null)p1=headB;else {
                p1=p1.next;
            }
            if (p2==null){
                p2=headA;

            }else {
                p2=p2.next;
            }
        }
        return p1;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head==null){
            return null;
        }
        ListNode a,b;
        a=b=head;
        /*找到链表反转的区间*/
        for (int i=0;i<k;i++){
            if (b==null) return head;
            b=b.next;
        }
        ListNode newHead=reverseLink(a,b);
        a.next=reverseKGroup(b,k);
        return newHead;

    }

    private ListNode reverseLink(ListNode a, ListNode b) {
        ListNode pre,cur,next;
        pre=null;
        cur=next=a;
        while (a!=b){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;

    }

    public ListNode reverseLink(ListNode head){
        if (head==null){
            return null;
        }
        ListNode pre=null;
        ListNode cur=head;
        ListNode next=head;
        while (cur!=null){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;


    }


    /**
     * 删除倒数第N个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode x=findFromEnd(dummy,n+1);
        x.next=x.next.next;
        return dummy.next;
    }

//    /**
//     *
//     * @param head
//     * @param k 倒数第n+1个节点
//     * @return
//     */
//    public ListNode findFromEnd(ListNode head, int k){
//        ListNode p1=head;
//        for (int i=0;i<k;i++){
//            p1=p1.next;
//
//        }
//        ListNode p2=head;
//        while (p1!=null){
//            p2=p2.next;
//            p1=p1.next;
//        }
//        return p2;
//    }

    /**
     * 合并k条链表
     * @param lists
     * @return
     */
    public ListNode mergeLists(ListNode[] lists){
        if (lists.length==0){
            return null;
        }
        ListNode dummy=new ListNode(-1);
        ListNode p=dummy;
        PriorityQueue<ListNode>pq=new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val- o2.val;
            }
        });
        for (ListNode head:lists){
            if (head!=null){
                pq.add(head);
            }
        }
        while (!pq.isEmpty()){
                ListNode node=pq.poll();
                p.next=node;
                if (node.next!=null){
                    pq.add(node.next);
                }
                p=p.next;
        }
        return dummy.next;

    }

    /**
     * 找到倒数第k个节点
     * @param head
     * @param k
     * @return
     */
   public ListNode findFromEnd(ListNode head,int k){
        ListNode p1=head;
        ListNode p2=head;
        for (int i=0;i<k;i++){
            while (p1!=null){
                p1=p1.next;
            }
        }
        while (p1!=null){
            p1=p1.next;
            p2=p2.next;
        }
        return p2;
    }

    /**
     * 链表的排序
     */
    public class Solution {
        /**
         *
         * @param head ListNode类 the head node
         * @return ListNode类
         */
        public ListNode sortInList (ListNode head) {
            // write code here
            if (head == null || head.next == null)
                return head;
            // 使用快慢指针寻找链表的中点
            ListNode fast = head.next, slow = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode tmp = slow.next;
            slow.next = null;
            // 递归左右两边进行排序
            ListNode left = sortInList(head);
            ListNode right = sortInList(tmp);
            // 创建新的链表
            ListNode h = new ListNode(0);
            ListNode res = h;
            // 合并 left right两个链表
            while (left != null && right != null) {
                // left  right链表循环对比
                if (left.val < right.val) {
                    h.next = left;
                    left = left.next;
                } else {
                    h.next = right;
                    right = right.next;
                }
                h = h.next;
            }
            // 最后添加未对比的链表部分判断左链表是否为空
            h.next = left != null ? left : right;
            return res.next;
        }
    }






}
