package modelTesting;

public class Config {
    
    /** The array of test values to use when generating tests. */
    protected static final int[] TEST_VALUES = {20, 50 ,100, 200, 500, 1000, 2000, 5000, 10000, 30000, 50000, 70000, 100000};

    /** maximum value of the dataset*/
    public static final int N_MAX = 65536;

    /** minimum value of the dataset */
    public static final int N_MIN = 0;


    private Config() {
        throw new IllegalStateException("Configuration class");
    }

}
