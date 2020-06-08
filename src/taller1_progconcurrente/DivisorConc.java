package taller1_progconcurrente;
/**
 *
 * @author LeonardoGonz
 */
public class DivisorConc {

    ///Función encargada 
    public static long numOfPosDivisors(long num, int numThreads) {

        if (num > 1 && numThreads > 1) {
            return DivisorConc.makeThread(num, numThreads) + 1;
        }
        return 1;

    }

    /*Programa de forma concurrente y distruida para hallar el número de divisores del número ingesado*/
    private static long makeThread(long num, int numThreads) {

        long range = searchRange(num, numThreads);

        Task[] tasks = tasksThread(range, numThreads, num);

        for (int i = 0; i < tasks.length; i++) {
            tasks[i].start();
        }

        long numOfPosDivisors = 0;

        //Espera a que termine cada una de las tareas
        for (Task task : tasks) {
            try {
                task.getTheThread().join();
                //Suma la cantidad de divisores encontrados de cada hilo
                numOfPosDivisors += task.getCount();
            } catch (InterruptedException ex) {

            }

        }

        return numOfPosDivisors;

    }

    //Asigna a cada hilo de forma distribuida los calculos a realizar cada uno
    public static Task[] tasksThread(long rangeFragment, int numThreads, long num) {

    
        long numRange = num / 2L / rangeFragment;

        if (rangeFragment * numRange < num / 2L) {
            numRange++;
        }

        Task[] tasks = new Task[numThreads];

        long begin = 1L;
        long end = rangeFragment;

        for (int i = 0; i < tasks.length; i++) {

            tasks[i] = new Task(begin, end, num, (i + 1));
            begin += rangeFragment;
            end += rangeFragment;

            if (end > num / 2L) {
                end = num / 2L;
            }
        }
        return tasks;
    }

    //Se encarga de calcular el rango de tareas para cada hilo
    public static long searchRange(long num, int numThread) {
        long range = num / 2L / numThread;
        return range;
    }

}
