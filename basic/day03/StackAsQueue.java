package basic.day03;

import java.util.Stack;

/**
 * 用栈实现队列
 *  注意: linkedlist类实现了queue接口(和list,zet一样都是继承子collection接口),所以可以把linkedlist类当作队列使用
 *  boolean offer是进队列,poll是出队列(这两个方法可以通过返回值判断操作是否成功),peek是返回队列中的首元素,
 *  尽量避免使用add和remove方法(这两个方法在操作失败时会抛出异常).
 */
public class StackAsQueue {
    //声明两个栈,数据栈专门存放数据
   private Stack<Integer> dataStack;
    //辅助栈专门取出数据
   private Stack<Integer> helpStack;

    //对栈进行初始化
    public StackAsQueue() {
        dataStack = new Stack<>();
        helpStack = new Stack<>();
    }

    //offer方法进队列
    public void offer(Integer item) {
        dataStack.push(item);
    }

    //poll方法出队列
    public Integer poll() {
        //由于队列是先进先出而栈是先进后出的,所以要借助辅助栈
        invertStack();
        //然后弹出辅助栈中的栈顶元素就可以了
        return helpStack.pop();
    }

    //peek方法取出队列首元素
    public Integer peek() {
        //同时记得倒元素,因为不满足条件会自动退出,所以这个哪里倒都一样的.
        invertStack();

        //返回栈顶元素即可
        return helpStack.peek();
    }

    //将数据倒入辅助栈
    public void invertStack() {
        //倒之前辅助栈要没有元素,而且要一次性全倒完
        if (helpStack.isEmpty()) {
            //当数据栈中没有元素时,停止倒元素
            while (!dataStack.isEmpty()) {
                helpStack.push(dataStack.pop());
            }
        }
    }

    //重写tostring方法

    @Override
    public String toString() {
        return dataStack.toString();
    }
}

class Test2 {
    public static void main(String[] args) {
        StackAsQueue queue = new StackAsQueue();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}