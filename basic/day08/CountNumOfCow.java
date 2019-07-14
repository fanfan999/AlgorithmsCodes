package basic.day08;

/**
 * 母牛每年可以生一头母牛,新出生的母牛成长三年后也能每年生一头母牛,假设不会死
 * 求N年后,母牛的数量
 */
public class CountNumOfCow {
    /**
     *
     * @param n 表示n年后
     */
    public static int countTotalNum(int n) {
        if (n <= 0) {
            return 0;
        }

        int count = 0;
        if (n <=4) {
            return n;
        }else {
           count = countTotalNum(n-1) + countTotalNum(n-3);
        }

        return count;
    }

    public static void main(String[] args) {
        int n = 7;
        int i = countTotalNum(n);
        System.out.println("第" + n + "年的母牛总数为:" + i);
    }
}
