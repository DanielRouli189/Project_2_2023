package model;

/**
 * The {@code Data} class represents a generic 2D data-point in a kd-Tree.
 * It contains the coordinates (x,y) which are two comparable values, the
 * depth of the data in the kd-Tree used to determine the discriminator and
 * also the data associated with the data point.
 * 
 */
public class Data<K extends Comparable<? super K>, V> {

    /** x-value of the data */
    private K x;

    /** y-value of the data */
    private K y;

    /** The value associated with the data-point */
    private V value;

    /** The depth of the data in the tree */
    private int depth;

    public Data(K x, K y) {
        this.x = x;
        this.y = y;
        depth = 0;
    }

    public Data(K x, K y, V value) {
        this.x = x;
        this.y = y;
        depth = 0;
        this.value = value;
    }

    /**
     * Compare two data-points based on the current discriminant.
     * 
     * @param o the data to be compared
     * @return the result of the {@link java.lang.Comparable#compareTo(Object o) compareTo} function.
     */
    public int compare(Data<K,V> o) {
        return (depth % 2 == 0) ? this.x.compareTo(o.x()) : this.y.compareTo(o.y());
    }

    /**
     * checks if the data-points are equal.
     * 
     * @param o the data-point to be compared
     * @return true if the coordinates are equal, false, otherwise.
     */
    public boolean eq(Data<K,V> o) {
        return this.x == o.x() && this.y == o.y();
    }

    /*=================Getters - Setters=================*/
    public K x() { return x; }

    public K y() { return y; }

    public V value() { return value; }

    public int depth() { return depth; }

    public void setX(K x) {this.x = x; }

    public void setY(K y) { this.y = y; }

    public void setDepth(int depth) { this.depth = depth; }

}
