package mythread;

/**
 * created by linghaoDo on 2020-03-08
 * description:
 * <p>
 * version:
 */
public class Test1 {

    public static void main(String args[]) {
        InterruptedThread thread;
        thread = new InterruptedThread();
        thread.start();
        thread.isStopped = true;
    }
}
