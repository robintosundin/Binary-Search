/**
 * A benchmark class for testing the performance of an abstract data type.
 * @author Chalmers
 */
public class Benchmark {
    /**
     * Contains the algorithms for benchmarking.
     * @param intSetClass Class generic for the ADT to benchmark.
     * @param size how large data sets to test for.
     * @param minSeconds minimum time in benchmark to look for elements in supplied ADT.
     * @return the execution time for performing contain-tests for ADT with properties supplied as parameters.
     */
    static double runBenchmark(Class<IntSet> intSetClass, int size, int minSeconds) {
        IntSet set = null;
        try {
            set = intSetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassCastException e) {
            System.out.println("Could not instantiate specified class, " + intSetClass.getName() + ".");
            System.out.println("Exception: " + e.toString());
            System.exit(1);
        }

        long starttime = System.currentTimeMillis();
        long nowtime = starttime;
        long n = 1;

        int i;
        /*
        Fill supplied set by adding to the ADT with an upper limit of 5000ms run time.
         */
        for (i = 0; i < size && nowtime - starttime < 5000;) {
            for (int j = 0; j < n && i < size; j++, i++) { 
                set.add(1 + i * 2);
            }
            nowtime = System.currentTimeMillis();
            n *= 2;
        }
        if (i < size) {  // True means above loop could not fill set within 5000ms so tries adding from other dir.
            try {
                set = intSetClass.newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassCastException e) {
                System.out.println("Could not instantiate specified class, " + intSetClass.getName() + ".");
                System.out.println("Exception: " + e.toString());
                System.exit(1);
            }
            for (i = size - 1; i >= 0; i--) {
                set.add(1 + i * 2);
            }            
        }
        
        long nop = 0; // Keep track of seconds passed, one iteration is 1000ms; in order to return proper time.
        starttime = System.currentTimeMillis();
        nowtime = starttime;
        n = 1;
        while (nowtime - starttime < 1000 * minSeconds) {
            int j = 0;
            for (long k = 0; k < n; k++) {
                set.contains(j);
                j++;
                if (j > size * 2) j = 0;
                nop++;
            }
            nowtime = System.currentTimeMillis();
            n *= 2;
        }
        return (nowtime - starttime) / (1000.0 * nop);
    }
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        if (args.length == 0 || args.length > 1) {
            System.out.println("The program needs one argument, the name of the class implementing IntSet that should be banchmarked.");
            System.exit(1);
        }
        
        Class<IntSet> intSetClass = null;
        try {
            intSetClass = (Class<IntSet>)Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            System.out.println("The specified class, " + args[0] + ", cannot be found.");
            System.exit(1);
        }

        new Thread(() -> {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println("A minute has passed. Terminating.");
            System.exit(0);
        }).start();

        
        System.out.println("Average execution time of method contains for different set sizes\n");
        System.out.println("set size  execution time (nanoseconds)");
        for (int size = 10; size <= 10000000; size *= 10) {
            double time = runBenchmark(intSetClass, size, 2);
            System.out.format("%8d  %14.1f\n", size, time * 1e9);
        }
        System.exit(0);
    }
}