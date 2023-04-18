package modelTesting;

public class Config {
    
    /** The array of test values to use when generating tests. */
    public static final int[] TEST_VALUES = {200, 500, 800, 1000, 2000, 5000, 10000, 20000, 30000, 50000, 70000, 100000, 200000, 500000};

    /** maximum value of the dataset*/
    public static final int N_MAX = 65536;

    /** minimum value of the dataset */
    public static final int N_MIN = 0;

    private Config(){
        throw new IllegalStateException("Configuration class");
    }

}
