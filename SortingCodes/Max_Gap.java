package fan;

/**
 * 求数组的最大差值
 * 利用桶排序的原理
 */
public class Max_Gap {

    public static int maxGap(int[] array) {

        //判断数组的有效性
        if (array == null) {
            return 0;
        }

        //定义一个变量len作为桶的大小,即数组长度＋1
        int len = array.length + 1;
        //定义两个变量,作为最大值和最小值
        int iniMin = Integer.MAX_VALUE;
        int iniMax = Integer.MIN_VALUE;

        //先遍历一遍,找出最大值和最小值
        for (int i = 0; i < array.length; i++) {
            iniMin = iniMin > array[i] ? array[i] : iniMin;
            iniMax = iniMax > array[i] ? iniMax : array[i];
        }
        System.out.println("最大值: " + iniMax + ", 最小值: " + iniMin);

        //若最大值和最小值为同一个数,直接返回0
        if (iniMax == iniMin) {
            return 0;
        }
        //找出最大值和最小值以后,就开始分桶,一共三个桶,也就是三个数组,一个判断是否有元素进去,一个记录最小值,一个记录最大值
        //声明的数组都有初始值,因为声明了长度,长度均为数组长度+1
        boolean[] hasNum = new boolean[len];
        int[] min = new int[len];
        int[] max = new int[len];
        int bid = 0;

        //再次遍历数组,将各个范围的数放在特定的桶内
        for (int i = 0; i < array.length; i++) {
            bid = getBucketId(iniMax, iniMin, len - 1, array[i]);
            min[bid] = hasNum[bid] ? Math.min(min[bid], array[i]) : array[i];
            max[bid] = hasNum[bid] ? Math.max(max[bid], array[i]) : array[i];
            hasNum[bid] = true;
        }

        //最后,所有非空桶的下一个桶的最小值减去上一个桶的最大值,所有非空桶的差值相比较
        int res = 0;
        int lastMax = max[0];
        for (int i = 1; i < len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, min[i] - lastMax);
                lastMax = max[i];
            }
        }

        return res;
    }

    //获取每个数所在桶的编号
    private static int getBucketId(int iniMax, int iniMin, int len, int value) {
        //每个桶的范围为(iniMax - iniMin) / len,所以当前数的所在的桶就是当前数减去最小的数然后再除以每个桶的范围
        //因为桶都是从0开始的,所以不怕会出现0.
        return (int)((value - iniMin) * len / (iniMax - iniMin));
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,20};
        System.out.println("最大差值为: " + maxGap(array));
    }
}
