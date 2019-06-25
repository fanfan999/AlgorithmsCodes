package basic.basic;

import java.util.HashSet;

/**
 * 有环链表的相交
 */
public class IntersectLinkedList {
    public static class Node {
        private int value;
        private Node next;

        public Node(int val) {
            this.value = val;
        }
    }

    public static Node ifIntersect(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        //定义记录两个链表头结点的变量
        Node temp1 = head1;
        Node temp2 = head2;
        //定义相交的节点变量
        Node node = null;
        //定义两个入环节点的变量
        Node entry1 = null;
        Node entry2 = null;

        //找到两个链表的入环节点
        entry1 = findEntryNode(temp1);
        entry2 = findEntryNode(temp2);
        System.out.println(entry1.value + ":" + entry2.value);

        //情况一,两个链表入口节点为同一个,找之前的相交节点,这里用哈希
        if (entry1 == entry2) {
            node = findIntersectNodeBySituation1(temp1, temp2, entry1);
        }else {
            //两个链表入口节点不是同一个
            //遍历任意一个链表的环,找不到另一个入口节点则说明两者不相交,找到则说明相交,返回任意一个节点都行
            Node temp = entry1.next;
            while (temp != entry1) {
                if (temp == entry2) {
                    node = temp;
                    break;
                }
                temp = temp.next;
            }
        }

        return node;
    }

    /**
     * 情况1,通过hashset找相交节点
     *
     * @param head1 链表1的头结点
     * @param head2 链表2的头结点
     * @param end   遍历结束位置
     * @return 两个链表第一次相交节点
     */
    private static Node findIntersectNodeBySituation1(Node head1, Node head2, Node end) {
        //记录返回值
        Node node = null;
        boolean flag = true;

        HashSet<Node> set = new HashSet<>();
        while (head1 != end) {
            set.add(head1);
            head1 = head1.next;
        }

        while (head2 != end) {
            flag = set.add(head2);
            if (flag == false) {
                node = head2;
                break;
            }
        }

        return node;
    }

    /**
     * 采用快慢指针方法找到入环节点
     *
     * @param head 有环链表的头结点
     * @return 返回入环节点
     */
    private static Node findEntryNode(Node head) {
        //记录入环节点
        Node node = null;
        //声明快慢指针
        Node fast = head;
        Node slow = head;
        //记录头结点的临时变量
        Node temp = head;

        //当快慢指针第一次相遇后,快指针回到头结点重新开始
        while (true) {
            fast = fast.next.next;
            slow = slow.next;
            //因为刚开始都是head,肯定是相等的,所以先移动再比较
            if (fast == slow) {
                //找到入口节点,退出循环
                node = getEntryNode(temp, fast, slow);
                break;
            }
        }

        return node;
    }

    /**
     * 让快指针回到头结点,,然后一次移动一个位置,慢指针继续移动
     * 下次相遇,就找到单个环形链表的入环节点
     *
     * @param head 就是链表的头节点
     * @param fast 快指针
     * @param slow 慢指针
     * @return 返回入口节点
     */
    private static Node getEntryNode(Node head, Node fast, Node slow) {
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

    public static void main(String[] args) {
        //这是各自成环,不相交的情况,其余情况我都试过了,是对的
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = head1.next;

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = head2.next;

        Node node = ifIntersect(head1, head2);
        if (node != null) {
            System.out.println(node.value);
        } else {
            System.out.println(node);
        }

    }
}
