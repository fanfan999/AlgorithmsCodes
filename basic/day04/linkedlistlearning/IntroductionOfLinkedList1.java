package basic.day04.linkedlistlearning;

import java.util.Currency;

/**
 * 带虚拟头结点的链表
 */
public class IntroductionOfLinkedList1<E> {

    private class Node{
        //存放元素数据
        public E e;
        //存放下一个节点的引用
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    //虚拟头结点,实际并不存在,只是为了代码方便
    private Node virtualHead;
    //链表长度
    private int size;

    public IntroductionOfLinkedList1(){
        //初始化时,它就不应该是一个空的,它应该包含元素和下一个节点的引用,虽然都为空.
        virtualHead = new Node(null, null);
        size = 0;
    }

    //获取链表中元素个数
    public int getSize(){
        return size;
    }

    //判断链表是否有元素
    public boolean isEmpty(){
        return size==0;
    }

    //链表头添加元素
    public void addFirst(E e) {
        add(0, e);
    }

    //在链表的index(0开始)位置添加元素,但是这个不常用.知道就行
    public void add(int index, E e) {
        //判断index的合法性
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("参数不合法");
        }
        //判断是否为头节点,头节点没有前一个节点,所以直接添加
        Node pre = virtualHead;
        //找到index前一个点的位置
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        //声明要添加的node
        Node node = new Node(e);
        //将node.next连接上pre.next(index位置的节点)
        node.next = pre.next;
        //将node作为pre的下一个节点
        pre.next = node;
        //简写为: pre.next = new Node(e, pre.next);
        size++;
        }

    //向末尾添加元素
    public void addLast(E e) {
        add(size, e);
    }

    //获取链表的第index个元素
    public E getEle(int index) {
        Node cur = virtualHead.next;
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("参数不合法");
        }else {
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
        }
        return cur.e;
    }
}
