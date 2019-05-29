package basic.day04;

import java.util.ArrayList;

/**
 * 打印两个链表公共部分
 */
public class PrintCommonPartOfList {

    public static class Node{
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void printCommomPart(Node head, Node head2) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        System.out.println("common part: ");
        //当任意一个链表到末尾就停止循环
        while (head != null && head2 != null) {
            if (head.value == head2.value) {
                if (!list.contains(head.value)){
                    list.add(head.value);
                }
                head = head.next;
                head2 = head2.next;
            }else if (head.value > head2.value) {
                head2 = head2.next;
            }else {
                head = head.next;
            }
        }

        //打印链表
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    public static void pringList(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        pringList(head);

        Node head2 = new Node(2);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        pringList(head2);

        printCommomPart(head, head2);
    }
}
