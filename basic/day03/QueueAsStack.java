package basic.day03;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 *  注意: push是压栈,pop是弹栈,peek是返回栈顶元素
 */
public class QueueAsStack {
    Queue<Integer> dataQueue;
    Queue<Integer> helpQueue;

    //初始化队列
    public QueueAsStack() {
        dataQueue = new LinkedList<Integer>();
        helpQueue = new LinkedList<Integer>();
    }

    /**
     * 压栈操作的时候,都放在数据队列中
     * @param item
     */
    public void push(Integer item) {
        dataQueue.offer(item);
    }

    /**
     * 出栈的时候,先将数据倒入辅助队列中,弹出数据队列中的最后一个元素
     */
    public Integer pop() {
        //判断数据队列是否有效
        if (dataQueue.isEmpty()) {
            throw new EmptyStackException();
        }
        //先将数据倒入辅助队列中,只留最后一个元素
        while (dataQueue.size() > 1) {
            helpQueue.offer(dataQueue.poll());
        }
        //取出数据队列中最后一个元素
        int ele = dataQueue.poll();
        //然后交换两者引用,将辅助队列作为数据队列,免得倒回来
        swap();
        System.out.println("--" + dataQueue);
        System.out.println("==" + helpQueue);
        //返回队列中最后一个元素
        return ele;
    }

    /**
     * 返回栈顶元素的操作与出栈类似
     */
    public Integer peek() {
        //判断数据队列是否有效
        if (dataQueue.isEmpty()) {
            throw new EmptyStackException();
        }

        //倒
        while (dataQueue.size() > 1) {
            helpQueue.offer(dataQueue.poll());
        }

        //取出队列中剩余的元素
        int lastEle = dataQueue.poll();

        //将取出的元素也放到辅助队列中去
        helpQueue.offer(lastEle);
        //交换两个引用
        swap();

        //返回最后一个元素
        return lastEle;
    }

    @Override
    public String toString() {
        return dataQueue.toString();
    }

    /**
     * 注意注意,这里的swap不能带参数,带参数了,你就要加this,
     * 不然就会报错,因为带参数了改变的就不是成员变量的两个队列了
     */
    private void swap() {
        Queue<Integer> temp = dataQueue;
        dataQueue = helpQueue;
        helpQueue = temp;
    }

}

class QASTest{
    public static void main(String[] args) {
        QueueAsStack stack = new QueueAsStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);

        System.out.println(stack.peek());
        System.out.println(stack.peek());

        System.out.println(stack.pop());
        System.out.println(stack.pop());



    }
}
