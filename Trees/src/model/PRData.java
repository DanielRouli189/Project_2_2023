package model;

public class PRData<V> {

    /** The x-coordinate of the data */
    private double x;

    /** The y-coordinate of the data */
    private double y;

    /** The Value associated with the data */
    private V value;

    public PRData(double x, double y) {
        this.x = x;
        this.y = y;
        this.value = null;
    }

    public PRData(double x, double y, V value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    /*=================Getters - Setters=================*/
    public double x() { return x; }
    
    public void setX(double x) { this.x = x; }
    
    public double y() { return y; }
    
    public void setY(double y) { this.y = y; }
    
    public V getValue() { return value; }
    
    public void setValue(V value) { this.value = value; }
}
