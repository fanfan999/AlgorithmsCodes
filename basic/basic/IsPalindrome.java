package basic.basic;

import java.util.Stack;

/**
 * 方法1,借助栈辅助空间,
 */
public class IsPalindrome {
    public static class Node {
        private int value;
        private Node next;

        public Node(int val) {
            this.value = val;
        }
    }

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Stack<Node> stack = new Stack<>();
        Node node = head;
        boolean flag = true;

        //压栈
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        //弹栈的过程中和原链表比较
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            //一旦有不相等的就退出
            if (head.value != temp.value) {
                flag = false;
                break;
            }else {
                head = head.next;
            }
        }

        return flag;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(2);

        boolean flag = isPalindrome(head);

        System.out.println(flag);
    }
}
