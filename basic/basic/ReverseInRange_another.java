package basic.basic;

/**
 * 每隔几个数就反转链表不用借助辅助结构,时间复杂度为O(N),空间复杂度为O(1)
 * 本代码以1->2->3->4->null为例
 * 这个题写法有点绕,用栈想清楚了再来写就好很多
 */
public class ReverseInRange_another {
    //节点类,链表都要写
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void reverese(Node head, int k) {
        if (head == null) {
            return;
        }
        //用于计数,当它是3的倍数的时候,就反转链表
        int count = 0;
        //反转链表的三个变量,都要有
        //pre表示要反转的链表的前一个,也就是1的前一个,刚开始为null
        Node pre = null;
        //链表操作一般不要动头结点,怕搞混
        Node cur = head;
        //next表示反转链表的下一个,也就是3的下一个4
        Node next = null;
        Node temp = head;

        while (cur != null) {

            //取下一个元素,要保证逆序的时候,next指向的是当前节点的下一个节点
            next = cur.next;

            //因为0%3也等于0,所以要先++
            count++;

            //当count是3的倍数时,就说明有3个数了,反转链表
            if (count % 3 == 0) {
                //如果是第一次反转链表,要改变其头结点为第三个
                head = pre == null ? cur : head;
                if (pre == null) {
                    pre = change(temp,pre, next, k);
                }else {
                    pre = change(pre.next,pre, next, k);
                }
            }

            cur = next;

        }

        print(head);
    }

    /**
     * 逆序
     *
     * @param head
     * @param pre
     * @param next
     * @param k
     */
    private static Node change(Node head, Node pre, Node next, int k) {

        //记录下本次逆序的第一元素,用于等会和下一次逆序的第一个元素连接
        Node start = head;
        //经典逆序代码
        //left就相当于经典逆序的pre
        Node left = null;
        //right就相当于经典逆序的next
        Node right = null;

        for (int i = 0; i < k; i++) {
            right = head.next;
            head.next = left;
            left = head;
            head = right;
        }

        //如果逆序的前一个节点不为空,就说明不是第一次逆序,那就要把前面的和后面的连起来
        if (pre != null) {
            pre.next = left;
        }

        //将本次逆序的最后一个节点和下一次逆序的头结点连起来
        start.next = next;

        return left;
    }

    private static void print(Node head) {
        while (head != null) {
            System.out.print(" " + head.value);
            head = head.next;
        }
        System.out.println();
        System.out.println("-------------------");
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        print(head);

        reverese(head, 3);

    }


}
