package fan;

import java.util.Arrays;

/**
 * 堆排序
 * 思路:
 *  1.先整体建立成一个大根堆
 *  2.然后最后一个数和第一个最大的数交换
 *  3.然后出了最后一个数以外的数都重新进行大根堆排序知道数组中元素为1
 */
public class HeapSort_1 {

    //调用该方法,表示开始了堆排序
    public static void heapSort(int[] array) {
        //判断数组的有效性
        if (array.length < 2 || array == null) {
            return;
        }

        //数组有效,然后先整体建立大根堆,先把第一个元素看成一个大根堆,然后前两个...依次进行
        for (int i = 0; i < array.length; i++) {
            heapProcess(array, i);
        }
        //整体建立堆以后,就可以进行排序了
        //定义一个变量控制堆的长度
        int heapSize = array.length - 1;
        //循环交换位置,然后建堆的过程
        //结束条件: 当堆只有一个元素的时候,停止
        while (heapSize >= 1) {
            //System.out.println("+++" + Arrays.toString(array));
            //1.先交换第一个和最后一个元素值,因为heapSize刚开始等于数组长度,所以要-1作为最后元素的下标
            swap(array,0, heapSize);
            //System.out.println("---" + Arrays.toString(array));
            //2.将最后一个元素以前的数重新建立大根堆
            heapSize--;
            //System.out.println(heapSize);
            sortProcess(array, 0, heapSize);
            //System.out.println("排序中: " + Arrays.toString(array));

        }

    }

    /**
     * 建立大根堆过程
     * @param array
     * @param index 表示大根堆的长度刚开始为0,然后是1...,也是子节点在数组中的下标
     */
    private static void heapProcess(int[] array, int index) {
        //用子节点去找父节点比较,比父节点大就交换,小就不管,由于这个可以取到0,所以这个可以作为结束条件
        while (array[index] > array[(index - 1) / 2]) {
            //子节点比父节点大,向上交换位置
            swap(array, index , (index - 1) / 2);
            //将父节点的下标赋给当前下标,也就是下标变成父节点的,然后继续向上比较
            index = (index - 1) / 2;
        }
    }

    /**
     * 建堆以后排序的过程,这里有几个要注意的点
     * @param array
     * @param i 表示从哪里开始建堆,都是从0下标开始的
     * @param heapSize 表示堆的长度,刚开始就是数组长度-1,因为最后一个数已经交换了,就不动了
     */
    private static void sortProcess(int[] array, int i, int heapSize) {
        //递归调用上面的建堆方法
        while (i <= heapSize) {
            heapProcess(array, i);
            i++;
        }
    }

    //交换值
    private static void swap(int[] array, int index, int father) {
        int temp = array[index];
        array[index] = array[father];
        array[father] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3,1,9,2,5,0,4,8,25,39};

        System.out.println("排序前: " + Arrays.toString(array));

        heapSort(array);

        System.out.println("排序后: " + Arrays.toString(array));
    }
}
