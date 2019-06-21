package basic.basic;

/**
 * 打印两个链表的公共部分
 */
public class PrintCommonPart {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     *
     * @param head1 链表1的头结点
     * @param head2 链表2的头结点
     */
    public static void printCommon(Node head1, Node head2) {
        //任意一个头结点为空,就说明两个链表不可能有相交的部分
        if (head1 == null || head2 == null) {
            return;
        }
        Node temp1 = head1;
        Node temp2 = head2;
        int len1 = 0;
        int len2 = 0;

        //判断哪个链表更长,用短的那个作为判断条件
        while (head1 != null) {
            len1++;
            head1 = head1.next;
        }
        while (head2 != null) {
            len2++;
            head2 = head2.next;
        }

        if (len1 > len2) {
            printProcess(temp1, temp2, temp2);
        }else {
            printProcess(temp1, temp2, temp1);
        }
    }

    /**
     *
     * @param head1 链表1的头结点
     * @param head2 链表2的头结点
     * @param count 较短的链表的头结点,作为结束条件的判断
     */
    private static void printProcess(Node head1, Node head2, Node count) {
        while (count != null) {
            if (head1.value == head2.value){
                System.out.println(head1.value);
            }
            head1 = head1.next;
            head2 = head2.next;
            count = count.next;
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(3);

        Node head2 = new Node(3);
        head2.next = new Node(2);
        head2.next.next = new Node(1);
        head2.next.next.next = new Node(2);

        printCommon(head1, head2);
    }
}
