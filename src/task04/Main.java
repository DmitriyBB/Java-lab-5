package task04;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    public static void main(String[] args) {
        Queue<String> waitlist = new ArrayDeque<>();
        waitlist.offer("anna");
        waitlist.offer("boris");
        waitlist.offer("carl");
        System.out.println("waitlist: " + waitlist);
        System.out.println("peek:     " + waitlist.peek());
        System.out.println("poll:     " + waitlist.poll());
        System.out.println("poll:     " + waitlist.poll());
        System.out.println("rest:     " + waitlist);

        waitlist.poll();
        System.out.println();
        System.out.println("empty queue:");
        System.out.println("  peek()    = " + waitlist.peek());
        System.out.println("  poll()    = " + waitlist.poll());
        try {
            waitlist.element();
        } catch (NoSuchElementException e) {
            System.out.println("  element() -> NoSuchElementException");
        }
        try {
            waitlist.remove();
        } catch (NoSuchElementException e) {
            System.out.println("  remove()  -> NoSuchElementException");
        }

        Queue<String> limited = new ArrayBlockingQueue<>(1);
        limited.add("anna");
        System.out.println();
        System.out.println("full queue with capacity 1:");
        System.out.println("  offer() = " + limited.offer("boris"));
        try {
            limited.add("boris");
        } catch (IllegalStateException e) {
            System.out.println("  add()   -> IllegalStateException: " + e.getMessage());
        }
    }
}
