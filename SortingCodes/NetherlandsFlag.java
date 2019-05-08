package fan;

import java.util.Arrays;

/**
 * 荷兰国旗问题
 */
public class NetherlandsFlag {

    /**
     * partition部分,即左右两边比较往中间走的代码
     * @param array 目标数组
     * @param l 数组下标开始位置,0
     * @param r 数组下标结束位置,array.length -1
     * @param target 要进行比较的数
     */
    public static void partition(int[] array, int l, int r, int target) {
        //less表示比target小的部分
        int less = l - 1;
        //more表示比target大的部分
        int more = r + 1;
        //表示当前比较的数
        int cur = 0;

        while (cur < more) {
            //开始比较,如果小于target
            if (array[cur] < target) {
                //System.out.println(less);
                swap(array,++less,cur++);
                //System.out.println(less);
            } else if (array[cur] > target) {
                swap(array,--more,cur);
            } else {
                cur++;
            }
        }
    }

    //交换数字
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[] {0,1,3,2,1,4,5,8,3,10};
        int target = 4;
        System.out.println("比较前: " + Arrays.toString(array));
        partition(array, 0, array.length - 1, target);
        System.out.println("比较后: " + Arrays.toString(array));
    }
}
