package model;

public class PRData<V> {

    private double x;
    private double y;

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

    public double x() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    
    public double y() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }

}
