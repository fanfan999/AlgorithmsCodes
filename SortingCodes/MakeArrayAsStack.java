package fan;

/**
 * 用数组结构实现大小固定的栈(先进后出)
 */
public class MakeArrayAsStack {

    //定义数组
    private Integer[] array;
    //定义数组内当前元素的位置
    private Integer index;

    //在构造函数中初始化栈的大小
    public MakeArrayAsStack(int iniSize) {
        //判断数组是否有效
        if (iniSize < 0) {
            throw new RuntimeException("栈的长度至少为0,不能小于0");
        }
        array = new Integer[iniSize];
        index = 0;
    }

    //入栈
    public void push(int obj) {
        //当数组中还有空间时,就放,因为size就相当于元素下标,
        //所以size不能等于数组长度
        if (index < array.length) {
            //注意弹出的时候index等于array.length!!!!!!!!!!!!
            array[index++] = obj;
        } else {
            throw new ArrayIndexOutOfBoundsException("栈已满!无法存储元素.");
        }
    }

    //出栈,弹出当前size所在元素,然后size--
    public int pop(){
        //判断数组中是否有元素
        if (index > 0) {
            return array[--index];
        } else {
            throw new ArrayIndexOutOfBoundsException("栈已空!无法取出元素.");
        }
    }

    //弹出最上面的元素,但是size不变
    public int peek() {
        //当数组有元素时,才弹出
        if (index >= 0) {
            return array[index - 1];
        }else {
            throw new ArrayIndexOutOfBoundsException("栈已空,无法弹出元素.");
        }
    }

    //重写toString()方法


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        out : for (int i = 0; i < index; i++) {
            if (i == 0) {
                builder.append("[");
            }
            if (i == (index - 1)) {
                builder.append(array[i] + "]");
                break out;
            }
            builder.append(array[i] + ", ");
        }
        return builder.toString();
    }

    //获取栈的大小
    public int size() {
        return index;
    }
}
