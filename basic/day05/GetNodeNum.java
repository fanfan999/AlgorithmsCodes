package basic.day05;

/**
 * 给定一个完全二叉树,求其节点的数目,要求时间复杂度小于O(N),N为节点的个数
 *
 * 这个思路绝了,搞算法的都是怎样的脑子啊
 */
public class GetNodeNum {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    public static int getNodeNum(Node root) {
        //根节点为空的话,节点为0个
        if (root == null) {
            return 0;
        }

        return countNum(root,1, mostLeftLevel(root, 1));
    }

    /**
     *看这个递归过程,你可以就先理解为根节点和左右子节点这三个节点嘛,这三个是对的其他的就也是对的
     * 你就以为1为根节点,2为左子节点,3为右子节点为例嘛
     * @param root 表示当前节点,刚开始就是根节点
     * @param level 这个表示当前节点root在第几层,刚开始root就是根节点,在第一层,之后是其左右子节点,那就是第二层
     * @param depth 这个表示整棵树最深的层数,这个永远不会变
     * @return
     */
    private static int countNum(Node root, int level, int depth) {
        //递归结束条件,当当前层level到了depth这一层,就说明是叶子节点了
        if (level == depth) {
            //叶子节点的节点数当然就是1了
            return 1;
        }

        /**
         * 如果当前层没有到最后一层,就要看根节点的右子树的深度是不是到了整棵树最深层
         * 到了,就说明左子树是满的
         */
        if (mostLeftLevel(root.right, level + 1) == depth) {
            //左子树是满的,先求出左子树+根节点合起来的总结点数,然后只对右子树递归求个数
            /**
             * 此时左子树的深度为:总深度-当前层,例如根节点深度为3,它左子节点深度为2
             * 那么左子树的总节点数为: 2^(总深度-当前层) - 1
             *
             * 加上根节点,左子树的节点总数为: 2^(总深度-当前层) - 1 + 1 = 2^(总深度-当前层)
             *
             * 1<<2就表示1x2^2 = 4;3<<2就表示3x2^2 = 12;
             */
            return ((1<<(depth - level)) + countNum(root.right, level+1, depth));
        }else {
            /**
             * 如果右子树没有到最深那层,说明左子树不一定满,但是右子树肯定比左子树少一层而且是满的
             *
             * 那么就求右子树的总个数+根节点,然后对左子树递归
             */
            return ((1<<(depth - level - 1)) + countNum(root.left, level + 1, depth));

        }
    }

    /**
     * 计算整棵树最大层数,也就是左边跑完的层数
     * @param root
     * @param i 从1开始,因为根节点的层数就是1
     * @return
     */
    private static int mostLeftLevel(Node root, int i) {
        while (root.left != null) {
            i++;
            root = root.left;
        }
        return i;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        int i = getNodeNum(head);
        System.out.println(i);
    }
}
