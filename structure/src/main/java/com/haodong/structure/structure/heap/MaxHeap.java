package com.haodong.structure.structure.heap;

/**
 * describe : 因为元素需要比较大小，所以需要继承compareable
 * date on 2019/6/26
 * author linghailong
 * email 105354999@qq.com
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<E>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * 堆中的元素多少
     *
     * @return
     */
    public int size() {
        return data.getSize();
    }

    /**
     * @return 判断这个堆是否为空
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树中一个索引所表示的元素的父亲节点的索引。
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }
    // 向堆中添加新的元素
    public void add(E e){
        data.addLast(e);

    }
    // 上浮操作
    private void siftUp(int k){
        // 如果比父亲节点的值大，则与父亲节点交换位置
        while (k>0&&data.get(parent(k)).compareTo(data.get(k))<0){
            data.swap(k,parent(k));
            k=parent(k);
        }
    }


}
