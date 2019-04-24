package haodong.com.views_diy.datastructure;

/**
 * describe :
 * date on 2019/4/23
 * author linghailong
 * email 105354999@qq.com
 */
public class MyNode<T> {
    MyNode next;
    T element;

    public MyNode(T element) {
        this.element = element;
    }

    public MyNode getNext() {
        return next;
    }

    public void setNext(MyNode next) {
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
    public static<T> MyNode<T> revertNode(MyNode head){
        if (head == null) {
            return null;
        }

        if (head.getNext() == null) {
            return head;
        }

        //previous上一个节点
        MyNode preNode = null;
        //current节点当前节点，并让它指向传进来的对象所在地址（是保存该对象的地址，不是它的next值）
        MyNode curNode = head;
        //next节点下一个节点
        MyNode nextNode = null;

        while (curNode != null) {

//            //让next节点指向后一个节点所在地址，并改变新地址的值（包括data，next）
//            nextNode = curNode.getNext();
//            if (nextNode != null) {
//                System.out.print("nextNode data :" + nextNode.getData() + " next :" + nextNode.getNext() + " " + nextNode + "\n");
//            }
//
//            //将current节点存储的地址（也就是next）的值改为preNode节点所指向的地址（这样就把指向箭头反转了）这儿有个误区
//            //注意：是将preNode指向的地址给curNode的next，不是把preNode的next给它。
//            curNode.setNext(preNode);
//            if (curNode != null) {
//                System.out.print("curNode data :" + curNode.getData() + " next :" + curNode.getNext() + " " + curNode + "\n");
//            }
//
//            //让previous节点指向的地址向后移动一个单位，并改变新地址的值（包括data，next）
//            preNode = curNode;
//            if (preNode != null) {
//                System.out.print("preNode data :" + preNode.getData() + " next :" + preNode.getNext() + " " + preNode + "\n");
//            }
//
//            //让current节点的索引向后移动一个单位，并改变新地址的值包括（data，next）
//            curNode = nextNode;
//            if (curNode != null) {
//                System.out.print("curNode data :" + curNode.getData() + " next :" + curNode.getNext() + " " + curNode + "\n");
//            }
//
//            System.out.print("-----------------------\n");
        }

        return preNode;
    }

}
