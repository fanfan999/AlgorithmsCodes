package basic.basic;

/**
 * 判断两个无环链表是否相交
 *  保证额外空间复杂度为O(1)
 */
public class IntersectList2 {
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
        //定义一个节点用于存放相交的节点
        Node node = null;
        //定义两个链表的长度变量
        int len1 = 0;
        int len2 = 0;
        //用于记录两个节点的长度差
        int gap = 0;
        //定义两个节点指向链表的头节点
        Node temp1 = head1;
        Node temp2 = head2;

        while (temp1 != null) {
            len1++;
            temp1 = temp1.next;
        }

        while (temp2 != null) {
            len2++;
            temp2  = temp2.next;
        }
        //让两个临时变量再次指向头结点
        temp1 = head1;
        temp2 = head2;

        //谁长谁先动
        if (len1 >= len2) {
            gap = len1 - len2;
            for (int i = 0; i < gap; i++) {
                temp1 = temp1.next;
            }
        }else {
            gap = len2 - len1;
            for (int i = 0; i < gap; i++) {
                temp2 = temp2.next;
            }
        }

        //走到这步,此时两个链表长度一样了,同时移动
        while (temp1 != null && temp2 != null) {
            if (temp1 == temp2) {
                node = temp1;
                break;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
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
