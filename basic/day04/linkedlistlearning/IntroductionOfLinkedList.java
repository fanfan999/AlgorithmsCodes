package basic.day04.linkedlistlearning;

/**
 * 链表入门学习
 */
public class IntroductionOfLinkedList<E> {

    private class Node{
        //存放元素数据
        public E e;
        //存放下一个节点的引用
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    //头结点
    private Node head;
    //链表长度
    private int size;

    public IntroductionOfLinkedList(){
        head = null;
        size = 0;
    }

    //获取链表中元素个数
    public int getSize(){
        return size;
    }

    //判断链表是否有元素
    public boolean isEmpty(){
        return size==0;
    }

    //链表头添加元素
    public void addFirst(E e) {
        //为参数新建一个节点
        Node node = new Node(e);
        //将节点的指向原来的头结点
        node.next = head;
        //将该节点作为头结点
        head = node;
        //上述代码可以简化为下面这样
        //head = new Node(e, head);
        size++;
    }

    //在链表的index(0开始)位置添加元素,但是这个不常用.知道就行
    public void add(int index, E e) {
        //判断index的合法性
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("参数不合法");
        }
        //判断是否为头节点,头节点没有前一个节点,所以直接添加
        if (index == 0) {
            addFirst(e);
        }else{
            Node pre = head;
            //找到index前一个点的位置
            for (int i = 0; i < index - 1; i++) {
                pre = pre.next;
            }
            //声明要添加的node
            Node node = new Node(e);
            //将node.next连接上pre.next(index位置的节点)
            node.next = pre.next;
            //将node作为pre的下一个节点
            pre.next = node;
            //简写为: pre.next = new Node(e, pre.next);
            size++;
        }
    }

    //向末尾添加元素
    public void addLast(E e) {
        /*Node pre = head;
        while (pre.next != null) {
            pre = pre.next;
        }

        pre.next = new Node(e, pre.next);
        size++;*/
        //简写为
        add(size, e);
    }
}
