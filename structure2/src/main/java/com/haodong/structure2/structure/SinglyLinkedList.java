package com.haodong.structure2.structure;

/**
 * describe :
 * date on 2019/5/4
 * author linghailong
 * email 105354999@qq.com
 */
public class SinglyLinkedList<T> {
    private int size;
    private Node first;
    private static class  Node{
        Node next;
        Node item;

        public Node(Node next, Node item) {
            this.next = next;
            this.item = item;
        }
    }

}
