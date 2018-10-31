package algorithm4th.chapter1.episode3;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * 环形缓冲区：又称环形队列，是一种定长为N的先进先出的数据结构。它在进程间的异步数据传输或记录日志文件是十分有用。
 * 当缓冲区为空时，消费者 会在数据存入缓冲区前等待，当缓冲区满时，生产者会等待将数据存入缓冲区。
 * <p>
 * 判断是否满：if ( (tail = (tail + 1) & (buffer.length - 1)) == head)
 * 在构造buffer元素的时候，它的长度一定是2的指数级，所以对于任意一个2的指数级的值减去1之后必然所有位全为1，
 * 例如：8-1之后为111，16-1之后1111。而对于tail来说，当tail+1小于等于elements.length - 1，两者与完之后的结果还是tail+1，
 * 但是如果tail+1大于elements.length - 1，两者与完之后就为0，回到初始位置。这种判断队列是否满的方式要远远比我们使用符号%直接取模高效
 *
 * @author chongyang18@gmail.com
 * @date 23/01/2018
 */
public class P_39 implements Iterable {
    private Object[] buffer;
    private int size;
    private int head;
    private int tail;
    private boolean isFull = false;

    P_39() {
        buffer = new Object[16];
    }

    public static void main(String[] args) {
        P_39 p = new P_39();
        for (int i = 0; i < 16; i++) {
            p.add(i);
        }
        p.remove();
        p.remove();
        p.remove();
        p.add(1);
        p.add(2);
        p.add(17);
        p.add(18);
        System.out.println(p.size());
        for (Object o : p) {
            System.out.print(o + " ");
        }
    }

    boolean isFull() {
        return isFull;
    }

    void add(Object o) {
        if (!isFull) {
            buffer[tail] = o;
            size++;
            if ((tail = (tail + 1) & (buffer.length - 1)) == head) {
                isFull = true;
            }
        }
    }

    void remove() {
        buffer[head] = null;
        head = (head + 1) % buffer.length;
        size--;
        isFull = false;
    }

    int size() {
        return size;
    }

    @NotNull
    @Override
    public Iterator iterator() {
        return new Iterator() {
            int count = head;
            boolean isLooped = false;
            boolean firstTime = true;

            @Override
            public boolean hasNext() {
                return !isLooped;
            }

            @Override
            public Object next() {
                boolean retrieve = false;
                if (count > buffer.length - 1) {
                    if ((count % buffer.length) - 1 < 0) {
                        retrieve = true;
                        firstTime = false;
                    } else {
                        count = count % buffer.length;
                    }
                }

                if (retrieve) {
                    count = 0;
                    retrieve = false;
                }

                if (!firstTime && count == tail - 1) {
                    isLooped = true;
                }

                return buffer[count++];
            }
        };
    }
}
