package basic.day04;

import java.util.Arrays;

/**
 * 之字形打印矩阵
 */
public class ZhiPrintMatrix {
    public void zhiPrintMatrix(int[][] array) {
        //判断数组有效性
        if (array.length == 0 || array == null) {
            return;
        }

        //定义AB两点坐标,初始位置都在(0,0)
        int APointR = 0;
        int APointC = 0;
        int BPointR = 0;
        int BPointC = 0;
        //定义控制打印方向的变量flag
        boolean flag = false;
        //定义结束位置的坐标
        int endR = array.length - 1;
        int endC = array[0].length - 1;

        //当a点的行到达右下角或者b点的列到达右下角,说明已经走完了
        //千万注意,后移动的语句放在前面,比如A点的行语句放在列语句前面,不然会漏掉某些对角线的值,真的,会搞死人的.
        while (APointR <= endR) {
            printMatrix(array, APointR, APointC, BPointR, BPointC, flag);
            //当a点行到最右边的时候,行动列不动
            APointR = APointC >= endC ? APointR + 1 : APointR;
            //a点还没到最右边,列动行不动
            APointC = APointC >= endC ? APointC : APointC + 1;
            //b到了最下面,列动行不动
            BPointC = BPointR >= endR ? BPointC + 1 : BPointC;
            //b还没到最下面,行动列不动
            BPointR = BPointR >= endR ? BPointR : BPointR + 1;

            //改变flag的值
            flag = !flag;
            //System.out.println(flag);
        }

    }

    /**
     * 在这个函数里,每次打印一条对角线
     * @param array
     * @param aPointR A点的行
     * @param aPointC A点的列
     * @param bPointR B点的行
     * @param bPointC B点的列
     * @param flag 打印方向控制
     */
    private void printMatrix(int[][] array, int aPointR, int aPointC, int bPointR, int bPointC, boolean flag) {
            //从下往上打印
            if (!flag) {
                while (bPointR >= aPointR) {
                    System.out.println(array[bPointR--][bPointC++]);
                }
            }else {
                //从上往下打印
                while (aPointR <= bPointR) {
                    System.out.println(array[aPointR++][aPointC--]);
                }
            }
    }
}

class Test3{
    public static void main(String[] args) {
        int[][] array = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        new ZhiPrintMatrix().zhiPrintMatrix(array);
    }
}
