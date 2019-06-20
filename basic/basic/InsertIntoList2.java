package basic.basic;

/**
 * 将指定数插入到链表中,分成左边小右边大中间相等的三部分,不借助辅助空间,额外空间复杂度为O(1)
 */
public class InsertIntoList2 {
    public static class Node{
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    //这里要注意一下条件,什么时候插入哪一个链表,
    public static void insert(Node head, int num) {
        Node less = null;
        //aLess变量是为了指向less最开始的位置,因为less是要移动的
        Node aLess = null;
        Node equal = new Node(num);
        Node aEqual = equal;
        Node more = null;
        Node aMore = null;

        //将不同的数分到不同的链表中,注意条件就行
        while (head != null) {
            if (head.value > num) {
                if (more == null) {
                    more = new Node(head.value);
                    aMore = more;
                }else {
                    more.next = new Node(head.value);
                    more = more.next;
                }
            }else if (head.value < num) {
                if (less == null) {
                    less = new Node(head.value);
                    aLess = less;
                }else {
                    less.next = new Node(head.value);
                    less = less.next;
                }
            }else {
                equal.next = new Node(head.value);
                equal = equal.next;
            }
            head = head.next;
        }

        //走到这里,说明大中小三个链表都连接好了,把三个链表连接起来就好了
        connect(aLess, aEqual, aMore);
    }

    /**
     *
     * @param less
     * @param equal 不可能为null
     * @param more
     */
    private static void connect(Node less, Node equal, Node more) {
        Node head = null;
        if (less == null) {
            head = equal;
            while (equal.next != null) {
                equal = equal.next;
            }
            equal.next = more;
        }else {
            head = less;
            while (less.next != null) {
                less = less.next;
            }
            less.next = equal;

            while (equal.next != null) {
                equal = equal.next;
            }
            equal.next = more;
        }

        System.out.println("connect:");
        while (head != null) {
            System.out.print(" " + head.value);
            head = head.next;
        }
    }

    public static void print(Node head) {
        System.out.println("print: ");
        while (head != null) {
            System.out.print(" " + head.value);
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.next = new Node(4);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        print(head);


        insert(head, 4);
    }
}
