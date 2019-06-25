package basic.basic;

import java.util.HashSet;

/**
 * 如何判断一个单链表是否有环
 * 通过快慢指针判断
 */
public class HavingRingList1 {
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

        //定义一个临时指针记录头结点的位置
        Node temp = head;
        //定义快慢指针,刚开始都等于头结点
        Node fast = head;
        Node slow = head;
        //定义一个节点变量放环形入口位置
        Node node = null;

        while (fast != null) {
            //快指针一次走两步,慢指针一次走一步
            fast = fast.next.next;
            slow = slow.next;

            //当第一次相遇后,快指针回到头结点
            if (fast == slow) {
                node = findEntry(head, fast, slow);
                break;
            }

        }
        return node;
    }

    //找到环形的入口节点
    private static Node findEntry(Node head, Node fast, Node slow) {
        //让快指针从头结点开始重新遍历
        fast = head;
        //当下一次他俩相遇,就一定是在环的入口处
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
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
