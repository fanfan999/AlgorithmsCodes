package basic.day04;

import java.util.Arrays;

/**
 * 旋转二维数组
 *  因为要旋转,所以这个题的潜台词就是这个二维数组肯定是正方形的
 */
public class RotateArray {
    public void rotateArray(int[][] array) {
        //定义左上角的坐标
        int lR = 0;
        int lC = 0;
        //定义右下角的坐标
        int rR = array.length - 1;
        int rC = array[0].length - 1;

        //循环旋转,当左上角的行坐标大于右下角的行坐标或者列坐标大于右下角的列坐标,就说明已经都旋转结束了
        while (lR <= rR || lC <= rC) {
            rotateProcess(array, lR++, rR--, lC++, rC--);
        }

        //旋转结束后,打印数组
        printArray(array);
    }

    /**
     *
     * @param array
     * @param lR 左上角行坐标,初始值为0
     * @param rR 右下角的行坐标
     * @param lC 左上角列坐标,初值为0
     * @param rC 右下角列坐标
     */
    private void rotateProcess(int[][] array, int lR, int rR, int lC, int rC) {
        //一圈的循环次数就是一行中最后一个元素减去一行中第一个元素,
        // 比如一行有三个元素,则转一圈需要循环3-1=2次,不懂可以画个图理解一下
        for (int i = 0; i < (rC - lC); i++) {
            //把左上角的数存起来先
            int temp = array[lR][lC + i];
            array[lR][lC + i] = array[rR - i][lC];
            array[rR - i][lC] = array[rR][rC - i];
            array[rR][rC - i] = array[lR + i][rC];
            array[lR + i][rC] = temp;
        }
    }

    private void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }
}

class Test2{
    public static void main(String[] args) {
        int[][] array = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        new RotateArray().rotateArray(array);
    }
}