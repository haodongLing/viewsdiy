package com.haodong.structure2.structure.link;

/**
 * Author: tangyuan
 * Time : 2021/10/14
 * Description:
 */
public class NodeQuestions {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /*判断链表是否有环*/
    public boolean hasCycle(ListNode listNode){
        ListNode fast,slow;
        fast=slow=listNode;
        while (fast!=null&&slow!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return false;

    }
    /*返回环的其实位置*/
    public ListNode detectCycle(ListNode head){
        ListNode fast,slow;
        fast=slow=head;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=fast.next;
            /*有环，往下执行*/
            if (fast==slow)
                break;
        }
        if (fast==null||fast.next==null){
            return null;
        }
        slow=head;
        while (slow!=fast){
            fast=fast.next;
            slow=slow.next;
        }
        return slow;
    }

    /**
     * 移除 倒数第n个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head,int n){
        ListNode fast,slow,lengthNode;
        fast=slow=lengthNode=head;

        /*1. 判断链表长度是否大于n*/
        int length=0;
        while (lengthNode!=null){
            lengthNode=lengthNode.next;
            length++;
        }
        if (length<n){
            return null;
        }
        while (n-->0){
            fast=fast.next;
        }
        while (fast!=null&&fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }
        /*此时 ，slow.next为倒数第n个节点，删除*/
        slow.next=slow.next.next;
        return head;
    }


}
