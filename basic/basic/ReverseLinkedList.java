package basic.basic;

/**
 * 反转单链表
 */
public class ReverseLinkedList {
    //链表节点类
    public static class Node{
        private Integer value;
        private Node next;

        public Node(Integer value) {
            this.value = value;
        }
    }

    public static Node reverse(Node head){

        if (head == null) {
            return null;
        }

        //设置前一个节点和后一个节点
        Node pre = null;
        Node next = null;

        while (head != null) {
            //例如: 取出第二个节点,然后就第一个节点连接第-1个节点
            next = head.next;
            head.next = pre;
            //将head和pre都往后移
            pre = head;
            head = next;
        }

        return pre;
    }

    public static void print(Node head) {
        while (head != null) {
            System.out.print(" " + head.value);
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        print(head);

        Node node = reverse(head);

        print(node);
    }
}