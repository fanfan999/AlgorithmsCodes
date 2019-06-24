package basic.basic;

/**
 * 判断是否为回文序列方法3
 */
public class IsPalindrome3 {
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
        //记录下半部分,也就是逆序的开始节点
        Node start = null;
        //记录前半部分的头结点
        Node beforeHead = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        /**
         * 走到这步之后,以1->2->2->1->null和1->2->3->2->1->null为例
         * 奇数个的话,slow指向第2个数2
         * 偶数个的话,slow指向第3个数3
         * mid
         */
        start = slow.next;
        //让slow的next指向null,走到这里也就表明上半部分和下半部分要开始区分了
        slow.next = null;

        System.out.println("逆序前:");
        print(beforeHead);

        //使下半部分逆序,返回下半部分逆序后的头结点behindHead,beforeHead为上半部分的头结点
        Node behindHead = reverse(start, slow);
        //记录下逆序后的节点的开始,为调整节点为原来的样子做准备
        Node temp = behindHead;

        System.out.println("逆序后:");
        print(behindHead);

        /**
         * 比较,这里不用管奇数个还是偶数个
         * 因为逆序后的是和中间的连着的,奇数的话都会比较中间的,永远相等,偶数的话,前面的到null了就结束就好了
         * 不懂的话对着图看看代码应该就知道了,再不懂就给我留言吧
         */

        while (beforeHead != null) {
            if (beforeHead.value != behindHead.value) {
                flag = false;
                break;
            }
            beforeHead = beforeHead.next;
            behindHead = behindHead.next;
        }

        /**
         * 恢复链表原来的样子也就是把原来逆序的部分再次逆序回来
         * 直接调用我们的逆序函数就好了,这里的temp就是原来逆序后的头结点
         */
        reverse(temp, null);
        System.out.println("恢复后的链表");
        print(head);

        return flag;
    }

    /**
     * 逆序下半部分
     *
     * @param start 中间节点,也就是逆序的前一个节点
     * @param slow 就是逆序开始的前一个节点,也是上半部分的最后一个节点
     * @return 返回逆序后的头结点
     */
    private static Node reverse(Node start, Node slow) {
        Node pre = slow;
        Node next = null;
        Node cur = start;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public static void print(Node head) {
        System.out.println("----------------");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
        System.out.println("----------------");
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(3);
        head.next.next = new Node(4);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(1);

        boolean flag = isPalindrome(head);

        System.out.println(flag);
    }
}
