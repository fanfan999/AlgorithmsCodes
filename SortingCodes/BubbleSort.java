package fan;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

    //自己写的冒泡排序算法
    private static void bubbleSort(int[] array) {
        //首先判断array对象是否为空
        //然后判断array是否是单个元素数组
        if (array == null || array.length < 2) {
            //是就直接退出
            return;
        }
        //控制次数(第一个~最后一个--第一个~倒数第二个--以此类推)
        //array.length - 1才是最后一个元素的下标,因为下标是从0开始的
        for (int end = array.length - 1; end > 0; end--) {
            //j只能指向倒数第二个元素,防止越界
            for (int j = 0; j < end; j++) {
                //前一个比后一个元素值大,交换位置
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1, array);
                }
            }
        }
    }

    //交换位置
    private static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //for test
    //一个绝对正确的排序算法
    public static void rightMethod(int[] array) {
        //调用Arrays的sort()方法进行排序,这是绝对正确的
        Arrays.sort(array);
    }

    //for test

    /**
     * @param length   表示数组的长度
     * @param maxValue 表示数组中元素的最大值
     * @return 返回一个长度随机, 元素随机的数组
     */
    public static int[] createRandomArray(int length, int maxValue) {
        //生成[0,length]范围的随机数使得randomArray的长度随机,因为Math.random()是不能到达右边界的,所以需要加1使它取到length这个数
        int[] randomArray = new int[(int) (Math.random() * (length + 1))];

        // 给随机数组赋值
        for (int i = 0; i < randomArray.length; i++) {
            //因为Math.random()方法是不会产生负数的,为了更好的模拟真实情况,我们采用两个随机数相减,就有可能产生负数了
            randomArray[i] = (int) ((Math.random() * (maxValue + 1)) - (Math.random() * (maxValue + 1)));
        }
        return randomArray;
    }

    //for test
    /**
     * 用于对比两个算法跑出来结果是否相同
     *
     * @param array1 表示自己写的算法跑出来的结果
     * @param array2 表示对数器中的算法跑出来的结果
     * @return 返回true表示两者完全相同, false就打印错误样本
     */
    public static boolean comparator(int[] array1, int[] array2) {
        int len = array1.length;

        //如果两个数组长度不相同一定不相同
        if (array1.length != array2.length) {
            return false;
        }

        //一个为空一个不为空,一定不相同
        if ((array1 == null && array2 != null) || (array2 == null && array1 != null)) {
            return false;
        }

        //遍历数组,进行比较
        for (int i = 0; i < len; i++) {
            if (array1[i] != array2[i]) {
                System.out.println("出错了!,此时自定义算法的数据为 : " + Arrays.toString(array1) +
                        ", 对数器样本数据为 : " + Arrays.toString(array2));
                System.out.println(array1[i]);
                System.out.println(array2[i]);

                return false;
            }
        }

        return true;
    }

    // for test
    /**
     *
     * @param array 表示要复制的样本
     */
    private static int[] copyArray(int[] array) {
        int[] copyArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            copyArray[i] = array[i];
        }
        return copyArray;
    }

    // for test
    public static void main(String[] args) {
        int[] a = new int[0];
        int[] b = null;
        System.out.println(a.length);
        System.out.println(b.length);

        boolean flag = true;

        //产生样本
        for (int i = 0; i < 10000; i++) {
            int[] array = createRandomArray(10, 1000);

            //复制样本
            int[] array2 = copyArray(array);

            //通过两种算法排序
            bubbleSort(array);
            rightMethod(array2);

            //判断两个数组此时是否相同
            flag = comparator(array,array2);
            //一旦有不相同,就退出循环
            if (flag != true) {
                break;
            }
        }

        System.out.println(flag == true ? "恭喜你,你的算法是正确的!" : "继续努力!");
    }

}
