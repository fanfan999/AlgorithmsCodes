package fan;

import java.util.Arrays;

/**
 * 小和问题
 */
public class SmallSum {

    /**
     * 设计一个求小和的方法
     * @param array 要求小和的数组
     * @return 因为要求和,所以返回值为int
     */
    public static int smallSum(int[] array) {
        //判断数组是否有效,这里有几个点要注意:
        //1.任何对象,操作之前最好都有一个判断是否为空的语句,可以防止出现空指针
        //2.array.length就是数组的长度,是从1开始计数的,和下标从0开始计数区分开!!!!!
        if (array == null || array.length < 2) {
            return 0;
        }

        return mergeSort(array, 0, array.length - 1);
    }

    //递归分治的过程
    private static int mergeSort(int[] array, int l, int r) {
        //数组有效,按照归并排序来写代码

        //递归结束条件
        if (l == r) {
            return 0;
        }
        int sum = 0;
        //计算中间值,这样写防止越界,写成(l + r) /2也没大问题
        int mid = l + ((r - l) / 2);

        //我们把左边归并产生的小和加上右边归并产生的小和再加上整体归并产生的小和加起来就是总的小和
        return mergeSort(array, l, mid) + mergeSort(array, mid + 1, r) + mergeProcess(array, l, mid, r);
        /*sum += mergeSort(array, l, mid);
        sum += mergeSort(array, mid + 1, r);
        sum += mergeProcess(array, l, mid, r);
        return sum;*/
    }

    //归并的过程,递归不大懂可以看我画的图哈哈哈
    private static int mergeProcess(int[] array, int l, int mid, int r) {
       //定义辅助数组,这里不能写成array.length,因为前面几次递归进来的只有几个元素,会空间浪费.
       int[] helpArray = new int[r - l + 1];
       //定义几个辅助变量
       int p1 = l;
       int m = mid + 1;
       int p2 = r;
       int count = 0;
       //定义和
       int sum = 0;

       //归并同时求和,当左右两边都没有走到末尾时
        while ((p1 <= m) && (p2 <= r)) {
            //左边小于右边时,求和.
            //注意这里是r - p2 + 1,因为有可能右边只有一个元素,此时r - p2 = 0,所以要＋1
            sum += array[p1] < array[p2] ? (array[p1] * (r - p2 + 1)) : 0;
            helpArray[count++] = array[p1] < array[p2] ? array[p1++] : array[p2++];
        }

        //当两边有一边走到末尾后,就跳出循环
        //当左边到末尾了,右边没有到,将右边的全部放到辅助数组中,注意这里不用计算sum了,因为在上面while中都已经算了
        while (p2 <= r) {
            helpArray[count++] = array[p2++];
        }
        //当右边到末尾了,左边没有到,将左边的全部放到辅助数组中
        while (p1 <= mid) {
            helpArray[count++] = array[p1++];
        }

        //将辅助数组放回原数组
        for (int i = 0; i < helpArray.length; i++) {
            //因为每次递归进来的数都是不一样的,所以是每次进来的左边界开始放.
            array[l + i] = array[i];
        }
       return sum;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,2,3};
        int sum = 0;

        System.out.println("排序前 :" + Arrays.toString(array));

        sum = smallSum(array);

        System.out.println("smallSum :" + sum);
        System.out.println("排序后 :" + Arrays.toString(array));
    }
}
