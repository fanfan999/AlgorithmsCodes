package basic.day04;

import java.util.LinkedList;

    /**
    * 反转单链表和双链表
     */


public class ReverseList {
    /**
     * 生成单链表节点
     */


    public static class Node{
        private Node next;
        private int value;

        public Node(int value){
            this.value = value;
        }
    }

    /**
     * 双链表节点类
     */
    public static class DoubleNode{
        //数据值
        private int value;
        //上一个节点
        private DoubleNode last;
        //下一个节点
        private DoubleNode next;

        //初始化,因为成员变量的初始值为null,所以这里不用管
        public DoubleNode(int value) {
            this.value = value;
        }
    }


    /**
     * 单链表倒置,这里采用遍历的办法,递归,我没搞明白,就算了
     *  遍历的方法,就是不断的把头结点分割出去,然后将后面分割的头节点不断往前面放
     * @param head
     * @return
     */


    public static Node reverseList(Node head) {
        //判断当前节点是否有效
        if (head == null) {
            return null;
        }
        //前一个节点,用于保存分割出去的头结点
        Node pre = null;
        //下一个节点,用于保存下一个节点
        Node next = null;


        //到末尾了就结束循环
        while (head != null) {
            next = head.next;
            //把头结点分割出去
            head.next = pre;
            //把头结点作为父节点
            pre = head;
            //把next作为新的头节点
            head = next;
        }

        return pre;
    }
    /**
     * 反转其实和上面的一样,上面是把下一个节点指向上一个节点
     * 这里是把下一个指向上一个同时让上一个指向下一个节点
     * @param head 双向链表的头结点
     * @return 返回一个双向链表的头结点
     */
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        //判断当前节点是否有效
        if (head == null) {
            return null;
        }
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void pringDoubleList(DoubleNode head) {
        System.out.println("DoubleLinkedList : ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    public static void printList(Node head) {
        System.out.println("LinkedList : ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        printList(head);

        Node node = reverseList(head);
        printList(node);
        System.out.println("此时的头结点: " + node.value);

        DoubleNode head2 = new DoubleNode(4);
        head2.next = new DoubleNode(5);
        head2.next.next = new DoubleNode(6);

        pringDoubleList(head2);
    }
}
