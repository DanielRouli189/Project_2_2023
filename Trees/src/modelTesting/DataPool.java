package modelTesting;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataPool {
    
    /** A Random number generator */
    protected static Random RNG = new Random();

    /** The amount of data the data pool contains */
    private int size;

    /** Save the data in a List */
    private List<Double[]> pool;

    /**
     * Creates a new {@code DataPool} object with given parameters.
     * @param size the size of the data pool.
     */
    public DataPool(int size) {
        this.size = size;
        this.pool = new ArrayList<>(size);
    }

    /**
     * fill the list with random numbers, uniformly distributed in range [{@link Config#N_MIN min}, {@link Config#N_MAX max}).
     */
    public void fill() {

        for(int i = 0; i < size; ++i) {
            Double x = RNG.nextDouble() * (Config.N_MAX - Config.N_MIN);
            Double y = RNG.nextDouble() * (Config.N_MAX - Config.N_MIN);
            Double[] key = {x,y};
            pool.add(key);
        }
    }

    /**
     * fill the list with random numbers, normally distributed in range [{@link Config#N_MIN min}, {@link Config#N_MAX max}).
     */
    public void fillGaussian() {

        for(int i = 0; i < size; ++i) {
            Double x = Math.min(Math.abs(RNG.nextGaussian() * (Config.N_MAX - Config.N_MIN)/2), Config.N_MAX - 1);
            Double y = Math.min(Math.abs(RNG.nextGaussian() * (Config.N_MAX - Config.N_MIN)/2), Config.N_MAX - 1);
            Double[] key = {x,y};
            pool.add(key);
        }
    }

    /*=================Getters - Setters=================*/
    public int getSize() { return size; }

    public void setSize(int size) { this.size = size; }

    public List<Double[]> getPool() { return pool; }

    public void setPool(List<Double[]> pool) { this.pool = pool; }

    
    
}
