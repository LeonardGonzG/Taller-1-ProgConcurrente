package taller1_progconcurrente;

/**
 *
 * @author Leonardo González G.
 */
public class Task implements Runnable {

    private long from;
    private long to;
    private Thread theThread = null;
    private long count;
    private long num;
    private int COD;

    public Task(long from, long to, long num, int cod) {
        this.from = from;
        this.to = to;
        this.num = num;
        this.COD = cod;
    }

    @Override
    public void run() {
        this.count = Divisor.numOfPosDivisors(num, from, to);
    }

    public Thread start() {

        if (theThread == null) {
            this.theThread = new Thread(this);
            theThread.start();
        }
        return theThread;

    }

    public long getCount() {
        return count;
    }

    public int getCOD() {
        return COD;
    }

    public Thread getTheThread() {
        return theThread;
    }

}
