package fan;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    public static void mergetSort(int[] array){
        //数组是否合理,空数组或者单个元素数组不需要排序,直接退出
        if (array == null || array.length < 2) {
            return;
        }

        //递归方法,L和R分别为数组左右两边的下标
        sortPosses(array,0,array.length - 1);

    }

    //递归排序过程
    private static void sortPosses(int[] array, int L, int R) {
        //递归结束条件,当只有一个元素时,就不用排序了,递归停止
        if (L == R) {
            return;
        }

        //求中间位置
        int mid = (L + R) / 2;

        //将数组从中间位置分为两个部分,分别排序
        sortPosses(array, L, mid);
        sortPosses(array, mid + 1, R);

        //到这一步时,两边排序已经排好了,将两个部分合并到辅助数组中即可
        mergerProcess(array, L, R, mid);
    }

    //合并两个有序的数组
    private static void mergerProcess(int[] array, int L, int R, int mid) {
        //生成辅助数组,长度与原数组长度相同
        int[] assistArray = new int[R - L + 1];

        //p1,p2分别为左右数组最开始的位置
        int p1 = L;
        int p2 = mid + 1;
        int i = 0;
        //当左右两边数组有任意一个到末尾时就停止赋值
        while ((p1 <= mid) && (p2 <= R)) {
            //利用三目运算符给辅助数组赋值,小的放数组
            assistArray[i++] = array[p1] < array[p2] ? array[p1++] : array[p2++];
        }

        //走到这步时,说明两边数组肯定有一个已经到末尾了
        //如果左边到末尾了,就把右边数组剩余的数全部放到辅助数组中
        while (p2 <= R) {
            assistArray[i++] = array[p2++];
        }

        //如果右边到末尾了,就把左边数组剩余的数全部放到辅助数组中
        while (p1 <= mid) {
            assistArray[i++] = array[p1++];
        }

        //把数组放回原数组
        for (int j = 0; j < assistArray.length; j++) {
            //注意这里是L+j开始,不然的话右边的排序好的也直接放在左边的位置了
            array[L + j] = assistArray[j];
        }
        System.out.println(Arrays.toString(array));
    }

    //for test
    public static void main(String[] args) {
        int[] array = new int[]{4,3,1,9,1,2};

        System.out.println("排序前 : " + Arrays.toString(array));

        mergetSort(array);

        System.out.println("排序后 : " + Arrays.toString(array));

    }
}
