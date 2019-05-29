package basic.day04;

import java.util.Stack;

/**
 * 回文序列
 *  1. 将该序列放入一个栈中,然后从头开始遍历链表,依次和栈弹出来的数相比较,如果到结束前都一模一样,返回true,一旦有不一样的,返回false.
 *  2. 就是一个快节点(一个走两步),一个慢节点(一次走一步),当快节点到末尾的时候,慢节点就刚好到中间,慢节点的next置为null,然后就慢节点从头开始,快节点倒着回来比较,完全一样就返回true
 */
public class PalindromeSequence {

    public static class Node{
        private Node next;
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 思路一: 需要用到o(n)的额外空间复杂度
     * @param head 头节点
     */
    public static boolean isPalindromeSequence1(Node head) {
        //定义一个栈存放给定链表的数据
        Stack<Integer> helpStack = new Stack<Integer>();
        //因为要变量两次,第一次的时候头结点不能变,所以我们定义一个节点变量作为第一次遍历的head
        Node cur = head;

        //当头节点不为空,即没到末尾时,就一直放到栈中
        while (cur != null) {
            helpStack.push(cur.value);
            cur = cur.next;
        }

        //都放到栈中以后,再次遍历,弹栈和链表比较,都相同则返回true,反之false
        while (head != null) {
            //只要二者不相等,就返回false
            if (head.value != helpStack.pop().intValue()) {
                return false;
            }
            //反之继续比较
            head = head.next;
        }

        //走到这里都还没有出错的话, 就说明都一样,返回true
        return true;
    }

    /**
     * 思路一: 只需要用o(n/2)的辅助空间
     * @param head
     * @return
     */
    public static boolean isPalindromeSequence2(Node head) {
        //判断链表的有效性
        if (head == null || head.next == null) {
            return true;
        }

        //定义快慢节点指针
        Node fast = head;
        Node slow = head;

        //当快指针的下一个和下下一个都不为空时,慢指针就变为下一个节点
        while (fast.next != null && fast.next.next != null) {
            //慢指针右移
            slow = slow.next;
            fast = fast.next.next;
        }

        Stack<Integer> helpStack = new Stack<Integer>();
        //因为要保证偶数的时候栈中只有一半,奇数的时候栈中只有一半减一所以从slow.next开始取
        Node help = slow.next;

        //把慢指针往右的,不包括当前慢指针,都放到栈中去
        while (help != null) {
            helpStack.push(help.value);
            help = help.next;
        }

        //将stack的元素依次弹栈比较
        while (!helpStack.isEmpty()) {
            //当任意元素不相等时,就返回false
            if (helpStack.pop().intValue() != head.value) {
                return false;
            }
            //相等就继续比较
            head = head.next;
        }
        return true;
    }

    /**
     * 思路二: 额外空间复杂度为o(1)
     * 这个搞个例子在旁边,一边看一边写容易理解一点
     * @param head
     * @return
     */
    public static boolean isPalindromeSequence3(Node head) {
        //判断链表有效性
        if (head == null || head.next == null) {
            return true;
        }

        //定义快慢节点指针
        Node fast = head;
        Node slow = head;

        //找到中间位置,奇数的时候正中间,偶数的时候就到中间的前一个,比如4个元素就在第二个元素,即下标为1
        while (fast.next != null && fast.next.next != null) {
            //慢指针右移
            slow = slow.next;
            fast = fast.next.next;
        }
        //找到中间位置以后,将快指针指向慢指针的下一个
        fast = slow.next;
        //然后将慢指针的next置空
        slow.next = null;
        //定义一个辅助节点
        Node help = null;
        //将后半部分逆序,也就是把原来1->2->3->3->2->1变成1->2->3<-3<-2<-1这样子
        while (fast != null) {
            //辅助节点取快节点下一个元素2,这是为了下一次运算
            help = fast.next;
            //把当前节点3连接到慢节点的最后一个即3,变成3<-3这样,这就逆序了
            fast.next = slow;
            //令慢节点等于快节点当前元素即后半部分的第一个元素3
            slow = fast;
            //令快节点等于2
            fast = help;
        }
        //走到这里说明逆序完成,
        //定义两个节点,一个表示链表前半部分开始1,另一个表示链表后半部分开始1,即最后一个元素,这里right不能等于fast,因为此时fast是null
        Node left = head;
        Node right = slow;
        boolean flag = true;
        //当两个节点相遇,比较结束
        while (left != null && right != null) {
            if (left.value != right.value) {
                flag = false;
            }
            left = left.next;
            right = right.next;
        }
        //把右边调回来,此时slow还是在最右边,从右往左逆序
        right = slow.next;//2
        slow.next = null;
        Node temp = null;
        while (right != null) {
            temp = right.next;//3
            right.next = slow;//1
            slow = right;
            right = temp;
        }
        printList(head);
        return flag;
    }
    public static void printList(Node head) {
        while (head != null) {
            System.out.print(" " + head.value);
            head = head.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);

        printList(head);

        boolean flag = isPalindromeSequence1(head);
        System.out.println(flag);
        System.out.println("--------------------");

        boolean flag1 = isPalindromeSequence2(head);
        System.out.println(flag1);
        System.out.println("--------------------");
        boolean flag2 = isPalindromeSequence3(head);
        System.out.println(flag2);
    }
}
