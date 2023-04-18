package model;

public class PRQuadTree<V> {
    
    /** The root of the PR-QuadTree */
    private PRNode<V> root;
    
    /** Boundaries of the PR-QuadTree */
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    /**
     * Creates a new PRQuadTree object with given parameters.
     * @param xMin x-lower bound.
     * @param xMax x-upper bound.
     * @param yMin y-lower bound.
     * @param yMax y-upper bound.
     */
    public PRQuadTree(double xMin, double xMax, double yMin, double yMax) {
        this.root = new PRNode<>(xMin, yMin, xMax, yMax);
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    /**
     * insert a data-point in the PR-QuadTree.
     * This is a wrapper function of {@link PRNode#insert(PRData) insert}
     * 
     * @param key the data-point to be inserted.
     * @throws NullPointerException if the key is null.
     * @throws IllegalArgumentException if the point is outside the boundaries of the tree.
     * 
     */
    public void insert(PRData<V> key){
        if(key == null)
            throw new NullPointerException("data-point value is null");

        if(key.x() < xMin || key.x() > xMax || key.y() < yMin || key.y() > yMax)
            throw new IllegalArgumentException("Point is out of the tree bounds");

        root.insert(key);
    }

    /**
     * Find a data-point in the PR-QuadTree.
     * This is a wrapper function of {@link PRNode#find(PRData) find}.
     * 
     * @param key the data point to be found.
     * @return the depth in which it was found (if it was).
     * @throws NullPointerException if the key is null.
     * @throws IllegalArgumentException if the point is outside the boundaries of the tree.
     */
    public int find(PRData<V> key) {
        if(key == null)
            throw new NullPointerException("data-point value is null");

        if(key.x() < xMin || key.x() > xMax || key.y() < yMin || key.y() > yMax)
            throw new IllegalArgumentException("Point is out of the tree bounds");

        return this.root.find(key);
    }

    /**
     * used for unit testing purposes.
     * 
     * @param key
     * @return
     */
    public boolean findTest(PRData<V> key) {
        return this.root.search(key);
    }

    /*=================Getters - Setters=================*/
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
