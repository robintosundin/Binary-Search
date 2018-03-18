import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Performs full unit test on IntSet by a random number of operations.
 * @author Chalmers
 */
public class Test {
    static Random randgen = new Random(686585037);

    static int nopdone = 0;
    static int lastnopdone = -1;
    static int noperation = 0;
    static StringBuilder log = new StringBuilder();

    static void showException(Exception e, StringBuilder log) {
        System.out.println("The operation on the last line of the following code causes an exception:\n");
        System.out.print(log.toString());
        System.out.println("\nThe exception is: " + e.toString());
        System.exit(1);
    }
    
    static void runTest(Class<IntSet> intSetClass, int noperation, int interval) {
        log = new StringBuilder();
        
        log.append("IntSet set = new " + intSetClass.getName() + "();\n");
        IntSet set = null;
        try {
            set = intSetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassCastException e) {
            System.out.println("Could not instantiate specified class, " + intSetClass.getName() + ".");
            System.out.println("Exception: " + e.toString());
            System.exit(1);
        }
        Set<Integer> refset = new HashSet<Integer>();
        for (int j = 0; j < noperation; j++) {
            int whichop = randgen.nextInt(3);
            switch (whichop) {
            case 0: {  // add
                int element = randgen.nextInt(interval);
                log.append("set.add(" + element + ");\n");
                try {
                    set.add(element);
                } catch (Exception e) {
                    showException(e, log);
                }
                refset.add(element);
            } break;
            case 1: {  // member
                int element = randgen.nextInt(interval);
                log.append("set.contains(" + element + ");");
                boolean res = false;
                try {
                    res = set.contains(element);
                } catch (Exception e) {
                    showException(e, log);
                }
                log.append("  // result: " + res + "\n");
                boolean refres = refset.contains(element);
                if (res != refres) {
                    System.out.println("The result on the last line of the following code is incorrect:\n");
                    System.out.print(log.toString());
                    System.out.println(" \nThe set should add this point contain the following elements:");
                    System.out.println(refset.toString());
                    System.exit(1);
                }
            } break;
            case 2: {  // remove
                int element = randgen.nextInt(interval);
                log.append("set.remove(" + element + ");\n");
                try {
                    set.remove(element);
                } catch (Exception e) {
                    showException(e, log);
                }
                refset.remove(element);
            } break;
            }
            nopdone++;
        }
        
    }
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        if (args.length == 0 || args.length > 2) {
            System.out.println("The program accepts one or two arguments.");
            System.out.println("The first argument is the name of the class implementing IntSet that should be tested.");
            System.out.println("If there is a second argument this should be a positive number specifying the number of seconds to run the test.");
            System.exit(1);
        }
        
        Class<IntSet> intSetClass = null;
        try {
            intSetClass = (Class<IntSet>)Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            System.out.println("The specified class, " + args[0] + ", cannot be found.");
            System.exit(1);
        }

        int nrepetition = 5;
        
        int n = 5;
        if (args.length == 2) {
            try {
                n = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("The given argument is not a valid number.");
                System.exit(1);
            }
        }
        final int nsec = n;

        if (args.length == 1) {
            System.out.println("The test will run for " + nsec + " seconds or until a bug is found. (The number of seconds can be specified as an argument to the program.)");
        }
        
        new Thread(() -> {
                for (int secs = 0; secs < nsec; secs++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                    if (nopdone == lastnopdone) {
                        System.out.println("No new operation completed during the last second.");
                        System.out.println("Your program seems to loop (or be very slow) at the operation on the last line of the following code:\n");
                        System.out.print(log.toString());
                        System.exit(1);
                    }
                    lastnopdone = nopdone;
                    System.out.println(nopdone + " operations executed. (No bugs found so far.)");
                }
                System.out.println("No bugs found in " + nsec + " seconds.");
                System.exit(0);
            }
        ).start();
        
        for (int iter = 1;; iter++) {
            nrepetition *= 2;
            noperation = iter;
            int interval = iter;
            for (int i = 0; i < nrepetition; i++) {
                runTest(intSetClass, noperation, interval);
            }
        }        
    
    }
}