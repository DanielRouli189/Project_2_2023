package modelTesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestGenerator implements Runnable {
    
    /** A Random number generator */
    private static Random RNG = new Random();

    /** A TestStructure object that contains performance information about the test. */
    private TestStructure testResults;

    /** The number of repetitions to make for random searches */
    private int counts;

    /** The number of insertions to be performed*/
    private int dataSize;

    /* A list to hold the performance metrics of the file structures. */
    private static List<TestStructure> testStructureList = new ArrayList<>();


    public TestGenerator(int dataSize, int counts){
        this.counts = counts;
        testResults = new TestStructure(0, 0, 0, 0);
    }

    @Override
    public void run() {

    }


    public synchronized float randomSearchKD() {
        
        return 0;
    }





}
