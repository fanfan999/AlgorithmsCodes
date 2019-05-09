package fan;

import java.util.Arrays;

/**
 * 随机快排
 * 先写快排,再加一行代码就可以了
 * 写这种递归代码的时候最好就是举个例子,一般在例子上走,一边写,
 * 然后举例的时候可以是比较特殊的例子,比如刚好左右两边差不多这样子
 * 而且只需要看第一回第二回怎么走就可以了
 */
public class RandomQuickSort {
    /**
     * 这是主函数调用的快排开始的起点
     * @param array 目标数组
     */
    public static void randomQuickSort(int[] array) {
        //判断数组的有效性,如果为空数组或单元素数组就直接返回
        if (array == null || array.length < 2) {
            return;
        }

        //数组有效就进行排序.
        iterationProcess(array, 0, array.length - 1);
    }

    /**
     * 在该方法中进行迭代,分割,
     * 注意是先排序,再迭代
     * @param array
     * @param l 起始的时候数数组的左边界,也就是0位置
     * @param r 起始的时候数数组的右边界,也就是最后一个元素位置,array.length - 1
     */
    private static void iterationProcess(int[] array, int l, int r) {
        //判断能否开始迭代,当左边界等于右边界说明只有一个元素,就没有必要迭代了
        if (l < r) {
            //注意,这就是随机快排与荷兰国旗引出的快排多的唯一一行代码:随机选择一个数与最后一个数交换
            //注意随机数的范围,我刚开始在这里就错了,想了好一会.
            int random = (int) (Math.random()*(r - l + 1)) + l;
            swap(array, random, r);

            //先排序一次,找到左边和右边,再迭代,返回左右边界的下标
            //temp数组长度永远为2,第一个元素为较小值的最右边,第二个元素为较大值的最左边
            int[] temp = sortProcess(array, l, r);

            //划分的时候,注意左右边界刚开始都是越界的,表示不存在,所以换位的时候也是左边界的下一个换,右边界的上一个换这样子
            //左右两边继续迭代
            iterationProcess(array, l, temp[0]);
            iterationProcess(array, temp[1] + 1, r);
        }
    }

    /**
     * 排序的过程
     * @param array
     * @param l
     * @param r
     */
    private static int[] sortProcess(int[] array, int l, int r) {
        //定义左边界,刚开始是越界的
        int less = l - 1;
        //定义右边界,刚开始是包含最后一个元素的
        int more = r;

        while (l < more) {
            //如果比参照数小,less边界的下一位与当前数交换,同时less后移一位,下标后移一位.
            if (array[l] < array[r]) {
                swap(array, ++less, l++);
            } else if (array[l] > array[r]) {
                //如果当前数比参照数大,more边界的上一位与当前数交换,同时more前移一位,但是下标不动!!!
                swap(array, --more, l);
            }else {
                //如果当前数与参照数相等,啥都不干,下标往后走就行了.
                l++;
            }
        }

        //交换右边界的最后一个数和右边界的第一个数
        swap(array, more, r);

        return new int[]{less, more};

    }

    //两数交换
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //测试函数
    public static void main(String[] args) {
        int[] array = new int[]{5,2,1,9,6};

        System.out.println("排序前: " + Arrays.toString(array));

        randomQuickSort(array);

        System.out.println("排序后: " + Arrays.toString(array));
    }
}
