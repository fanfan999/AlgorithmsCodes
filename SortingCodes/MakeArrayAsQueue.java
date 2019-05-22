package fan;

import java.util.Arrays;

/**
 * 用数组结构实现大小固定的队列(先进先出)
 */
public class MakeArrayAsQueue {
    //声明一个数组作为队列
    private int[] array;
    //表示取出位置的变量
    private int start;
    //表示插入位置的变量
    private int end;
    //表示队列容量的变量
    private int size;

    //在构造函数中初始化
    public MakeArrayAsQueue(int iniSize) {
        //当数组长度<=0时,直接报错
        if (iniSize <= 0) {
            throw new IllegalArgumentException("队列长度应该大于0");
        }

        //数组长度有效就直接生成数组
        array = new int[iniSize];
        //给变量赋初值
        size = 0;
        start = 0;
        end = 0;
    }

    //添加元素
    public void push(int obj) {
        //当数组满了的时候,报错
        if (size == array.length) {
            throw new ArrayIndexOutOfBoundsException("队列满了");
        }

        //数组没满的时候
        size++;
        array[end] = obj;
        //当数组到最后一个元素下标了还没满,就说明前面元素被取出去了,所以end回到0位置.
        //没有到最后一个元素就往后移
        end = end == array.length - 1 ? 0 : end+1;
        //System.out.println("比较后end: " + end);
    }

    //取出队列第一个元素,不删除该元素
    public int peek() {
        //判断队列是否有元素,没有元素就返回默认值0
        if (size == 0) {
            return 0;
        }
        //有元素就返回队列第一个位置元素,即start位置指向的元素
        return array[start];
    }

    //取出队列中的元素,删除该元素
    public int poll() {
        //当数组长度为0时,就报错
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("队列中没有元素,无法弹出元素");
        }

        //数组中有元素就取出来
        size--;
        int temp = start;
        System.out.println("比较前: " + start);
        start = start==array.length ? 0 : start+1;
        System.out.println("比较后: " + start);
        return array[temp];
    }
}
