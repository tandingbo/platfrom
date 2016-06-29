package com.tanbobo.platfrom.base.common.threads1;

/**
 * 循环队列类
 */
public class QueueArray {
    private Object[] obj;   //初始化储存空间
    private int front;     //头指针,若队列不为空,指向队列头元素
    private int rear;      //尾指针,若队列不为空,指向队列尾元素的下一个位置

    public QueueArray() {
        this(10);
    }

    public QueueArray(int size) {
        //队列进行初始化
        obj = new Object[size];
        front = 0;
        rear = 0;
    }

    public Object dequeue() {//出队
        if (rear == front) {
            return null;
        }

        Object ob = obj[front];
        front = (front + 1) % obj.length;
        return ob;
    }

    public boolean enqueue(Object obje) {//入队
        if ((rear + 1) % obj.length == front) {
            return false;
        }

        obj[rear] = obje;
        rear = (rear + 1) % obj.length;
        return true;
    }

    public static void main(String[] args) {
        QueueArray d = new QueueArray(10);
        for (int i = 0; i <= 20; i++) {
            System.out.println(i + "  " + d.enqueue(i));
            if (i > 8) {
                System.out.println(i + "  " + d.dequeue());
            }
        }
    }
}
