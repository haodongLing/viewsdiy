package com.haodong.structure2.structure.mytree;

/**
 * created by linghaoDo on 2019-09-16
 * <p>
 * description:
 */
public class Node<E> {
    public E e;
    public Node left;
    public Node right;

    public Node(E e) {
        this.e = e;
        left = null;
        right = null;
    }
}
