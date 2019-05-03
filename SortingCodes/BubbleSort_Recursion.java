package fan;

public class BubbleSort_Recursion {
    //获取最大值的方法

    /**
     *
     * @param array 要获取最大值的数组
     * @param L 从哪里开始
     * @param R 到哪里结束
     * @return 返回最大值
     */
    public static int getMax(int[] array , int L, int R) {
        //结束条件
        if (L >= R) {
            return array[L];
        }

        //找到中间位置,划分为左右两边
        int mid = (L + R) / 2;

        //左边最大值
        int maxLeft = getMax(array, 0, mid);
        //右边最大值
        int maxRight = getMax(array, mid + 1, R);

        //返回左右两边最大值
        return Math.max(maxLeft, maxRight);
    }

    public static void main(String[] args) {
        int[] array = new int[]{2,3,1,5};
        int max = getMax(array, 0, array.length - 1);
        System.out.println(max);
    }
}
