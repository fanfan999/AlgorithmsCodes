package fan;

import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序
 */
public class InsertionSort {
    public static void main(String[] args) {

        Random r = new Random(10);
        int i = r.nextInt(100);
        System.out.println(i);
        int[] array = new int[]{3, 1, 5, 2, 7, 6, 1};

        // 排序前
        System.out.println(Arrays.toString(array));

        insertionSort(array);

        // 排序后
        System.out.println(Arrays.toString(array));
    }

    //插入排序
    private static void insertionSort(int[] array) {

        //判断数组对象是否为空和是否为单个元素数组
        if (array == null || array.length < 2) {
            return;
        }

        //控制开始和结束,到数组末尾就停,i表示右边第一个未排序的元素下标
        for (int i = 1; i < array.length; i++) {
            // 让左边已经排好序的和右边未排序的依次比较,j表示左边已排序的最后一个元素下标
            for (int j = i - 1; j >= 0; j--) {
                //大就交换
                if (array[j] > array[j + 1]) {
                    swap(array, j);
                }
            }
        }
    }

    //交换
    private static void swap(int[] array, int j) {
        int temp = array[j];
        array[j] = array[j + 1];
        array[j + 1] = temp;
    }
}
