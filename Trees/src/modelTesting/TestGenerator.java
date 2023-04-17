package modelTesting;

import java.util.ArrayList;
import java.util.List;

import model.*;

public class TestGenerator implements Runnable  {

    /** A kd-Tree with {@code Double} coordinates (x,y) and {@code Integer} value (if any)*/
    private KdTree<Double, Integer> kd;

    /** A PR-QuadTree with {@code String} value (if any) */
    private PRQuadTree<String> pr;

    /** A TestStructure object that contains performance information about the test. */
    private TestStructure testResults;

    /** The number of repetitions to make for random searches */
    private int counts;

    /** The number of insertions to be performed*/
    private int dataSize;

    /* A list to hold the performance metrics of the file structures. */
    private static List<TestStructure> testStructureList = new ArrayList<>();

    /** The singleton instance of the {@code DataPool} class */
    private static DataPool dp = null;

    /** boolean value to verify that the dataPool is filled */
    private static boolean isFull = false;


    /**
     * COnstructs a TestGenerator object with the specified parameters.
     * @param dataSize the number of {@link Config#TEST_VALUES M} elements to
     *                 be inserted.
     * @param counts the number of random searches to be performed.
     */
    public TestGenerator(int dataSize, int counts){
        this.dataSize = dataSize;
        this.counts = counts;
        testResults = new TestStructure(dataSize, 0, 0, 0, 0);
        kd = new KdTree<>();
        pr = new PRQuadTree<>(Config.N_MIN, Config.N_MAX, Config.N_MIN, Config.N_MAX);
        dp = instantiateDataPool();

        if(!isFull) {
            dp.fill();
            makeFull();
        }  
    }

    /**
     * Get the singleton instance of the {@code DataPool} class. If the instance has not yet been
     * created, it will be created and returned.
     * @return The singleton instance of the DataPool class.
     */
    private DataPool instantiateDataPool() {
        return dp == null ?  new DataPool(Config.TEST_VALUES[Config.TEST_VALUES.length - 1]) : dp;
    }

    /**
     * A flag to verify that the dataPool has been filled.
     * @return true
     */
    public static synchronized boolean makeFull() {
        isFull = true;
        return isFull;
    }

    /**
     * Run the test in a thread.
     */
    @Override
    public void run() {
        insertKD();
        insertPR();

        testStructureList.add(makeTest());
    }

    /**
     * insert {@link Config#TEST_VALUES M} elements from the dataPool
     * into the kd-Tree
     */
    public void insertKD() {
        for(int i = 0; i < dataSize; ++i)
            kd.insert(new Data<>(dp.getPool().get(i)[0], dp.getPool().get(i)[1]));
    }

    /**
     * insert {@link Config#TEST_VALUES M} elements from the dataPool
     * into the PR-QuadTree
     */
    public void insertPR() {
        for(int i = 0; i < dataSize; ++i)
            pr.insert(new PRData<>(dp.getPool().get(i)[0],dp.getPool().get(i)[1]));
    }

    /**
     * Creates a TestStructure object containing results of the search tests
     * on the Trees.
     * 
     * @return the TestStructure object.
     */
    public TestStructure makeTest() {
        float successKD = successSearchKD();
        float failKD = failSearchKD();
        float successPR = successSearchPR();
        float failPR = failSearchPR();

        testResults = new TestStructure(dataSize, successKD, failKD, successPR, failPR);
        return testResults;
    }

    /**
     * Returns the average depth for a search of a key that already exists
     * in the kd-Tree.
     * 
     * @return average depth in the tree for a search.
     */
    public synchronized float successSearchKD() {
        float result = 0;
        int i = DataPool.RNG.nextInt(Config.N_MAX - counts);
        int bound = i + counts;
        for(int j = i; j < bound; ++j)
            result += kd.search(new Data<>(dp.getPool().get(j)[0], dp.getPool().get(j)[1]));

        return result/counts;
    }

    /**
     * Returns the average depth for a search of a key that is not
     * in the kd-Tree.
     * 
     * @return average depth in the tree for a search.
     */
    public synchronized float failSearchKD() {
        float result = 0;
        int i = 0;
        while(i < counts){
            double x = DataPool.RNG.nextInt(Config.N_MAX);
            double y = DataPool.RNG.nextInt(Config.N_MAX);
            if(!kd.find(new Data<>(x, y))) {
                ++i;
                result += kd.search(new Data<>(x, y));
            }
        }

        return result/counts;
    }

    /**
     * Returns the average depth for a search of a key that already exists
     * in the PR-QuadTree.
     * 
     * @return average depth in the tree for a search.
     */
    public synchronized float successSearchPR() {
        float result = 0;
        int i = DataPool.RNG.nextInt(Config.N_MAX - counts - 1);
        int bound = i + counts;
        for(int j = i; j < bound; ++j)
            result += pr.find(new PRData<>(dp.getPool().get(j)[0], dp.getPool().get(j)[1]));

        return result/counts;
    }

    /**
     * Returns the average depth for a search of a key that is not
     * in the PR-QuadTree.
     * 
     * @return average depth in the tree for a search.
     */
    public synchronized float failSearchPR() {
        float result = 0;
        int i = 0;
        while(i < counts){
            double x = DataPool.RNG.nextInt(Config.N_MAX);
            double y = DataPool.RNG.nextInt(Config.N_MAX);
            if(!pr.getRoot().search(new PRData<>(x, y))) {
                ++i;
                result += pr.find(new PRData<>(x, y));
            }
        }

        return result/counts;
    }

    /*=================Getters - Setters=================*/
    public KdTree<Double, Integer> getKd() { return kd; }

    public void setKd(KdTree<Double, Integer> kd) { this.kd = kd; }

    public PRQuadTree<String> getPr() { return pr; }

    public void setPr(PRQuadTree<String> pr) { this.pr = pr; }

    public TestStructure getTestResults() { return testResults; }

    public void setTestResults(TestStructure testResults) { this.testResults = testResults;}

    public int getCounts() { return counts; }

    public void setCounts(int counts) { this.counts = counts; }

    public int getDataSize() { return dataSize; }

    public void setDataSize(int dataSize) { this.dataSize = dataSize; }

    public static List<TestStructure> getTestStructureList() { return testStructureList; }

    public static void setTestStructureList(List<TestStructure> testStructureList) {
        TestGenerator.testStructureList = testStructureList;
    }
    
    public DataPool getDp() { return dp; }
}
