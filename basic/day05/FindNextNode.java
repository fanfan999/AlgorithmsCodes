package basic.day05;

/**
 * 给定一个节点,找到其后继结点(中序遍历后该节点的下一个节点)
 *  写的时候画个例子在旁边嘛,这样就直观很多
 *  本代码以中序遍历为2->7->5->4->6->1->3为例,图就是博客里的图
 */
public class FindNextNode {

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
     * @param tarNode 这个节点就是要找其后继结点的节点
     * @return
     */
    public static Node getNextNode(Node tarNode) {
        //判断这个节点的合法性
        if (tarNode == null) {
            return tarNode;
        }

        //定义一个存放返回值的变量
        Node retNode = null;

        //目标节点不为空,就往下走
        //当该节点有右子树的时候,找到其右子树的最左边的节点
        if (tarNode.right != null) {
            tarNode = tarNode.right;

            while (tarNode.left != null) {
                tarNode = tarNode.left;
            }
            //走到这里说明tarNode.left已经为空了,走到了右子树的最左边节点位置,这就是tarNode的后继结点
            retNode = tarNode;
        }else {
            //定义一个父节点辅助变量,用于存放当前节点的父节点
            Node parent = tarNode.parent;
            //定义一个子节点辅助变量,用于存放当前节点
            Node curNode = tarNode;
            /**
             * 当该节点没有右子树的时候,就要一直往上找,直到某节点是其父节点的左子树为止
             * 不过这里的while循环有两个需要注意的点
             *  1. 最右边的叶子节点,在本例中也就是3,它是没有后继节点的,它的循环结束条件应该是其父节点为null;
             *  2. 不是最右边的叶子节点肯定是能找到其后继结点的,所以找到了就停止;
             */
            while (parent != null && parent.left != curNode) {
                //没有找到就一直向上走
                curNode = parent;
                parent = parent.parent;
            }

            //走到这里说明要么没有,要么就是找到了,反正parent肯定是想要的值
            retNode = parent;
        }


        return retNode;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        //这里行代码不写也可以,因为parent是成员变量有默认值且默认值就是null;
        //head.parent = null;

        head.right.parent = head;

        head.left.right = new Node(4);
        head.left.parent = head;

        head.left.right.parent = head.left;
        head.left.right.left = new Node(5);
        head.left.right.right = new Node(6);

        head.left.right.left.parent = head.left.right;
        head.left.right.right.parent = head.left.right;

        Node node = getNextNode(head.left.right.left);
        if (node != null) {
            System.out.println(node.val);
        }else {
            System.out.println(node);
        }

    }
}
