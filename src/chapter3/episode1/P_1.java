package chapter3.episode1;

/**
 * @author chongyang18@gmail.com
 * @date 20/02/2018
 */
public class P_1 {
    private class Node{
        private String key;
        private double value;
        private Node next;

        Node(String key, double value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int size;
    private Node head;
    private Node tail;

    private P_1(){
        size = 0;
        head = null;
        tail = null;
    }

    private void add(String key, double value) {
        if(size == 0) {
            head = new Node(key, value, tail);
            tail = head;
        }else{
            Node newTail = new Node(key, value, null);
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    private void iterator() {
        Node index = head;
        if(index == null){
            throw new NullPointerException("Empty dictionary");
        }
        while(index != null) {
            System.out.println(index.key + " " + index.value);
            index = index.next;
        }
    }

    private double gpa() {
        Node index = head;
        double total = 0.0;
        if(index == null) {
            return total;
        }

        while(index != null) {
            total += index.value;
            index = index.next;
        }

        return total/size;
    }

    public static void main(String[] args) {
        P_1 p = new P_1();
        p.add("A+", 4.33);
        p.add("A", 4.00);
        p.add("A-", 3.67);
        p.add("B+", 3.33);
        p.add("B", 3.00);
        p.add("B-", 2.67);
        p.add("C+", 2.33);
        p.add("C", 2.00);
        p.add("C-", 1.67);
        p.add("D-", 1.00);
        p.add("F-", 0.00);
        p.iterator();
        System.out.println("GPA: " + p.gpa());
    }
}
