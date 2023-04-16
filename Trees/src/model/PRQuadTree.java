package model;

public class PRQuadTree<V> {
    
    private PRNode<V> root;
    
    private double xMin;
    
    private double xMax;
    
    private double yMin;
    
    private double yMax;

    public PRQuadTree(double xMin, double xMax, double yMin, double yMax) {
        this.root = new PRNode<>(xMin, yMin, xMax, yMax);
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public void insert(PRData<V> key){
        if(key == null)
            throw new IllegalArgumentException("data-point value is null");

        if(key.x() < xMin || key.x() > xMax || key.y() < yMin || key.y() > yMax)
            throw new IllegalArgumentException("Point is out of the tree bounds");

        root.insert(key);
    }




}
