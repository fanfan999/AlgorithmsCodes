package basic.basic;

/**
 * 删除值为给定值得节点代码
 * 本代码以1->2->3->4->null为例
 */
public class DeleteSpecificNode {
    public static class Node{
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void deleteNode(Node head, int val) {
        if (head == null) {
            return;
        }

        Node pre = null;
        Node cur = head;
        Node next = null;

        while (cur != null) {
            //先获得cur的下一个节点
            next = cur.next;
            if (cur.value == val) {
                //pre为null说明是头节点,那就直接让头结点等于下一个节点就好了,但是pre不变
                if (pre == null) {
                    head = next;
                }else {
                    //删除的不是头结点的话,就让前一个节点的next指针指向下一个节点就好了
                    pre.next = next;
                }
            }else {
                //如果节点值不等于val,那就让pre等于当前节点值,因为下一步当前节点cur就要向下移,这就使得pre为cur的上一个节点了
                pre = cur;
            }
            cur = next;
        }

        print(head);
    }

    private static void deleteProcess(Node pre,Node next) {
        pre.next = next;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        print(head);

        deleteNode(head, 1);
    }

    private static void print(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
        System.out.println("------------------");
    }
}
