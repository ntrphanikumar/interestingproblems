package interesting.leetcode.msi;

public class JobScheduler {
    static class Task1 extends java.util.TimerTask {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task1 " + new java.util.Date());
        }
    }

    static class Task2 extends java.util.TimerTask {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task2 " + new java.util.Date());
        }
    }

    public static void main(String[] args) {
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new Task1(), 0, 2000);
        new java.util.Timer().scheduleAtFixedRate(new Task2(), 0, 3000);
    }
}
