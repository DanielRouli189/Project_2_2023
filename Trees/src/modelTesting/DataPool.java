package modelTesting;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataPool {
    
    /** A Random number generator */
    protected static Random RNG = new Random();

    /** The amount of data the data pool contains */
    private int size;

    /** 
     * Save the data in a concurrent hash map to be accessed safely by 
     * different threads.
     * 
     */
    private List<Double[]> pool;

    public DataPool(int size) {
        this.size = size;
        this.pool = new ArrayList<>(size);
    }

    public void fill() {
        double[] xy = new double[2]; 
        for(int i = 0; i < size; ++i) {
            xy= RNG.doubles(Config.N_MIN, Config.N_MAX).limit(2).toArray();
            Double x = xy[0];
            Double y = xy[1];
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
