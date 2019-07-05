package basic.day05;

/**
 * 判断一个树是否是平衡二叉树
 *  1. 判断根节点的左子树是否是平衡的.
 *  2. 判断根节点的右子树是否是平衡的.
 *  3. 左右子树都平衡后,返回左右子树的高度
 *  4. 返回的高度就是根节点的左右子节点的高度,如果差小于等于1,返回true
 */
public class IsBalanceTree {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    //整合需要返回的信息
    public static class ReturnData{
        public boolean isB;
        public int h;

        public ReturnData(boolean isB, int h) {
            this.isB = isB;
            this.h = h;
        }
    }

    /**
     * 整个递归过程
     *  注意: 递归都是要有两个返回值的
     *      1. 一个是返回上一层的返回值,一般都是第一句那些判断空
     *      2. 另一个是总体的返回值,就是当前面所有条件都满足时,整个递归已经结束了的时候的返回值.
     * @param root 这就是左右子树的父节点,刚开始就是根节点.
     * @return
     */
    public static ReturnData process(Node root) {
        //如果这是一颗空树,是平衡的
        if (root == null) {
            return new ReturnData(true, 0);
        }
        //不是空树的话,我就要拿到我左子树的信息和右子树的信息
        ReturnData leftData = process(root.left);
        //如果左子树不平衡,直接返回
        if (!leftData.isB) {
            //此时的高度不重要了,啥都行,因为我只需要这个false就可以了
            return new ReturnData(false, 0);
        }
        ReturnData rightData = process(root.right);
        //如果右子树不平衡,直接返回
        if (!rightData.isB) {
            //此时的高度不重要了,啥都行,因为我只需要这个false就可以了
            return new ReturnData(false, 0);
        }

        //走到这里说明左右子树都平衡的,返回他们的高度判断根节点的左右节点
        //如果左右子节点的高度差大于1,不是平衡二叉树,返回false
        if(Math.abs(leftData.h - rightData.h) > 1) {
            return new ReturnData(false, 0);
        }

        //走到这里,说明是平衡二叉树,那么根节点的高度就是左右子节点较高的那个＋1,因为这个根节点可能是上一层的子节点,所以高度不能瞎写
        return new ReturnData(true, Math.max(leftData.h, rightData.h) + 1);
    }

    //调用函数
    public static boolean isBalance(Node root) {
        //返回递归过程中返回值得布尔值
        return process(root).isB;
    }

    //打印树的代码,这个直接用就好了
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
        String val = to + head.value + to;
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
        head.right.left = new Node(3);
        printTree(head);
        System.out.println("-------------------");

        boolean b = isBalance(head);
        System.out.println(b);
    }
}
