package basic.day08;

/**
 * 给定一个正数数组arr和一个正整数aim,可以任意选择arr中的数字,看能不能累加得到aim
 * 返回true或false
 */
public class IfSumIsAim {
    /**
     *
     * @param arr 给定的数组
     * @param index 当前的数的下标
     * @param sum 已经形成的和
     * @param aim 目标要形成的和
     * @return
     */
    public static boolean ifAddToAim(int[] arr, int index, int sum, int aim) {

        if (index == arr.length) {
            return sum == aim;
        }
        //当前值不和后面的相加
        boolean result1 = ifAddToAim(arr, index+1, sum, aim);
        //当前值和后面的相加
        boolean result2 = ifAddToAim(arr, index+1, sum+arr[index], aim);

        return result1 || result2;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,7};

        boolean b = ifAddToAim(arr, 0, 0, 90);
        System.out.println(b);
    }
}
