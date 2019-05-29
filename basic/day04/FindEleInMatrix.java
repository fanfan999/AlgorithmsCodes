package basic.day04;

/**
 * 在给定的n*m的有序整型矩阵找到目标值K,存在返回true,不存在返回false
 */
public class FindEleInMatrix {
    public static void findEle(int[][] array, int K) {
        //把右上角元素作为起点
        int start = array[0][array[0].length - 1];

        //用于接收函数返回值
        boolean flag;

        flag = findEleInMatrix(array, K);
        System.out.println(flag);
    }

    /**
     *
     * @param array
     * @param tar 要找的目标元素
     */
    private static boolean findEleInMatrix(int[][] array, int tar) {
        boolean flag = false;
        //最后一行的下标,
        int rows = array.length - 1;
        //当前所在行
        int curRow = 0;
        //当前所在列
        int curCol = array[0].length - 1;

        //在本例中,当移动位置的行超过了最后一行或者
        // 当前所在列超过了最前面一列就表示不存在了,返回false
        while (curRow <= rows && curCol >= 0) {
            if (array[curRow][curCol] == tar) {
                flag = true;
                break;
            }
            //当前值比目标值小,下移,列不变
            if (array[curRow][curCol] < tar) {
                curRow++;
            }else {
                //当前值比目标值大,左移
                curCol--;
            }
        }
        return flag;
    }
}

class Test4{
    public static void main(String[] args) {
        int[][] array = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        FindEleInMatrix.findEle(array, 90);
    }
}