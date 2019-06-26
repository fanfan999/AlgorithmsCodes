package basic.day05;

/**
 * 二叉树遍历递归版本
 * 把每个三角形都看成一个二叉树嘛,分别都有头节点和左右子节点
 */
public class PreInPosTraversal {
    //二叉树节点类,看吧,和链表差不多的
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    /**
     * 先序遍历,先父节点(头节点),再左子节点,再右子节点
     *
     * @param root 根节点,刚开始是根节点,后来遍历的过程中,他就是每一个节点
     */
    public static void preOrderTraversal(Node root) {
        //当当前节点为空时,就直接返回
        if (root == null) {
            return;
        }

        //因为要先打印头节点,所以当root不空时,第一个就是头节点,直接打印
        System.out.print(root.value + "->");
        //然后把自己的左子节点当成父节点,使得遍历往下走
        preOrderTraversal(root.left);
        //左边走完了,就往右边走
        preOrderTraversal(root.right);
    }

    /**
     * 中序遍历: 左节点->头结点->右节点
     *
     * @param root 每次遍历的根节点,也就是三角形中间那个,
     */
    public static void inOrderTraversal(Node root) {
        //当头结点为null,就说明已经到了叶子节点了
        if (root == null) {
            return;
        }
        //没到叶子节点就一直左移
        inOrderTraversal(root.left);
        //走到这里说明已经到了叶子节点了,也就是最左边的,直接打印,然后返回第二层
        System.out.print(root.value + "->");
        //把右边的当成一颗独立的树,做同样的操作
        inOrderTraversal(root.right);
    }

    /**
     * 后序遍历,左子结点->右子节点->头节点
     *
     * @param root
     */
    public static void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        //先往左边走
        postOrderTraversal(root.left);
        //左边走完然后右边走
        postOrderTraversal(root.right);
        //左右两边都走完了,就剩头结点了,刚好头结点是最后打印,直接打印
        System.out.print(root + "->");
    }


    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        preOrderTraversal(root);
        System.out.println();
        System.out.println("--------------");
        inOrderTraversal(root);
        System.out.println();
        System.out.println("--------------");
    }
}
