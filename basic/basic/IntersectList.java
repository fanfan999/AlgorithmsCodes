package basic.basic;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断两个无环链表是否相交
 * 通过hashset实现
 */
public class IntersectList {
    public static class Node {
        private int value;
        private Node next;

        public Node(int val) {
            this.value = val;
        }
    }

    /**
     * @param head1 链表1的头结点
     * @param head2 链表2的头结点
     * @return
     */
    public static Node ifIntersect(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        //set集合用于存放节点
        Set<Node> set = new HashSet<>();
        //声明一个节点用于存放链表相交的地方
        Node node = null;
        //用于判断是否到了相交的点
        boolean flag = false;

        //遍历链表1
        while (head1 != null) {
            set.add(head1);
            head1 = head1.next;
        }

        //遍历链表2
        while (head2 != null) {
            flag = set.add(head2);
            //当flag为false时,说明到了第一个相交的节点
            if (flag == false) {
                node = head2;
                break;
            }
            head2 = head2.next;
        }

        return node;
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);

        Node head2 = new Node(1);
        head2.next = head1.next.next;

        Node node = ifIntersect(head1, head2);
        if (node != null) {
            System.out.println(node.value);
        } else {
            System.out.println(node);
        }

    }
}
