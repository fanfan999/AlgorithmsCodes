package basic.day08;

/**
 * 打印所有的子序列,包括空字符串
 */
public class PrintSonStr {
    /**
     *
     * @param chs 这是要输出其子序列的字符串
     * @param i 表示当前在第几个字符来了
     * @param result 表示当前已经形成的字符串
     */
    public static void printSubString(char[] chs, int i, String result) {
        //base case,当我的子串已经到了我最后的位置了,就没有子串可以打印了
        if (i == chs.length) {
            System.out.println(result);
            return;
        }

        //这里就是说我可以不用我当前的字符和后面的拼接
        printSubString(chs, i +1, result);
        //也可以把我当前的字符拿去和后面的字符拼接
        printSubString(chs, i +1, result + String.valueOf(chs[i]));
    }

    public static void main(String[] args) {
        String str = "abc";

        printSubString(str.toCharArray(), 0, "");
    }

}
