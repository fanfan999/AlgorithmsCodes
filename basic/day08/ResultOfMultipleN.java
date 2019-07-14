package basic.day08;

/**
 * 求n的阶乘
 */
public class ResultOfMultipleN {
    //暴力方法
    public static long getResult(int n) {
        long result = 1L;

        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    //递归版本,n的阶乘就相当于是求n*(n-1)的阶乘
    public static long getResult1(int n) {
        //这就是basecase
        if (n == 1) {
            return 1L;
        }

        return (long) n * getResult1(n - 1);
    }


}
