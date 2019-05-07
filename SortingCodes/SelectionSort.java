package fan;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] array = new int[]{7, 6, 8, 9, 1, 2, 7, 6, 4};

        //排序前
        System.out.println(Arrays.toString(array));

        selectionSort(array);

        //排序后
        System.out.println(Arrays.toString(array));
    }

    //选择排序代码
    private static void selectionSort(int[] array) {

        //首先判断数组对象是否为空以及是否为单个元素数组
        if (array == null || array.length < 2){
            return;
        }
        //控制循环次数
        for (int start = 0; start < array.length; start++) {
            //注意这里的i只能取到倒数第二个数,防止i+1越界
            for (int i = start; i < array.length - 1; i++) {
                //如果前一个比后面的大,就交换
                if (array[start] > array[i + 1]) {
                    swap(start,i + 1, array);
                }
            }
        }
    }

    private static void swap(int start, int i, int[] array) {
        int temp = array[i];
        array[i] = array[start];
        array[start] = temp;
    }
}
