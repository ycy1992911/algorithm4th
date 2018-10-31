package chapter2.episode1;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Comparable 与 Comparator
 *
 * @author chongyang18@gmail.com
 * @date 29/01/2018
 */
class myComparable implements Comparable<myComparable> {
    private int name;
    private int size;

    @Override
    public int compareTo(@NotNull myComparable mc) {
        if(this.size >= mc.getSize()) {
            return 1;
        }else{
            return -1;
        }
    }

    private int getName() {
        return name;
    }

    private int getSize() {
        return size;
    }
}

class myComparator implements Comparator<myComparator> {
    private int age;

    @Override
    public int compare(myComparator o1, myComparator o2) {
        if(o1.getAge() >= o2.getAge()) {
            return 1;
        }else{
            return -1;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        List<myComparator> list = new ArrayList<>();
        myComparator m1 = new myComparator();
        m1.setAge(20);
        myComparator m2 = new myComparator();
        m2.setAge(11);
        list.add(m1);
        list.add(m2);

        // 传入比较器
        list.sort(new myComparator());
        /*
        // 如果只用一次，此处new myComaprator比较器也可以用匿名内部类new Comparator<>(){...}替代
        list.sort(new Comparator<myComparator>() {
            @Override
            public int compare(myComparator o1, myComparator o2) {
                if(o1.getAge() >= o2.getAge()) {
                    return 1;
                }else{
                    return -1;
                }
            }
        });*/

        for (myComparator myComparator : list) {
            System.out.print(myComparator.getAge() + " ");
        }
    }
}
