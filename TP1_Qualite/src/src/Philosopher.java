package src;

public class Philosopher extends Thread {

    final int timeThink_max = 100;
    final int timeNextFork = 100;
    final int timeEat_max = 100;
    private GraphicTable table;
    private int leftChopstickID;
    private int rightChopstickID;
    private int ID;
    private boolean asleep;

    public Philosopher(int ID, GraphicTable table, int leftChopstickID, int rightChopstickID) {
        this.ID = ID;
        this.table = table;
        this.leftChopstickID = leftChopstickID;
        this.rightChopstickID = rightChopstickID;
        this.asleep = false;
        setName("Philosopher " + ID);
    }

    public synchronized void run() {
        doSleep(500l);
        for (;;) {

            this.table.doThinking(ID, table);
            System.out.println(getName() + " thinks");
            //doSleep(1000l);
            doSleep((long) (Math.random() * timeThink_max));
            System.out.println(getName() + " finished thinking");

            
            becomesHungry(ID);

            
            takeLeftChopstick();
            

            doSleep(timeNextFork);

            
            takeLeftChopstick();
            

            final double timeToSleep = Math.random() * timeEat_max;
            System.out.println(getName() + " eats for " + timeToSleep);
            doSleep((long) timeToSleep);

            System.out.println(getName() + " finished eating");

            releaseLeftChopstick();
            

            releaseRightChopstick();
           
        }
    }

    public boolean isAsleep() {
        return asleep;
    }
    
    public synchronized void prepareToEat(){
        
        takeChopsticks();
    }
    
    public synchronized void takeChopsticks(){
        takeLeftChopstick();
        takeRightChopstick();
    }
    
    public synchronized void releaseLeftChopstick() {
        table.colorChopstick(this.leftChopstickID, -1);
        table.release(this.leftChopstickID);
        logChopstickAction("release", this.leftChopstickID);
    }

    public synchronized void releaseRightChopstick() {
        table.colorChopstick(this.rightChopstickID, -1);
        table.release(this.rightChopstickID);
        logChopstickAction("release", this.rightChopstickID);
    }

    public synchronized void takeLeftChopstick() {
        logChopstickAction("wants", this.leftChopstickID);
        System.out.println(String.format("repainting ph %d - color %d ", this.ID, this.leftChopstickID));
        table.colorChopstick(this.ID, this.leftChopstickID);
        table.take(this.leftChopstickID);
        logChopstickAction("got", this.leftChopstickID);
    }
    
    public synchronized void takeRightChopstick() {
        logChopstickAction("wants", this.rightChopstickID);
        //System.out.println(String.format("repainting ph %d - color %d ", phID, this.rightChopstickID));
        table.colorChopstick(this.ID, this.rightChopstickID);
        table.take(this.rightChopstickID);
        logChopstickAction("got", this.rightChopstickID);
    }
    
    public void logChopstickAction(String action, int chopstickID) {
        String whichChopstick = (chopstickID == this.leftChopstickID) ? "left" : "right";
        System.out.println(getName() + " " + action + " " + whichChopstick + " chopstick");
    }

    public synchronized void doSleep(long millis) {
        try {
            asleep = true;
            sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        asleep = false;
    }

    public synchronized void becomesHungry(int phID) {
        System.out.println(getName() + " is hungry");
        while (GraphicTable.compte == GraphicTable.NUMBER_PEOPLE || GraphicTable.enAttente > 0) {
            //this.table.attente();
        }
        this.table.compte++;
    }

}
