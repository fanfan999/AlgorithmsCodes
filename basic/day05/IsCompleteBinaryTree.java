package basic.day05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一棵树是否是完全二叉树
 */
public class IsCompleteBinaryTree {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    /**
     * 注意这里是按层遍历,所以不能是递归方法
     *      1. 先实现按层遍历
     *      2. 再来判断是否是完全二叉树
     * @param head
     * @return
     */
    public static boolean isCBT(Node head) {
        if (head == null) {
            return false;
        }

        boolean flag = false;
        Node temp = head;
        Queue<Node> queue = new LinkedList<>();
        //将头结点加入到队列中,进入循环
        queue.offer(temp);

        while (!queue.isEmpty()) {
            temp = queue.poll();

            /**
             * 判断是否满足不是完全二叉树的情况
             *  1. 布尔值为false,说明在情况1,那有右没左就返回false
             *  2. 布尔值为true,说明过了情况2,那后面的节点不是叶子节点,有子节点就直接返回false
             *  3. 二者满足其一就返回false
             */
            //这是情况1
            if ((temp.left==null && temp.right !=null)) {
                return false;
            }
            //这是跑完了情况2,后面的节点有不是叶子节点的就返回false
            if (flag && (temp.left != null || temp.right != null)) {
                return false;
            }

            //走到这里,说明是遍历完的部分是完全二叉树,继续往下遍历就成
            if (temp.left != null) {
                queue.offer(temp.left);
            }

            if (temp.right != null) {
                queue.offer(temp.right);
            }else {
                /**
                 * 因为最上面判断是否是情况1的时候就已经排除掉了有右没左的情况
                 * 所以这里只需要考虑有左没右或者左右都没得情况,即左边不用管,反正右边不能有
                 * 满足有左没右或者左右都没有那就进去第二阶段
                 *
                 */
                flag = true;
            }
        }

        return flag;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);

        boolean b = isCBT(head);
        System.out.println(b);
    }
}
