package fan;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 求解逆序对问题,和小和问题一样,都是归并思想.
 */
public class ReversedPairs {

    public static String reversedPairs(int[] array) {
        //判断数组是否有效
        if (array == null || array.length < 2) {
            return null;
        }

        return mergerSort(array, 0, array.length - 1);
    }

    /**
     * 迭代,逐渐拆分的过程
     * @param array 求解的数组
     * @param l 左边界
     * @param r 右边界
     * @return 把左右的逆序对都放在一起
     */
    private static String mergerSort(int[] array, int l, int r) {
        //找到中间的下标
        int mid = l + ((r - l) / 2);
        //结束条件
        if (l == r) {
            return null;
        }
        //定义结果的list
        ArrayList<String> lists = new ArrayList<>();
        lists.add(mergerSort(array, l, mid));
        lists.add(mergerSort(array, mid + 1, r));
        lists.add(mergeProcess(array, l, mid, r));
        //迭代
        return lists.toString();
    }

    private static String mergeProcess(int[] array, int l, int mid, int r) {
        //辅助变量
        int[] helpArray = new int[r - l + 1];
        int count = 0;
        int p1 = l;
        int m = mid + 1;
        int p2 = r;
        ArrayList<String> list = new ArrayList<>();

        while ((p1 <= mid) && (m <= p2)) {
            if (array[p1] > array[m]) {
                list.add(array[p1] + ":" + array[m]);
                helpArray[count++] = array[m++];
            } else {
                helpArray[count++] = array[p1++];
            }
        }

        while (p1 <= mid) {
            helpArray[count++] = array[p1++];
        }

        while (m <= p2) {
            helpArray[count++] = array[m++];
        }

        for (int i = 0; i < helpArray.length; i++) {
            array[l + i] = helpArray[i];
        }
        return list.toString();
    }

    public static void main(String[] args) {
        int[] array = new int[]{5,4,3,2,1};

        System.out.println("排序前 : " + Arrays.toString(array));
        String str = reversedPairs(array);
        System.out.println(str.length());
        System.out.println(str);
        System.out.println("排序后 : " + Arrays.toString(array));
    }
}
