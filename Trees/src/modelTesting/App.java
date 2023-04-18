package modelTesting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.CsvWriter;
import utils.Utils;


/**
 * The {@code App} class is the main class of this application, responsible for
 * generating tests for the kd-Tree and PR-QuadTree data structures.
 * 
 * @author nrouli
 * @since 2023-04
 * 
 */
public class App {
    
    /* The Singleton instance of the App class. */
    private static App app = null;

    /* The list of TestGenerator objects used to generate the test files. */
    private List<TestGenerator> generators;

    /* The list of threads that run the generators */
    private List<Thread> threads;

    /**
     * Constructs A new App Object.
     */
    public App() {
        generators = new ArrayList<>();
        threads = new ArrayList<>();
    }

    /**
     * Get the singleton instance of the app class. If the instance has not yet been
     * created, it will be created and returned.
     * @return The singleton instance of the App class.
     */
    public static App getInstance() {
        return (app == null) ? new App() : app;
    }

    /**
     * Clears the generators and threads lists
     */
    public void resetThreads() {
        generators.clear();
        threads.clear();
        TestGenerator.getTestStructureList().clear();
    }

    /**
     * The main method of the {@code App} class, which generates tests that
     * measure the average depth for a search in a kd-Tree and a PR-QuadTree
     * that contains {@link Config#TEST_VALUES M} elements
     * 
     * @param args The command-line arguments to the application.
     * @throws InterruptedException If one of the threads is interrupted while waiting
     *                              for another thread to finish.
     *                          
     */
    public static void main(String[] args) throws InterruptedException {
        app = App.getInstance();
        app.createTests();

        try{
            CsvWriter.writeCSV(TestGenerator.getTestStructureList());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a bunch of threads that each make a new kd-Tree and PR-QuadTree with
     * {@link Config#TEST_VALUES M} elements and perform random searches on them.
     * Once the threads have finished running, the results of the tests are printed.
     * 
     * @throws InterruptedException
     */
    public synchronized void createTests() throws InterruptedException {
        resetThreads();
        // Creating a new TestGenerator and Thread for each value in the TEST_VALUES array.
        for(int i = 0; i < Config.TEST_VALUES.length; ++i) {
            generators.add(new TestGenerator(Config.TEST_VALUES[i], 400));
            threads.add(new Thread(generators.get(i)));
        }

        //Starting all the threads.
        for(int i=0; i < threads.size(); ++i)
            threads.get(i).start();
        
        // Waiting for the threads to finish before continuing
        for(int i = 0; i < threads.size(); ++i)
            threads.get(i).join();

        // Sorting the `TestStructure` list by the number of records.
        Collections.sort(TestGenerator.getTestStructureList(), (g1, g2) -> Utils.compare(g1.dataSize(), g2.dataSize()));

        System.out.println("\n\n"+"|| Data Size |"+"| Successful Searches k-d |"+ "| Failed Searches k-d |"+"| Successful Searches PR |"+ "| Failed Searches PR ||");
        for(int i = 0; i < threads.size(); i++) {
            System.out.printf("||%11d|"+"|%25.2f||%21.2f||%24.2f||%20.2f||\n", 
            TestGenerator.getTestStructureList().get(i).dataSize(),
            TestGenerator.getTestStructureList().get(i).successKD(), 
            TestGenerator.getTestStructureList().get(i).failKD(),
            TestGenerator.getTestStructureList().get(i).successPR(),
            TestGenerator.getTestStructureList().get(i).failPR());
        }
    }
}
