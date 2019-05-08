package fan;

import java.util.Arrays;

public class ResversedPair {
    public static int resversedPair(int [] array) {
        //判断数组的有效性
        if(array == null || array.length < 2) {
            return 0;
        }

        //调用归并方法
        return mergeSort(array, 0, array.length - 1);
    }

    //递归分治的过程
    private static int mergeSort(int[] array, int l, int r) {
        //数组有效,按照归并排序来写代码

        //递归结束条件
        if (l == r) {
            return 0;
        }

        //计算中间值,l + ((r - l) / 2)这样写防止越界,写成(l + r) /2也没大问题
        int mid = (l + r) /2;

        //我们把左边归并产生的小和加上右边归并产生的小和再加上整体归并产生的小和加起来就是总的小和
        return mergeSort(array, l, mid) + mergeSort(array, mid + 1, r) + mergeProcess(array, l, mid, r);
    }

    private static int mergeProcess(int[] array, int l, int mid, int r) {
        //辅助变量
        int count = 0;
        int p1 = l;
        int m = mid + 1;
        int p2 = r;
        int[] helpArray = new int[r - l + 1];
        int num = 0;

        while ((p1 <= mid) && (m <= p2)) {
            if (array[p1] > array[m]) {
                count += (mid - p1 + 1);
                helpArray[num++] = array[m++];
            } else {
                helpArray[num++] = array[p1++];
            }
        }

        while ((p1 <= mid)) {
            helpArray[num++] = array[p1++];
        }

        while (m <= p2) {
            helpArray[num++] = array[m++];
        }

        for (int i = 0; i < helpArray.length; i++) {
            array[l + i ] = helpArray[i];
        }

        return count;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7,0};

        System.out.println("排序前: " + Arrays.toString(array));

        int count = resversedPair(array);
        
        System.out.println("逆序对的数量为 : " + count);
        System.out.println("排序后: " + Arrays.toString(array));
    }
}
