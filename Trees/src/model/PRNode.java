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
    /** The data contained in the node */
    private PRData<V> data;
    /** The depth of the node in the tree */
    private static int depth = 0;

    /** Leaf node Constructor */
    public PRNode(PRData<V> data) {
        this.data = data;
    }

    /** Internal node Constructor */
    public PRNode(double xMin, double yMin, double xMax, double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    /** 
     * Determine the nature of the node. If it is a leaf
     * it does not have any children. Otherwise, it does
     * not contain a value and has exactly four children.
     */
    public boolean isLeaf() {
        return nw == null && ne == null && sw == null && se == null;
    }

    /**
     * Inserts a new data-point into the PR-QuadTree.
     * 
     * @param key The data-point to be inserted.
     * @return 0 or 1 on success, -1 if the given key
     * already exists in the tree.
     */
    public int insert(PRData<V> key) {
        if(!hasData() && isLeaf()){
            data = new PRData<>(key.x(), key.y(), key.getValue());
            return 1;
        }

        if(hasData() && isLeaf() && this.getData().x() == key.x() && this.getData().y() == key.y())
            return -1;

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
        
        if(hasData() && !isLeaf()) {
            PRData<V> tmp = this.data;
            this.data = null;
            insert(tmp);
        }

        return 0;
    }

    /**
     * insert helper function. It creates a new node with given boundaries
     * and inserts the key.
     * 
     * @param key the data to be inserted
     * @param root the root of the tree
     * @param xMin x-lower bound
     * @param yMin y-lower bound
     * @param xMax x-upper bound
     * @param yMax y-upper bound
     * @return the node that was inserted.
     */
    private PRNode<V> insert(PRData<V> key, PRNode<V> root, double xMin, double yMin, double xMax, double yMax){
        if(root == null)
            root = new PRNode<>(xMin, yMin, xMax, yMax);
        
        root.insert(key);
        return root;
    }

    /**
     * wrapper function of {@link PRNode#search(PRData) search}.
     * @param key the data to be inserted.
     * @return the depth reached in the tree for the search. 
     */
    public synchronized int find(PRData<V> key) {
        depth = 0;
        search(key);
        return depth;
    }

    /**
     * Performs a searching algorithm on the PR-QuadTree.
     * 
     * @param key the data to be searched.
     * @return true if the key is found, false, otherwise.
     */
    public synchronized boolean search(PRData<V> key) {
        if(this.data != null)
            return this.data.x() == key.x() && this.data.y() == key.y();

        double xMid = (xMin + xMax)/2;
        double yMid = (yMin + yMax)/2;
        
        if(key.x() < xMid && key.y() < yMid)
            return (this.sw != null && this.sw.search(key)) & ++depth > 0;
        else if(key.x() >= xMid && key.y() < yMid)
            return (this.se != null && this.se.search(key)) & ++depth > 0;
        else if(key.x() < xMid && key.y() >= yMid)
            return (this.nw != null && this.nw.search(key)) & ++depth > 0;
        else
            return (this.ne != null && this.ne.search(key)) & ++depth > 0;
    }

    /**
     * Performs a searching algorithm on the PR-QuadTree.
     * 
     * @param key the data to be searched.
     * @return true if the key is found, false, otherwise.
     */
    public synchronized PRNode<V> search(PRData<V> key, PRNode<V> root) {
        if(root == null & ++depth > 0)
            return root;

        double xMid = (xMin + xMax)/2;
        double yMid = (yMin + yMax)/2;
        if(key.x() < xMid && key.y() < yMid && this.sw != null)
            return this.sw.search(key, this.sw);
        else if(key.x() >= xMid && key.y() < yMid && this.sw != null)
            return this.se.search(key, this.se);
        else if(key.x() < xMid && key.y() >= yMid && this.nw != null)
            return this.nw.search(key, this.nw);
        else if(this.ne != null)
            return this.ne.search(key, this.nw);

        return root;
    }


    /*=================Getters - Setters=================*/
    public PRNode<V> getNW() { return nw; }

    public void setNW(PRNode<V> nw) { this.nw = nw; }

    public PRNode<V> getNE() { return ne; }

    public void setNE(PRNode<V> ne) { this.ne = ne; }

    public PRNode<V> getSW() { return sw; }

    public void setSW(PRNode<V> sw) { this.sw = sw; }

    public PRNode<V> getSE() { return se; }

    public void setSE(PRNode<V> se) { this.se = se; }

    public PRData<V> getData() { return data; }

    public static int getDepth() { return depth; }

    public boolean hasData() { return this.data != null; }

    public double getXMin() { return xMin; }

    public void setXMin(double xMin) { this.xMin = xMin; }

    public double getYMin() { return yMin; }

    public void setYMin(double yMin) { this.yMin = yMin; }

    public double getXMax() { return xMax; }

    public void setXMax(double xMax) { this.xMax = xMax; }

    public double getYMax() { return yMax; }

    public void setYMax(double yMax) { this.yMax = yMax; }
}
