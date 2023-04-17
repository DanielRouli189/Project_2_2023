package modelTesting;

import java.util.ArrayList;
import java.util.List;

import model.*;

public class TestGenerator implements Runnable  {

    private KdTree<Double, Integer> kd;

    private PRQuadTree<String> pr;

    /** A TestStructure object that contains performance information about the test. */
    private TestStructure testResults;

    /** The number of repetitions to make for random searches */
    private int counts;

    /** The number of insertions to be performed*/
    private int dataSize;

    /* A list to hold the performance metrics of the file structures. */
    private static List<TestStructure> testStructureList = new ArrayList<>();

    private static DataPool dp = null;

    private static boolean isFull = false;

    public TestGenerator(int dataSize, int counts){
        this.dataSize = dataSize;
        this.counts = counts;
        testResults = new TestStructure(dataSize, 0, 0, 0, 0);
        kd = new KdTree<>();
        pr = new PRQuadTree<>(Config.N_MIN, Config.N_MAX, Config.N_MIN, Config.N_MAX);
        dp = instantiateDataPool();

        if(!isFull){
            dp.fill();
            makeFull();
        }  
    }

    private DataPool instantiateDataPool() {
        return dp == null ?  new DataPool(Config.TEST_VALUES[Config.TEST_VALUES.length - 1]) : dp;
    }

    public static synchronized boolean makeFull() {
        isFull = true;
        return isFull;
    }

    @Override
    public void run() {
        insertKD();
        insertPR();

        testStructureList.add(makeTest());
    }

    public void insertKD() {
        for(int i = 0; i < dataSize; ++i)
            kd.insert(new Data<>(dp.getPool().get(i)[0], dp.getPool().get(i)[1]));
    }

    public void insertPR() {
        for(int i = 0; i < dataSize; ++i)
            pr.insert(new PRData<>(dp.getPool().get(i)[0],dp.getPool().get(i)[1]));
    }


    public TestStructure makeTest() {
        float successKD = successSearchKD();
        float failKD = failSearchKD();
        float successPR = successSearchPR();
        float failPR = failSearchPR();

        testResults = new TestStructure(dataSize, successKD, failKD, successPR, failPR);
        return testResults;
    }

    public synchronized float successSearchKD() {
        float result = 0;
        int i = DataPool.RNG.nextInt(Config.N_MAX/2 - counts - 1);
        int bound = i + counts;
        for(int j = i; j < bound; ++j)
            result += kd.search(new Data<>(dp.getPool().get(j)[0], dp.getPool().get(j)[1]));

        return result/counts;
    }

    public synchronized float failSearchKD() {
        float result = 0;

        for(int i = 0; i < counts; ++i){
            double[] xy = DataPool.RNG.doubles((double) Config.N_MAX + 1,(double) 2*Config.N_MAX).limit(2).toArray();
            result += kd.search(new Data<>(xy[0], xy[1]));
        }

        return result/counts;
    }


    public synchronized float successSearchPR() {
        float result = 0;
        int i = DataPool.RNG.nextInt(Config.N_MAX - counts - 1);
        int bound = i + counts;
        for(int j = i; j < bound; ++j)
            result += pr.find(new PRData<>(dp.getPool().get(j)[0], dp.getPool().get(j)[1]));

        return result;
    }

    public synchronized float failSearchPR() {
        float result = 0;

        for(int i = 0; i < counts; ++i){
            double[] xy = DataPool.RNG.doubles((double) Config.N_MAX + 1,(double) 2*Config.N_MAX).limit(2).toArray();
            result += pr.find(new PRData<>(xy[0], xy[1]));
        }

        return result;
    }

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
