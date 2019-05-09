package fan;

import java.util.Arrays;

/**
 * 快速排序 = 荷兰国旗问题＋递归
 * 这个快排需要注意一下是把每一次的最后一个数当作被比较的数
 * 而且较大数的边界也就是左边界开始是把最后一个数包含在里面的,
 * 排序结束后再跟左边界包含的第一个数交换,这样可以保证每一轮排序最后一个数不变
 */
public class QuickSort {
    public static void quickSort(int[] array) {
        //判断数组是否有效
        if (array == null || array.length < 2) {
            return;
        }

        //数组有效时
        partition(array, 0, array.length - 1);
    }

    /**
     * 迭代分割的过程
     * @param array 目标数组
     * @param l 数组左边界
     * @param r 数组右边界
     */
    private static void partition(int[] array, int l, int r) {
        //定义一个数组用于接收中间等于区的左右两边下标
        if (l < r) {
            int[] p = sortProcess(array, l, r);

            partition(array, l, p[0]);
            partition(array, p[1] + 1, r);
        }
    }

    /**
     *
     * @param array
     * @param l
     * @param r 为本次比较的中间值,就是数组都是跟他比较
     * @return
     */
    private static int[] sortProcess(int[] array, int l, int r) {
        //less和more分别为记录较小数和较大数的边界
        int less = l - 1;
        //注意此时较大值的边界就是r,要保证最左边的值不被换出去,不然会 出问题
        int more = r;

        //当记录当前是哪个数在比较的变量到较大数边界时,比较结束
        while (l < more) {
            //如果左边的数比最后一个数小
            if (array[l] < array[r]) {
                swap(array, ++less, l++);
            } else if (array[l] > array[r]){
                swap(array, --more, l);
            } else {
                l++;
            }
        }
        swap(array, more, r);
        return new int[] {less, more};
    }

    //交换数字
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,5,2,56,8,52,1};

        System.out.println("排序前 : " + Arrays.toString(array));

        quickSort(array);

        System.out.println("排序前 : " + Arrays.toString(array));
    }
}
