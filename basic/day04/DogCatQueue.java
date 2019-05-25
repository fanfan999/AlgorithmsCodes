package basic.day04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列问题
 * 这里用内部类来写
 * 思路:
 *  1. 我们可以使用两个队列,一个放猫,一个放狗;
 *  2. 当我们需要弹出所有动物最早的一个的时候,就有个问题,我们怎么找到两个队列中较早的那个呢?
 *  我们可以新建一个类,题目给定的基础类是不能动的,该新建的类用于给每个加进来的动物赋予一个特定的数字标识一下
 */
public class DogCatQueue {
    //宠物类
    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return type;
        }
    }

    //猫类
    public static class Cat extends Pet{

        public Cat() {
            super("cat");
        }

    }

    //狗类
    public static class Dog extends Pet{

        public Dog() {
            super("dog");
        }

    }

    //标识动物的类
    public static class IdentifyPet {
        //用于判别是猫还是狗的成员变量
        private Pet pet;
        //用于标识的成员变量
        private int count;

        //通过构造函数判断是属于猫还是狗,同时改变count实现区分
        public IdentifyPet(Pet pet, int count) {
            this.pet = pet;
            this.count = count;
            //System.out.println(pet.getType() + " : " + count);
        }

        public Pet getPet() {
            return pet;
        }

        public int getCount() {
            return count;
        }

        //获取当前宠物的类型
        public String getPetType() {
            return pet.getType();
        }
    }

    //实现猫狗队列方法的类
    public static class DogAndCatQueue {
        //声明猫狗变量
        private Queue<IdentifyPet> dog;
        private Queue<IdentifyPet> cat;
        //声明标识变量
        private int count;

        //初始化
        public DogAndCatQueue() {
            this.dog = new LinkedList<IdentifyPet>();
            this.cat = new LinkedList<IdentifyPet>();
            this.count = 0;
        }

        //加入队列
        public void add(Pet pet) {
            //如果为狗类
            if (pet.getType().equals("dog")) {
                this.dog.offer(new IdentifyPet(pet, this.count++));
            }else if (pet.getType().equals("cat")) {
                //如果为猫类
                this.cat.offer(new IdentifyPet(pet, this.count++));
            }else {
                //如果都不是,就报错
                throw new RuntimeException("请存放猫或者狗进队列");
            }
        }

        //取出两个队列的首元素
        public Pet pollAll() {
            //如果两个均为空
            if (this.cat.isEmpty() && this.dog.isEmpty()) {
                throw new RuntimeException("为空!");
            }else if (this.cat.isEmpty()){
                //如果任意一个为空另一个不空,直接返回另一个队列首元素
                return this.dog.poll().getPet();
            }else if (this.dog.isEmpty()) {
                return this.cat.poll().getPet();
            }
            else {
                //判断谁先进来
                if (this.dog.peek().getCount() < this.cat.peek().getCount()) {
                    return this.dog.poll().getPet();
                }else {
                    return this.cat.poll().getPet();
                }
            }
        }

        //取出狗队列首元素
        public Dog pollDog() {
            return (Dog) this.dog.poll().getPet();
        }

        //取出猫队列首元素
        public Cat pollCat() {
            return (Cat) this.cat.poll().getPet();
        }

        //判断是否所有队列为空
        public boolean isEmpty() {
            if (this.cat.isEmpty() && this.dog.isEmpty()) {
                return true;
            }else {
                return false;
            }
        }

        //判断猫队列是否为空
        public boolean isCatEmpty() {
            if (this.cat.isEmpty()) {
                return true;
            }else {
                return false;
            }
        }

        //判断猫队列是否为空
        public boolean isDogEmpty() {
            if (this.dog.isEmpty()) {
                return true;
            }else {
                return false;
            }
        }
    }

    //测试类
    public static class Test {
        public static void main(String[] args) {
            DogAndCatQueue queue = new DogAndCatQueue();

            Pet dog1 = new Dog();
            Pet dog2 = new Dog();
            Pet dog3 = new Dog();
            Pet cat1 = new Cat();
            Pet cat2 = new Cat();
            Pet cat3 = new Cat();

            queue.add(dog1);
            queue.add(cat1);
            queue.add(dog2);
            queue.add(cat2);
            queue.add(dog3);
            queue.add(cat3);

            System.out.println(queue.isCatEmpty());
            System.out.println(queue.isDogEmpty());
            System.out.println(queue.isEmpty());

            System.out.println(queue.pollAll());
            System.out.println(queue.pollAll());

            System.out.println(queue.pollCat());
            System.out.println(queue.pollCat());

            System.out.println(queue.pollDog());
        }
    }
}
