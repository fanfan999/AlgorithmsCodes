package basic.basic;

import java.util.HashSet;

/**
 * 如何判断一个单链表是否有环
 *  通过hashset的返回值判断
 */
public class HavingRingList {
    public static class Node {
        private int value;
        private Node next;

        public Node(int val) {
            this.value = val;
        }
    }

    public static Node isRing(Node head) {
        if (head == null) {
            return null;
        }

        HashSet<Node> set = new HashSet<>();
        boolean b = false;
        Node node = null;
        while (head != null) {
            b = set.add(head);
            if (b == false) {
                node = head;
                break;
            }
            head = head.next;
        }
        return node;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = head.next;

        Node node = isRing(head);
        if (node != null) {
            System.out.println(node.value);
        } else {
            System.out.println(node);
        }

    }
}
