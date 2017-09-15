package src;

public class Philosopher extends Thread {
    final int timeThink_max = 4000;
    final int timeNextFork = 1000;
    final int timeEat_max = 3000;
    private GraphicTable table;
    private int left;
    private int right;
    private int ID;
    private boolean asleep;


    public Philosopher(int ID, GraphicTable table, int left, int right) {
        this.ID = ID;
        this.table = table;
        this.left = left;
        this.right = right;
        this.asleep = false;
        setName("Philosopher " + ID);
    }

    public void run() {
        doSleep(500l);
        for (; ; ) {

            table.doThinking(ID);
            System.out.println(getName() + " thinks");
            //doSleep(1000l);
            doSleep((long)(Math.random()*timeThink_max));
            System.out.println(getName() + " finished thinking");

            System.out.println(getName() + " is hungry");
            table.becomesHungry(ID);

            System.out.println(getName() + " wants left chopstick");
            table.take(left);
            table.takeChopstick(ID, left);
            System.out.println(getName() + " got left chopstick");

            doSleep(timeNextFork);

            System.out.println(getName() + " wants right chopstick");
            table.take(right);
            table.takeChopstick(ID, right);
            System.out.println(getName() + " got right chopstick");

            final double timeToSleep = Math.random() * timeEat_max;
            System.out.println(getName() + " eats for " + timeToSleep);
            doSleep((long) timeToSleep);

            System.out.println(getName() + " finished eating");

            table.releaseChopstick(ID, left);
            table.release(left);
            System.out.println(getName() + " released left chopstick");

            table.releaseChopstick(ID, right);
            table.release(right);
            System.out.println(getName() + " released right chopstick");
        }
    }

    public void doSleep(long millis) {
        try {
            asleep = true;
            sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        asleep = false;
    }

    public boolean isAsleep() {
        return asleep;
    }
    

}
