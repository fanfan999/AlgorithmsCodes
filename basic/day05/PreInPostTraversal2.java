package basic.day05;

import java.util.Stack;

/**
 * 二叉树的遍历非递归版本
 */
public class PreInPostTraversal2 {
    public static class Node {
        private Node left;
        private Node right;
        private int value;

        public Node(int val) {
            this.value = val;
        }
    }

    /**
     * 先序遍历:这个代码是我从别人那里学来的,神逻辑,巨叼
     * 建议你搞一个例子在旁边对着代码走一遍你就知道了
     * 这个在循环中每次都是一个三角形,你把他看成很多个三角形就成了
     *
     * @param root 根节点
     */
    public static void preTraveresal(Node root) {
        //空树就不打印,本来也没什么东西可以打印的
        if (root != null) {

            System.out.println("非递归的先序遍历:");
            //声明一个栈作为辅助空间
            Stack<Node> stack = new Stack<>();
            //先把根节点放到栈中去,因为先序遍历根节点是第一个要被打印的
            stack.push(root);
            //如果栈不为空,就说明遍历还没结束
            while (!stack.isEmpty()) {
                //每一节节点都是他所在的三角形的根节点
                root = stack.pop();
                //先打印头结点
                System.out.print(root.value + " ");
                //打印顺序是中->左->右,但是栈弹出顺序是相反的,所以压栈的时候要中右左
                //当根节点的左右节点为空时,说明到了叶子节点,就相当于只有一个根节点的二叉树,
                // 所以直接打印就是先序遍历
                if (root.right != null) {
                    stack.push(root.right);
                }
                if (root.left != null) {
                    stack.push(root.left);
                }
            }

            System.out.println();
        }
    }

    /**
     * 中序遍历,整个例子跟着代码跑一遍,自然就知道了
     *
     * @param root 其实这个root在遍历过程中可以看成任何一个节点,只是刚开始的时候等于根节点
     */
    public static void inTraversal(Node root) {
        //不是空树才遍历,空树遍历没有意义
        if (root != null) {
            Stack<Node> stack = new Stack<>();
            // 注意这里,这里的循环条件是栈不为空或者当前节点不为空,
            while (!stack.isEmpty() || root != null) {
                //如果左子节点不为空,就一直往左,因为要先遍历左边嘛
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    //左边没有节点了,就先打印它的父节点,然后一直往右
                    root = stack.pop();
                    System.out.print(root.value + " ");
                    root = root.right;

                }
            }
            System.out.println();
        }
    }

    /**
     * 后序遍历,后序遍历需要借助两个栈,其实就是先序遍历的变体
     * 先序遍历是中->左->右,我们先通过一个栈实现中->右->左,然后把打印的语句换成了输出到另一个辅助栈中,遍历结束后再统一打印,就对了
     *
     * @param root
     */
    public static void postTraversal(Node root) {
        System.out.println("后序遍历: ");
        if (root != null) {
            Stack<Node> stack = new Stack<>();
            Stack<Node> stack1 = new Stack<>();
            //仿造先序遍历
            stack.push(root);

            while (!stack.isEmpty()) {
                root = stack.pop();
                //将头结点放到另一个栈中
                stack1.push(root);
                //放左
                if (root.left != null) {
                    stack.push(root.left);
                }
                //放右
                if (root.right != null) {
                    stack.push(root.right);
                }
            }

            //走到这里,说明已经排好序了
            while (!stack1.isEmpty()) {
                System.out.print(stack1.pop().value + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        preTraveresal(root);
        inTraversal(root);
        postTraversal(root);
    }
}
