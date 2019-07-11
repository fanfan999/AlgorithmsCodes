package basic.day05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树按层实现序列化和反序列化
 * 在序列化的时候,就不能用递归了,因为递归它会一直向下走,我们要通过其它办法控制一层一层的走
 *  在同一层的时候从左往右走
 */
public class SerializeTreeByLevel {

    public static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }
    }


    public static String serializeByLevel(Node root) {
        //空树就直接返回#_
        if (root == null) {
            return "#_";
        }

        String str = root.val + "_";
        Queue<Node> queue = new LinkedList<>();
        //先把头结点的值给它,我们要通过它来控制层数
        queue.offer(root);

        //当队列中不为空的时候,就说明还没有遍历结束
        while (!queue.isEmpty()) {
            //先把根结点弹出来,然后开始下一层
            root = queue.poll();

            //走它的左子树
            if (root.left != null) {
                str += root.left.val + "_";
                //将左节点加入到队列中,下一层从左边开始
                queue.offer(root.left);
            }else {
                str += "#_";
            }

            //走它的右子树
            if (root.right != null) {
                str += root.right.val + "_";
                //将右节点加入到队列中,下一层遍历时左边整完就整右边,因为队列先进先出
                queue.offer(root.left);
            }else {
                str += "#_";
            }

        }

        return str;
    }

    /**
     * 怎么遍历的就怎么恢复原状
     *  1. 我们先将字符串分割为字符数组
     *  2. 声明一个队列,以其是否为空作为循环结束条件
     *  3. 跟按层遍历一样的,当队列不为空,就说明字符数组还没完,就继续往下装
     *  4. 注意这个是一层一层的装,装完左边载装右边,搞个例子在旁边就明白了
     * @param str
     * @return
     */
    public static Node deserializeByLevel(String str) {
        //字符串长度为0,说明啥也没有,直接返回就完事了
        if (str.length() == 0) {
            return null;
        }

        String[] values = str.split("_");
        //声明一个变量用来控制数组下标
        int index = 0;
        //第一个元素肯定是根节点,这个没的说,
        Node root = generateNodeByString(values[index]);
        //声明一个节点去下面不断移动,因为这就是一个不断把子树看成一个二叉树的过程,
        // 让root移动的话,等会返回就只有一个元素了.
        Node head = root;
        //队列就是用来控制循环次数的
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        //队列不为空就说明数组还有元素
        while (!queue.isEmpty()) {
            head = queue.poll();
            //头结点为空了的话,这个完全走不下去,
            if (head != null) {
                //以根节点为例,这就是在放第二层,也就是根节点的左右子节点
                head.left = generateNodeByString(values[++index]);
                head.right = generateNodeByString(values[++index]);
            }

            //如果根节点的左右子节点不为空,也就是第二层的元素不为空
            //就将其作为头结点,去放它们的左右子节点,也就是第三层
            if (head.left != null) {
                queue.offer(head.left);
            }

            if (head.right != null) {
                queue.offer(head.right);
            }
        }

        return root;
    }

    /**
     * 通过传来的字符串生成对象的节点对象
     * @param value
     * @return
     */
    public static Node generateNodeByString(String value) {
        if (value.equals("#")) {
            return null;
        }

        return new Node(Integer.parseInt(value));
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

        String str = serializeByLevel(head);
        System.out.println(str);
        System.out.println("---------------------");

        Node root = deserializeByLevel (str);
        printTree(root);
        System.out.println();
        System.out.println("---------------------");

    }
}
