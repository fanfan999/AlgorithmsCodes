package basic.basic;

import java.util.Stack;

/**
 * 给定一个单链表的头结点,实现一个调整单链表的函数,使得每K个节点之间逆序,如果最后不够K个节点,则不调整最后几个点
 * 例如:1->2->3->4->null, k = 3
 * 调整后为: 3->2->1->4->null
 * 方法1: 借助栈来实现,其时间复杂度为O(N),额外空间复杂度为O(K),其中K<=N
 */
public class ReverseInRange {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node reverseInRange(Node head, int k) {
        //当链表为空不需要调整
        if (head == null) {
            return head;
        }

        Node cur = head;
        Node pre = null;
        Node next = null;
        Stack<Node> stack = new Stack<>();

        while (cur != null) {
            stack.push(cur);
            next = cur.next;
            //当栈的大小为k时,说明就已经有k个了,开始逆序
            if (stack.size() == k) {
                //判断是否需要改变头结点,以1->2->3->4为例
                //当pre为null,说明是第一次逆序,就需要改变其头节点1为3
                head = pre == null ? cur : head;
                //第一次pre为null, next为3
                pre = reverseProcess(stack, pre, next);
            }
            cur = next;
        }

        return head;
    }

    /**
     *
     * @param stack 存放元素的栈,例如此时栈为3,2,1,链表实际上为1,2,3
     * @param pre 即将逆序的元素中最左边的前一个,第一次为1前面的一个,为null
     * @param next 即将逆序的元素中最右边的后一个,也就是下一次排序的开始
     * @return 返回本次逆序的最后一个元素,也就是下次逆序的开头的前一个元素
     */
    private static Node reverseProcess(Stack<Node> stack, Node pre, Node next) {

        Node cur = stack.pop();//第一次依次弹出3,2,1
        while (pre != null) {
            pre.next = cur;
        }

        Node temp = null;
        while (!stack.isEmpty()) {
            temp = stack.pop();
            cur.next = temp;
            cur = temp;
        }

        //使本次逆序结束后与下一次逆序的开头连接上,因为cur.next本来后面是连接的前面的值,不连接下一次逆序的开头的话输出会死循环的
        cur.next = next;
        return cur;
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
       /* head.next.next = new Node(3);
        head.next.next.next = new Node(4);*/

        Node node = reverseInRange(head, 3);
        while (node != null) {
            System.out.println(" " + node.value);
            node = node.next;
        }
    }
}
