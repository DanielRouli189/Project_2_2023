package modelTesting;

public class Config {
    
    /* The array of test values to use when generating tests. */
    public static final int[] TEST_VALUES = {200, 500, 1000, 10000, 30000, 50000, 70000, 100000};

    public static final double N_MAX = 12;

    public static final double N_MIN = 0;


    private Config(){
        throw new IllegalStateException("Configuration class");
    }
}
