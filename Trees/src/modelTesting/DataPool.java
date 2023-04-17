package modelTesting;

import java.util.concurrent.ConcurrentHashMap;

public class DataPool<K extends Comparable<? super K>, V> {
    
    /** The amount of data the data pool contains */
    private int size;
    
    private ConcurrentHashMap<K,V> pool;

    public DataPool(int size) {

    }
    
}
