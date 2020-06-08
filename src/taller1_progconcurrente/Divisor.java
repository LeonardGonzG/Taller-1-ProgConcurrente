package taller1_progconcurrente;

/**
 *
 * @author ceaufres
 */
public class Divisor {

    public static long numOfPosDivisors(long num, long from, long to) {
        int inc = 1;
        long count = 0;

        if (num % 2 == 1) { // Num is odd
            inc = 2;
            if (from % 2 == 0) { // From is even 
                from++;
            }
        }

        for (long k = from; k <= to; k += inc) {
            if (num % k == 0) {
                count++;
            }
        }

        return count;
    }

    public static long numOfPosDivisors(long num) {
        return 2 + numOfPosDivisors(num, 2, num / 2);
    }

}
