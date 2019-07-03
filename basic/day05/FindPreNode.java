package basic.day05;

/**
 * 给定一个节点,找到其先驱结点(中序遍历后该节点的上一个节点)
 * 写的时候画个例子在旁边嘛,这样就直观很多
 * 本代码以中序遍历为2->7->5->4->6->1->3为例,图就是博客里的图
 */
public class FindPreNode {

    public static class Node {
        private int val;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     *
     * @param tarNode 要找的就是tarNode在中序遍历后的上一个节点
     * @return
     */
    public static Node getPreNode(Node tarNode) {
        //如果tarNode无效就返回null
        if (tarNode == null) {
            return tarNode;
        }

        //定义一个节点表示当前节点
        Node curNode = tarNode;
        //定义一个节点表示父节点
        Node parNode = curNode.parent;
        //定义一个节点保存返回值
        Node retNode = null;

        //如果该节点有左子树,就找到左子树的最右边的节点
        if (curNode.left != null) {
            curNode = curNode.left;

            while (curNode.right != null) {
                curNode = curNode.right;
            }

            //走到这里说明已经到了左子树的最右边,这就是我们要找的节点
            retNode = curNode;
        }else {
            //如果没有左子树,就一直往父节点找,直到找到某个节点是其父节点的右节点,则该父节点为所求
            //不过要注意有可能是最左边的叶子节点,它的先驱节点为空,它会一直找到根节点,所以要加上parNode != null这个条件
            //表示如果到了根节点还没有找到就返回null
            while (parNode != null && parNode.right != curNode) {
                //没找到就一直往上走
                curNode = parNode;
                parNode = curNode.parent;
            }

            //走到这里说明要么找到了,要么到了根节点,直接返回parNode就成,这个肯定是要求的
            retNode = parNode;
        }

        return retNode;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);

        head.right.parent = head;

        head.left.right = new Node(4);
        head.left.parent = head;

        head.left.right.parent = head.left;
        head.left.right.left = new Node(5);
        head.left.right.right = new Node(6);

        head.left.right.left.parent = head.left.right;
        head.left.right.right.parent = head.left.right;

        Node node = getPreNode(head.right);
        if (node != null) {
            System.out.println(node.val);
        } else {
            System.out.println(node);
        }

    }
}
