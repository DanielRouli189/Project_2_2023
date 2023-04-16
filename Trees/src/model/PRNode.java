package model;


public class PRNode<V> {
    
    /** The boundaries of the node */
    private double xMin;
    private double yMin;
    private double xMax;
    private double yMax;

    /** outer left child of the node */
    private PRNode<V> nw;
    /** inner left child of the node */
    private PRNode<V> ne;
    /** inner right child of the node */
    private PRNode<V> sw;
    /** outer right child of the node */
    private PRNode<V> se;
    
    private PRData<V> data;

    /** Leaf node Constructor */
    public PRNode(PRData<V> data){
        this.data = data;
    }

    /** Child node Constructor */
    public PRNode(double xMin, double yMin, double xMax, double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    /** 
     * Determine the nature of the node. If it is a leaf
     * it does not have any children. Otherwise, it does
     * not contain a value and has exactly four.
     */
    public boolean isLeaf() {
        return nw == null && ne == null && sw == null && se == null;
    }

    public void insert(PRData<V> key) {
        if(!hasData() && isLeaf())
            data = new PRData<>(key.x(), key.y(), key.getValue());

        double xMid = (xMin + xMax)/2;
        double yMid = (yMin + yMax)/2;
        if(key.x() < xMid && key.y() < yMid)
            sw = insert(key, sw, this.xMin, this.yMin, xMid, yMid);
        else if(key.x() >= xMid && key.y() < yMid)
            se = insert(key, se, xMid, this.yMin, this.xMax, yMid);
        else if(key.x() < xMid && key.y() >= yMid)
            nw = insert(key, nw, this.xMin, yMid, xMid, this.yMax);
        else
            ne = insert(key, ne, xMid, yMid, this.xMax, this.yMax);   
    }

    public PRNode<V> insert(PRData<V> key, PRNode<V> root, double xMin, double yMin, double xMax, double yMax){
        if(root == null)
            root = new PRNode<>(xMin, yMin, xMax, yMax);
        root.insert(key);

        return root;
    }

    public PRNode<V> getNW() {
        return nw;
    }

    public void setNW(PRNode<V> nw) {
        this.nw = nw;
    }

    public PRNode<V> getNE() {
        return ne;
    }

    public void setNE(PRNode<V> ne) {
        this.ne = ne;
    }

    public PRNode<V> getSW() {
        return sw;
    }

    public void setSW(PRNode<V> sw) {
        this.sw = sw;
    }

    public PRNode<V> getSE() {
        return se;
    }

    public void setSE(PRNode<V> se) {
        this.se = se;
    }

    public boolean hasData() {
        return data != null;
    }

    public double getxMin() {
        return xMin;
    }

    public void setxMin(double xMin) {
        this.xMin = xMin;
    }

    public double getyMin() {
        return yMin;
    }

    public void setyMin(double yMin) {
        this.yMin = yMin;
    }

    public double getxMax() {
        return xMax;
    }

    public void setxMax(double xMax) {
        this.xMax = xMax;
    }

    public double getyMax() {
        return yMax;
    }

    public void setyMax(double yMax) {
        this.yMax = yMax;
    }
}
