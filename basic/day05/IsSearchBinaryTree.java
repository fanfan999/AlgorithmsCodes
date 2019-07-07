package basic.day05;

import java.util.*;

/**
 * 判断是否是搜索二叉树
 *  1. 通过中序遍历,当遍历结果是一个递增的.那就是搜索二叉树
 *  2. 用非递归方式,这里有一个点,就是递归就是不断压栈弹栈的过程,所以我们非递归的时候利用的辅助空间就是栈
 *  3. 注意区别ifelse和两个if的使用,ifelse每次只会执行一个,两个if每次两个都会对其进行判断
 *  4. 中序遍历过程中,因为要先压完左边的才压右边的,所以用ifelse,而不是和先序遍历一样用ifif,ifif是两边都压栈.
 *  5. 中序遍历就是全部通过左节点来遍历,因为一颗二叉树是可以被左节点划分称很多部分的.
 *
 */
public class IsSearchBinaryTree {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int val) {
            this.value = val;
        }
    }


    public static boolean isSBT(Node head) {
        if (head == null) {
            return true;
        }

        Stack<Node> stack = new Stack<>();
        int i = Integer.MIN_VALUE;
        Node temp = head;
        boolean flag = true;

        while (!stack.isEmpty() || temp != null) {
            if (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }else {
                //左边走到底了就弹出其父节点,然后去右边
                temp = stack.pop();
                if (temp.value < i) {
                    flag = false;
                    break;
                }else {
                    i = temp.value;
                }
                temp = temp.right;
            }
        }

        return flag;
    }



    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.right.left = new Node(4);
        head.right.right = new Node(5);
        head.left.left = new Node(6);
        head.left.right = new Node(7);

        boolean b = isSBT(head);
        System.out.println(b);

    }
}
