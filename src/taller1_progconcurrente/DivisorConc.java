/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller1_progconcurrente;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LeonardoGonz
 */
public class DivisorConc implements Runnable {

    private long from;
    private long to;
    private Thread theThread = null;
    private int count;
    private long num;
    private int COD;

    public DivisorConc(long from, long to, long num, int cod) {
        this.from = from;
        this.to = to;
        this.num = num;
        this.COD = cod;
    }

    public DivisorConc() {
    }

    public static long numOfPosDivisors(long num, int numThreads) {

        if (num > 1 && numThreads > 0) {
            return DivisorConc.makeThread(num, numThreads)+1;
        }
        return 0;

    }

    /*Important */
    private static long makeThread(long num, int numThreads) {

        long limit =  num / 2;
        
        if(limit%2==0){
            limit++;
        }
        

        long range = searchRange(limit, numThreads);
        System.out.println("limit: "+limit + " range: "+range);
        
        DivisorConc[] tasks = new DivisorConc[numThreads];
        Thread[] threads = new Thread[numThreads];

        int pos = 0;
        long rangeA = 0;
        long rangeB = 0;
        for (int i = 0; i <= limit; i += range) {
            if (pos < numThreads) {

                if (pos == 0) {

                    rangeA = 1;
                    rangeB += range;
                    tasks[pos] = new DivisorConc(rangeA, rangeB, num, pos);
                    System.out.println("Task # " + pos);
                    System.out.println("Begin: " + rangeA + " End: " + rangeB);

                    rangeA = rangeB;

                } else {

                    rangeA++;

                    if (pos == numThreads - 1 && (rangeA + range < limit)) {

                        rangeB = limit;
                        tasks[pos] = new DivisorConc(rangeA, rangeB, num, pos);
                        System.out.println("Task # " + pos);
                        System.out.println("Begin: " + rangeA + " End: " + rangeB);

                    } else {
                        rangeB += range;
                        tasks[pos] = new DivisorConc(rangeA, rangeB, num, pos);

                        System.out.println("Task # " + pos);
                        System.out.println("Begin: " + rangeA + " End: " + rangeB);
                        rangeA = rangeB;
                    }
                }

                pos++;
            }

        }

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread();
            threads[i].setPriority(Thread.MAX_PRIORITY);
            threads[i] = tasks[i].start();

        }

        //Espera a que termine cada una de las tareas
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        long numOfPosDivisors = 0;

        for (int i = 0; i < tasks.length; i++) {
            numOfPosDivisors += tasks[i].getCount();
        }

        return numOfPosDivisors;

    }

    @Override
    public void run() {

        for (long k = from; k <= to; k++) {

            //  System.out.println("Soy: "+ getCOD() + " --> voy en: "+ k);
            if (this.num % k == 0) {
                this.count++;
               System.out.println("numero: "+num+" divisor: "+k);
            }
        }
    }

    public Thread start() {

        if (theThread == null) {
            this.theThread = new Thread(this);
            theThread.start();
        }
        return theThread;

    }

    public int getCount() {
        return count;
    }

    public int getCOD() {
        return COD;
    }

    //Buscador de rangos
    public static long searchRange(long num, int numThread) {

        int range = 2;
        float numDiv = numDiv = (float) num / range;

        while (numDiv > numThread) {
            range++;
            numDiv = (float) num / range;
        }

        if (num % range > 0) {
            range--;
        }

        return range;
    }

}
