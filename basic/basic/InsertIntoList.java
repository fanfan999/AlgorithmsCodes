package basic.basic;

/**
 * 将给定值num插入到有序环形链表中并保证插入后链表依旧有序
 * 1. 如果原链表为空,将num节点直接自己变成一个环形
 * 2. 如果链表不为空,定义一个pre节点和next节点
 * 将num插入到pre>=num>=next的位置
 * 如果找了一圈还是没有,就说明应该放在头节点前面,也就是最开始的那个pre节点
 */
public class InsertIntoList {

    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void print(Node head) {
        Node next = head;
        System.out.print(next.value);
        while (next.next != head) {
            next = next.next;
            System.out.print(" " + next.value);
        }
        System.out.println();
    }

    public static Node insert(Node head, int num) {
        //生成一个新的节点
        Node node = new Node(num);
        //如果链表为空,让它自己串成一个链表
        if (head == null) {
            node.next = node;
            return node;
        }

        Node pre = head;
        Node next = head.next;
        while (pre.next != head) {
            //如果找到num的值在两个节点中间,插进去
            if (pre.value <= node.value && next.value >= node.value) {
                pre.next = node;
                node.next = next;
                return head;
            }
            //没有找到就继续循环
            pre = pre.next;
            next = next.next;
        }

        //到这里了就说明没有,那就应该插在头结点前面
        pre.next = node;
        node.next = head;
        if (num >= head.value) {
            return head;
        }else {
            return node;
        }

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = head;
        print(head);

        Node node = insert(head, 5);
        print(node);
    }
}
