package model;

public class Data<K extends Comparable<K>, V extends Comparable<V>> {

    /** x-value of the data */
    private K x;

    /** y-value of the data */
    private V y;

    /** The depth of the data in the tree */
    private int depth;

    public Data(K x, V y) {
        this.x = x;
        this.y = y;
        depth = 0;
    }

    public K x() {
        return x;
    }

    public V y() {
        return y;
    }

    public int depth() {
        return depth;
    }

    public void setX(K x) {
        this.x = x;
    }

    public void setY(V y) {
        this.y = y;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int compare(Data<K,V> o) {
        return (depth % 2 == 0) ? this.x.compareTo(o.x()) : this.y.compareTo(o.y());
    }

    public int equals(Data<K,V> o) {
        return this.x == o.x() && this.y == o.y() ? 0 : 1;
    }

}
