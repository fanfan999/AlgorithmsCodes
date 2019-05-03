package fan;

import java.util.Arrays;

/**
 * 冒泡排序的递归算法
 */

//这里递归应该是对圈数递归和单个数组递归
public class BubbleSort_Recursion {
    public static void bubbleSort(int[] array, int start, int end) {
        //判断数组是否有效
       if (array == null || array.length <= 1) {
           return;
       }

        //每一圈数组递归
       if (start < end - 1) {
           if (array[start] > array[start + 1]) {
               swap(start, start + 1, array);
           }
           bubbleSort(array, start + 1, end);
       } else {
           return;
       }

       //圈数递归
        bubbleSort(array, start, end - 1);
    }

    //交换位置
    private static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{};
        System.out.println("排序前 : " + Arrays.toString(array));
        System.out.println(array.length);

        bubbleSort(array,0, array.length);

        System.out.println("排序后 : " + Arrays.toString(array));
    }
}
