package model;

public class PRQuadTree<V> {
    
    private PRNode<V> root;
    
    /** Boundaries of the PR-QuadTree */
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
            throw new NullPointerException("data-point value is null");

        if(key.x() < xMin || key.x() > xMax || key.y() < yMin || key.y() > yMax)
            throw new IllegalArgumentException("Point is out of the tree bounds");

        root.insert(key);
    }

    public int find(PRData<V> key) {
        if(key == null)
            throw new NullPointerException("data-point value is null");

        if(key.x() < xMin || key.x() > xMax || key.y() < yMin || key.y() > yMax)
            throw new IllegalArgumentException("Point is out of the tree bounds");

        return this.root.find(key);
    }

    public PRNode<V> getRoot() { return root; }

    public void setRoot(PRNode<V> root) { this.root = root; }

    public double getxMin() { return xMin; }

    public void setxMin(double xMin) { this.xMin = xMin; }

    public double getxMax() { return xMax; }

    public void setxMax(double xMax) { this.xMax = xMax; }

    public double getyMin() { return yMin; }

    public void setyMin(double yMin) { this.yMin = yMin; }

    public double getyMax() { return yMax; }

    public void setyMax(double yMax) { this.yMax = yMax; }

    
}
