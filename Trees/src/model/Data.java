package model;

public class Data<K extends Comparable<K>, V extends Comparable<V>> {

    /** x-value of the data */
    private K x;
    
    /** y-value of the data */
    private V y;

    /** The depth of the data in the tree */
    private int depth;

    public Data(K x, V y){
        this.x = x;
        this.y = y;
    }

    public K x() {
        return x;
    }

    public V y() {
        return y;
    }

    public void setX(K x) {
        this.x = x;
    }

    public void setY(V y) {
        this.y = y;
    }

    public int compare(Data<K,V> o) {
        if(depth % 2 == 0)
            return this.x.compareTo(o.x());

        return this.y.compareTo(o.y());
    }

}
