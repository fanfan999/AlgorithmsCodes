package basic.day08;

/**
 * 汉诺塔
 */
public class HanNuoTa {
    /**
     *
     * @param n 表示当前是1~n的问题,理解为1~n个盘子,且都在left杆上
     * @param left
     * @param middle
     * @param right
     */
    public static void process(int n, String left, String middle, String right) {

        //如果n=1,也就是说左边杆上只有1个盘子了,直接移到right杆上
        if (n == 1) {
            System.out.println("Move 1 from-" + left + "-to-" + right);
        }else {
            //如果不是只有1个元素,有n个盘子
            //1.把n-1个盘子从left移动middle杆上去,注意传参的时候对于参数的位置
            process(n-1,left,right,middle);
            //2. 把from剩下的一个盘子从left移动到right中去
            //process(1,left,middle,right);,这样写更直观,此时单独挪动n
            System.out.println("Move " + n +" from-" + left + "-to-" + right);
            //3. 把middle剩下的移动到right中去
            process(n-1,middle,left,right);
        }
    }

    public static void main(String[] args) {
        process(3, "left杆","middle杆", "right杆");
    }
}
