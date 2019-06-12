package basic.day04;

/**
 * 将单向链表按照某个值划分成左边小,中间相等,右边大的情况
 * 不保证有序,类似于荷兰国旗问题
 * 思路:
 *  将node对象都放在数组中,在数组中按照value的大小排序,排好序以后再连接成链表形式
 */
public class ComparesWithPivot {
    //给定节点类
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node compareWithPivot(int pivot, Node head) {
        //如果链表为空,直接返回
        if (head == null) {
            return null;
        }
        //定义数组的长度
        int len = 0;
        //定义一个数组
        Node[] array = null;
        //定义一个临时节点变量存储头结点的值
        Node cur = head;
        //由于不知道链表的长度,所以先得到链表长度给数组初始化
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        //System.out.println("array.length= " + len);
        //对数组进行初始化
        array = new Node[len];

        //将节点复制到数组中去
        for (int i = 0; i < len; i++) {
            array[i] = head;
            head = head.next;
        }
        //比较过程
        listPatition(array, pivot);

        //将数组还原为链表,此时head为null
        /*for (int i = 1; i < len; i++) {
            array[i - 1].next = array[i];
        }
        array[len - 1].next = null;*/

        head = new Node(array[0].value);
        cur = head;
        Node t = null;
        for (int i = 1; i < len; i++) {
            t = new Node(array[i].value);
            cur.next = t;
            //这一句就相当于使得cur向右移动一位
            cur = t;
        }
        printList(head);
        System.out.println("===============");
        printList(cur);

        System.out.println("----------");
        getLen(array[0]);
        System.out.println("----------");
        return head;
    }

    /**
     * 数组划分的过程
     * @param array
     * @param pivot
     */
    private static void listPatition(Node[] array, int pivot) {
        //左边界刚开始为在第一元素的前一位
        int left = -1;
        //右边界刚开始在最后一个元素的后一位
        int right = array.length;
        //移动变量,记录移动的位置
        int temp = 0;

        //当移动变量超过右边界时循环停止
        while (temp < right) {
            //当移动变量当前值比pivot大,右边界前一位元素与当前元素交换,然后右边界左移一位,但是移动变量不动
            if (array[temp].value > pivot) {
                swap(array, temp,--right);
            }else if (array[temp].value < pivot){
                //当移动变量当前值比pivot小
                //左边界后一位与移动变量当前值交换,临时变量右移,左边界右移
                swap(array, temp++, ++left);
            }else {
                //当移动变量当前值与pivot相等
                //临时变量右移
                temp++;
            }
        }

        //此时数组已经排好序了
        for (int i = 0; i < array.length; i++) {
            System.out.print(" " + array[i].value);
        }
        System.out.println();
    }

    private static void swap(Node[] array, int temp, int i) {
        Node node = array[temp];
        array[temp] = array[i];
        array[i] = node;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(" " + head.value);
            head = head.next;
        }
        System.out.println();
    }

    public static int getLen(Node head) {
        int i = 0;
        while (head != null) {
            i++;
            head = head.next;
        }

        System.out.println("length = " + i);

        return i;
    }

    public static void main(String[] args) {
        Node head = new Node(3);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        head.next.next.next.next = new Node(0);

        printList(head);

        Node node = compareWithPivot(1, head);
        printList(node);
    }
}
