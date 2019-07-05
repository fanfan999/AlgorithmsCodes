package basic.day05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 */
public class SerializeTreeByPre {

    public static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 如果左子树或右子树为空,则返回#_,这是为了在还原树的过程不出现问题
     * 比如说如果某个数只有左子树和只有右子树,且值全为1,那么如果不考虑空的话,遍历形成的字符串是一样的都是1->1->1这样子,就没办法恢复了.
     * 不为空就返回节点值+_.
     * 这段代码宏观来看,就是先拼接根节点,然后拼接其左右子树
     *
     * @param root 传进来的是根节点
     * @return
     */
    public static String serializeByPre(Node root) {
        if (root == null) {
            return "#_";
        }
        //将头结点作为字符串
        String str = root.val + "_";
        //拼接左子树
        str += serializeByPre(root.left);
        //左边拼接完了就拼接右子树
        str += serializeByPre(root.right);

        return str;
    }

    /**
     * 反序列化不借助辅助空间,现将其分割为一个数组,然后遍历数组,重建二叉树
     *
     * @param str
     * @return
     */
    public static Node deserializeByPre(String str) {
        String[] values = str.split("_");
        if (values.length == 0) {
            return null;
        }
        return reconPreOrder(values);
    }

    public static int index = -1;

    private static Node reconPreOrder(String[] values) {

        index++;
        //当数组中出现#时,说明到了空节点,就往上一层返回
        if (values[index].equals("#")) {
            return null;
        }
        //头结点不为空,就设置头结点
        Node head = new Node(Integer.parseInt(values[index]));
        //连接其左子树
        head.left = reconPreOrder(values);
        //连接其右子树
        head.right = reconPreOrder(values);

        return head;
    }

    /**
     * 反序列化,借助辅助空间队列,这个其实好理解多了
     *
     * @param str
     * @return
     */
    public static Node deserializeByPre1(String str) {
        String[] values = str.split("_");
        Queue<String> queue = new LinkedList<>();

        for (int i = 0; i < values.length; i++) {
            queue.offer(values[i]);
        }

        return reconPreOrder1(queue);
    }

    private static Node reconPreOrder1(Queue<String> queue) {
        //依次取出节点
        String value = queue.poll();
        //为空就返回
        if (value.equals("#")) {
            return null;
        }

        //设置头结点
        Node head = new Node(Integer.parseInt(value));
        //设置左子树
        head.left = reconPreOrder1(queue);
        //设置右子树
        head.right = reconPreOrder1(queue);

        return head;
    }

    //打印树的代码
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.val + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);

        String str = serializeByPre(head);
        System.out.println(str);
        System.out.println("---------------------");

        Node root = deserializeByPre(str);
        printTree(root);
        System.out.println();
        System.out.println("---------------------");

        Node root1 = deserializeByPre1(str);
        printTree(root1);
        System.out.println();
        System.out.println("---------------------");
    }
}
