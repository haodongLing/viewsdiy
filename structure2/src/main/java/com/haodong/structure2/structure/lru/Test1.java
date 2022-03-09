package com.haodong.structure2.structure.lru;

import java.util.HashMap;

/**
 * Author: tangyuan
 * Time : 2022/2/28
 * Description:  lru 双向链表+hash数组 保证插入 删除 时间复杂度为O（1）
 */
public class Test1 {
    class Node {
        public int key, val;
        public Node next, pre;

        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    class DoubleList {
        private Node head, tail;
        private int size;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
            size = 0;
        }

        // 相当于插到tail的前面
        public void addLast(Node x) {
            x.pre = tail.pre;
            x.next = tail;
            tail.pre.next = x;
            tail.pre = x;
            size++;
        }

        public void remove(Node x) {
            x.pre.next = x.next;
            x.next.pre = x.pre;
            size--;

        }

        public Node removeFirst() {
            if (head.next == tail) {
                return null;
            }
            Node first = head.next;
            remove(first);
            return first;
        }

        public int size() {
            return size;
        }

    }

    /**
     * 注意我们实现的双链表 API 只能从尾部插入，也就是说靠尾部的数据是最近使用的，靠头部的数据是最久为使用的。
     */
    class LRUCache {
        // 本质 查询快 需要用其来存储Node
        private HashMap<Integer, Node> map;
        private DoubleList cache; // 其是用于实现最近最少的核心 根据HashMap 中找到的 Node来找到Node的位置
        private int cap;

        public LRUCache(int cap) {
            this.cap = cap;
            map = new HashMap<>();
            cache = new DoubleList();
        }

        /*添加最近使用的元素*/
        private void makeRecently(int key) {
            Node x = map.get(key);
            cache.remove(x);
            cache.addLast(x);
        }

        private void addRecently(int key, int val) {
            Node x = new Node(key, val);
            // 链表尾部就是最近使用的元素
            cache.addLast(x);
            // 别忘了在 map 中添加 key 的映射
            map.put(key, x);
        }

        private void deleteKey(int key) {
            Node x = map.get(key);
            cache.remove(x);
            map.remove(key);
        }

        /**
         * 这里就能回答之前的问答题「为什么要在链表中同时存储 key 和 val，而不是只存储 val」，注意 removeLeastRecently 函数中，我们需要用 deletedNode 得到 deletedKey。
         * <p>
         * 也就是说，当缓存容量已满，我们不仅仅要删除最后一个 Node 节点，还要把 map 中映射到该节点的 key 同时删除，而这个 key 只能由 Node 得到。如果 Node 结构中只存储 val，
         * 那么我们就无法得知 key 是什么，就无法删除 map 中的键，造成错误。
         */
        private void removeLastRecently() {
            Node deleteNode = cache.removeFirst();
            int deleteKey = deleteNode.key;
            map.remove(deleteKey);
        }

        public int get(int key){
            if (!map.containsKey(key)){
                return -1;
            }
            makeRecently(key);
            return map.get(key).val;
        }

        public void put(int key,int val){
            if (map.containsKey(key)){
                deleteKey(key);
                addRecently(key,val);
                return;
            }
            if (cap==cache.size()){
                removeLastRecently();
            }
            addRecently(key,val);
        }

    }
}
