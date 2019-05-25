package basic.day03;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 实现一个特殊的栈结构
 */
public class SpecialStack {
    //声明两个栈,一个存放数据,一个存放最小值
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    //通过构造函数初始化
    public SpecialStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * 添加元素
     * @param item
     */
    public void push(Integer item) {
        //数据栈直接放
        dataStack.push(item);

        //如果最小值栈为空的话,直接放进去
        if (minStack.isEmpty()) {
            minStack.push(item);
        }

        //获取当前最小值栈栈顶元素
        int min = minStack.peek();

        //当最小值栈非空
        //当前元素比最小值栈栈顶元素大,再放一个栈顶元素
        if (item > min) {
            minStack.push(min);
        }else {
            //当前元素比最小值栈栈顶元素小,放当前元素
            minStack.push(item);
        }

    }

    /**
     * 删除元素,注意两个栈同时删除
     */
    public Integer pop() {
        //判断数据栈是否有效
        if (dataStack.isEmpty()) {
            throw new RuntimeException("栈是空的");
        }
        minStack.pop();
        return dataStack.pop();
    }

    /**
     * 返回栈顶元素,返回数据栈的就好了
     */
    public Integer peek() {
        //判断数据栈是否有效
        if (dataStack.isEmpty()) {
            throw new RuntimeException("栈是空的");
        }
        return dataStack.peek();
    }

    /**
     * 返回最小值,返回最小值栈栈顶元素就好了
     * @return
     */
    public Integer getMin() {
        //判断栈是否为空
        if (minStack.isEmpty()) {
            throw new EmptyStackException();
        }
        return minStack.peek();
    }

    @Override
    public String toString() {
        return dataStack.toString();
    }
}

class Test{
    public static void main(String[] args) {
        SpecialStack ss = new SpecialStack();
        ss.push(1);
        ss.push(2);
        ss.push(3);
        System.out.println(ss);
        System.out.println(ss.peek());
        System.out.println(ss.peek());
        System.out.println(ss.pop());
        System.out.println(ss.pop());
        System.out.println(ss.getMin());

        Queue<Integer> queue = new LinkedList<>();
    }
}