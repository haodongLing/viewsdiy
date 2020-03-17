package mythread;

/**
 * created by linghaoDo on 2020-03-08
 * description:
 * <p>
 * version:
 */
public class InterruptedThread extends Thread {
    volatile boolean isStopped = false;

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 10000; i++) {
            if (isStopped) {
                return;
            }
        }
    }


}
