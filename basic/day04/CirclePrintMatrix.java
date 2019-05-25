package basic.day04;

/**
 * 转圈打印数组
 */
public class CirclePrintMatrix {
    public void circlePrintMatrix(int[][] array) {
        //定义左上角下标变量,初始值为(0,0)
        int leftTopRow = 0;
        int leftTopCol = 0;
        //定义右下角变量下标,初始值为(array.length -1),(array[0].length - 1)
        int rightBottomRow = array.length - 1;
        int rightBottomCol = array[0].length - 1;
        System.out.println(leftTopRow+":"+leftTopCol+":"+rightBottomRow+":"+rightBottomCol);

        //循环打印数组,当左上角行大于右下角行或者左上角列大于右下角列的时候就停止
        while (leftTopRow <= rightBottomRow || leftTopCol <= rightBottomCol) {
            printEdge(array, leftTopRow++, leftTopCol++, rightBottomRow--, rightBottomCol--);
        }
    }

    /**
     *
     * @param array
     * @param lR 左上角行下标
     * @param lC 左上角列下标
     * @param rR 右下角行下标
     * @param rC 右下角列下标
     */
    private void printEdge(int[][] array, int lR, int lC, int rR, int rC) {
        //当是单行元素的时候,直接打印一行就好了,只有列变化
        if (lR == rR) {
            while (lC <= rC) {
                System.out.println(array[lR][lC++]);
            }
        }else if (lC == rC) {
            //当是单列元素的时候,直接打印一列,只有行变化
            while (lR <= rR) {
                System.out.println(array[lR++][lC]);
            }
        }else {
            int tempLR = lR;
            int tempLC = lC;
            int tempRR = rR;
            int tempRC = rC;
            //当不是单行单列的时候,先从左到右,行不变,列变
            while (tempLC < rC){
                System.out.println(array[lR][tempLC++]);
            }
            System.out.println("____");
            //然后从上往下,列不变,行变
            while (tempLR < rR){
                System.out.println(array[tempLR++][rC]);
            }
            System.out.println("++++");
            //然后再从右往左,行不变, 列变
            while (lC < tempRC) {
                System.out.println(array[rR][tempRC--]);
            }
            System.out.println("----");
            //最后再从下往上,列不变,行变
            while (tempRR > lR) {
                System.out.println(array[tempRR--][lC]);
            }
            System.out.println("====");

        }
    }

}
class Test{
    public static void main(String[] args) {
        int[][] array = new int[][]{{1,2}};
        new CirclePrintMatrix().circlePrintMatrix(array);
    }
}