package basic.day08;

import sun.net.www.protocol.http.HttpURLConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * 输出字符串的全排列
 * 思路: 将当前字符不断与后面的字符交换
 * 然后将整个问题化解为求当前字符和后面字符全排列的问题
 * 注意: 每次交换后要换回原来刚开始的顺序,不然那后面会乱七八糟.
 */
public class QuanPaiLie {
    public static void printAllStr(char[] chs, int i) {
        if (chs == null) {
            return;
        }

        List<String> list = new ArrayList<>();

        process(chs, i);
    }

    private static void process(char[] chs, int i) {
        if (i == chs.length - 1) {
            String s = String.valueOf(chs);
            System.out.println(s);
        }

        for (int t = i; t < chs.length; t++) {
            swap(chs, i, t);
            process(chs, i + 1);
            swap(chs, i, t);
        }
    }

    public static void swap(char[] chs, int t, int i) {
        char temp = chs[t];
        chs[t] = chs[i];
        chs[i] = temp;
    }

    public static void main(String[] args) {
        String str = "abc";

        printAllStr(str.toCharArray(), 0);
    }
}
