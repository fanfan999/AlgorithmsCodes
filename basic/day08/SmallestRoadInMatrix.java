package basic.day08;

/**
 * 给定一个二维矩阵,从左上角到右下角的的路径的最小值
 */
public class SmallestRoadInMatrix {
    /**
     * @param matrix 要走的二维数组
     * @param row    当前位置在哪一行
     * @param col    当前位置在那一列
     * @return 返回的就是最短的路径
     */
    public static int process(int[][] matrix, int row, int col) {
        //如果当前位置就在最右下角,直接返回当前位置的值就成,因为根本不需要动
        if (row == matrix.length - 1 && col == matrix[0].length - 1) {
            return matrix[row][col];
        }

        //如果当前位置在右边界上,就只能往下移动,
        // 路径和为此位置的值加上下面几个位置到右下角的值
        if (col == matrix[0].length - 1) {
            return matrix[row][col] + process(matrix, row+1, col);
        }

        /**
         * 如果当前位置在下边界,也就是说在最后一行,只能向右移动
         * 那么路径和就是当前位置的值一直加到右下角位置的值
         */
        if (row == matrix.length - 1) {
            return matrix[row][col] + process(matrix, row, col+1);
        }

        /**
         * 如果当前位置既可以往下走,也可以往右走,那就比较往下走和往右走的路径和
         */
        int right = matrix[row][col] + process(matrix, row, col + 1);
        int down = matrix[row][col] + process(matrix, row+1, col);

        return Math.min(right, down);
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4}, {5,1,3,0},{1,0,3,8}};
        int i = process(matrix, 0, 0);
        System.out.println(i);
    }
}
