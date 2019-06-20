package basic.basic;

/**
 * 借助辅助空间,类似于荷兰国旗思路
 */
public class InsertIntoList1 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node insert(Node head, int tar) {
        //将链表转化为数组
        Node[] nodes = changeToArray(head, tar);

        //对转换后的链表进行小的在左边,大的在 右边的排序
        sortProcess(nodes, tar);
        for (Node n : nodes) {
            System.out.print(" " + n.value);
        }
        System.out.println();

        //将数组转换为链表
        Node node = changeToList(nodes);
        while (node != null) {
            System.out.print(" " + node.value);
            node = node.next;
        }

        return null;
    }

    //切记这里一定要新建节点,因为本来这里面的节点的next都是有值的,不新建这样子会死循环
    private static Node changeToList(Node[] nodes) {
        Node head = new Node(nodes[0].value);
        Node cur = head;
        Node next = null;

        for (int i = 1; i < nodes.length; i++) {
           next = new Node(nodes[i].value);
           cur.next = next;
           cur = next;
        }

        return head;
    }

    private static void sortProcess(Node[] nodes, int tar) {
        int cur = 0;
        int less = -1;
        //将最后一个值tar包含较大值里面
        int more = nodes.length - 1;

        while (cur < more) {
            if (nodes[cur].value > tar) {
                exchange(nodes, cur, --more);
            }else if (nodes[cur].value < tar) {
                exchange(nodes, cur++, ++less);
            }else {
                cur++;
            }
        }

        //将最后一个元素放在较大值的最前面一个位置
        exchange(nodes, more, nodes.length - 1);

    }

    //交换元素
    private static void exchange(Node[] nodes, int cur, int i) {
        Node node = nodes[cur];
        nodes[cur] = nodes[i];
        nodes[i] = node;
    }

    //将链表转换为数组
    private static Node[] changeToArray(Node head, int tar) {
        Node aim = new Node(tar);
        int len = 0;
        int i = 0;
        Node cur = head;
        while (head != null) {
            len++;
            head = head.next;
        }

        Node[] nodes = new Node[len + 1];

        while (cur != null) {
            nodes[i++] = cur;
            cur = cur.next;
        }
        //将tar值放进数组
        nodes[len] = aim;

        return nodes;
    }


    public static void main(String[] args) {
        Node head = new Node(5);
        head.next = new Node(0);
        head.next.next = new Node(1);
        head.next.next.next = new Node(4);

        insert(head, 0);
    }
}
