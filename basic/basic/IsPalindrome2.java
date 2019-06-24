package basic.basic;

import java.util.Stack;

/**
 * 判断是否为回文序列方法2
 */
public class IsPalindrome2 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int val) {
            this.value = val;
        }
    }

    public static boolean isPalindrome(Node head) {
        boolean flag = true;

        if (head == null || head.next == null) {
            return true;
        }

        //快慢指针,刚开始都指向头节点
        Node fast = head;
        Node slow = head;
        Stack<Node> stack = new Stack<>();

        //这里用且运算,使得奇数个数的话,慢指针就在中间的前一个,快指针就在中间的前两个,你举个例子就晓得了
        while (fast.next != null && fast.next.next != null) {
            stack.push(slow);
            slow = slow.next;
            fast = fast.next.next;
        }

        //通过判断快指针的next是否为null来判断是奇数个还是偶数个
        if (fast.next == null) {
            //奇数个的话,直接跳过中间的,指向中间位置的下一个
            slow = slow.next;
        }
        if (fast.next != null && fast.next.next == null) {
            //偶数个的话,比如如果有4个,此时慢指针指向的是第二个,所以还需要压栈一个
            stack.push(slow);
            //然后让慢指针指向第三个,也就是下半部分的第一个
            slow = slow.next;
        }

        //走到这里说明已经慢指针到了后半部分,弹栈依次比较
        Node temp = stack.pop();
        while (!stack.isEmpty()) {
            if (temp.value != slow.value) {
                flag = false;
                break;
            }else {
                temp = stack.pop();
                slow = slow.next;
            }
        }

        return flag;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(3);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);

        boolean flag = isPalindrome(head);

        System.out.println(flag);
    }
}
