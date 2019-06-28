package basic.day05;

/**
 * 二叉树遍历递归版本
 * 把每个三角形都看成一个二叉树嘛,分别都有头节点和左右子节点
 */
public class PreInPosTraversal1 {
   public static class Node {
       private Node left;
       private Node right;
       private int value;

       public Node(int val) {
           this.value = val;
       }
   }

       public static void preTraversal(Node root) {
           if (root == null) {
               return;
           }
           System.out.print(root.value + " ");
           preTraversal(root.left);
           preTraversal(root.right);
       }

       public static void inTraversal(Node root) {
           if (root == null) {
               return;
           }

           inTraversal(root.left);
           System.out.print(root.value + " ");
           inTraversal(root.right);
       }

       public static void postTraversal(Node root) {
           if (root == null) {
               return;
           }

           postTraversal(root.left);
           postTraversal(root.right);
           System.out.print(root.value + " ");
       }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        preTraversal(root);
        System.out.println();
        System.out.println("--------------");
        inTraversal(root);
        System.out.println();
        System.out.println("--------------");
        postTraversal(root);
        System.out.println();
        System.out.println("--------------");
    }
}
