package basic.day06;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 设计一个randompool结构
 * <p>
 * 思路: 因为map本来就不重,所以题目中去重问题就直接解决了
 * 1. 准备两张哈希表map1,map2,
 * 因为只有key,我们就手动地将`插入的次序(也就是从0开始的递增的数)`作为value放到map1中
 * 然后将map1中的value, key分别作为map2中的key,value
 * 2. 插入结束后,因为插入的次序是有序的,所以想要获取一个随机的元素的话
 * 我们可以随机获取一个元素的下标,也就是它插入的次序,然后在map2中通过value= get(key)方法获取到目标值.
 */
public class CreateRandomPool {
    //这里index是从0开始
    private static int index = 0;
    private static HashMap<Object, Object> map1 = new HashMap<Object, Object>();
    private static HashMap<Object, Object> map2 = new HashMap<Object, Object>();

    public static void setEle(Object key) {
        map1.put(key, index);
        map2.put(index, key);
        index++;
    }

    public static void insert(Object key) {
        setEle(key);
    }

    //删除元素要注意一下

    /**
     * 如果我们直接删的话,就会在中间产生洞,那么我们在生成随机元素的时候就有可能生成null
     * 这不是我们想要的,思路就是我们把要删除的那条记录和最后一条记录交换,然后删除最后一条记录
     * 注意最后index要减一
     *
     *      其实在实现的时候我们是用最后一个元素的值去替代待删除的元素,
     *      这时候要删除的元素在哈希表里已经没有了,但是因为减少了一个元素,所以容量要减一
     *      又因为最后一个元素在要删除的位置已经有了,所以删除最后一个元素就可以了
     * @param key 要删除的元素,这里是"li"
     * @return
     */
    public static boolean delete(Object key) {
        if (map2.isEmpty() || map1.isEmpty()) {
            return false;
        }

        if (map1.containsKey(key)) {
            //即将删除的键值对的值,也就是填入时候的次序,本例中是2
            Object deleteVal = map1.get(key);
            //找到最后一条记录在map1中的键值对为lastEle--index,本例中为lei--4
            Object lastEle = map2.get(--index);
            //删除了一个元素,index也要--,因为index的角色就是相当于数组下标
            //将最后一个元素的value换成要删除元素的value,这样随机的时候下标还是从一个范围到一个范围中间没有空
            map1.put(lastEle, deleteVal);
            map2.put(deleteVal, lastEle);
            //删除元素
            map1.remove(key);
            map2.remove(deleteVal);

        }
        return false;
    }

    public static Object getRandom() {
        if (index == 0) {
            return null;
        }

        Integer i = (int) (Math.random() * index);

        return map2.get(i);
    }

    public static void main(String[] args) {
        insert("fan");
        insert("li");
        insert("cun");
        insert("lei");
        System.out.println(map1 + "==" + map2);

        delete("li");
        System.out.println(map1 + "==" + map2);

    }

}
