package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code KdTree} class represents a generic kd-Tree data structure 
 * that performs the operations of {@link KdTree#insert(Data) insertion}, 
 * {@link KdTree#search(Data) searching}, and {@link KdTree#remove(Data) deletion}.
 *  
 * @author nrouli
 * @since 2023-04
 */
public class KdTree<K extends Comparable<? super K>, V> {

    /** The root of the kd-Tree. */
    private Node<Data<K,V>> root;

    /** Creates a new instance of the {@code KdTree} class. */
    public KdTree() {
        this.root = null;
    }

   /**
    * Inserts a new node with a given key into a kd-Tree.
    * 
    * @param key The value to be inserted into the kd-Tree
    * @return always 0.
    * @throws NullPointerException if the given key is null
    */
    public int insert(Data<K,V> key) {
        if(key == null)
            throw new NullPointerException("data-point value is null");
        
        root = insert(key, root);
        return 0;
    }

    /**
     * This is a recursive function that inserts a new node with a given key into a kd-tree.
     * 
     * @param key The value to be inserted into the kd-tree.
     * @param root The root node of the kd-tree where the new key will be inserted.
     * @return The root of the kd-tree.
     */
    private Node<Data<K,V>> insert(Data<K,V> key, Node<Data<K,V>> root){
        if(root == null)
            return new Node<>(key);
        
        if(key.compare(root.data()) < 0) {
            key.setDepth( key.depth() + 1 );
            root.setLeft(insert(key, root.left()));
        }
        else {
            key.setDepth( key.depth() + 1 );
            root.setRight(insert(key, root.right()));
        }

        return root;
    }

    /**
     * Searches for a given data-point in the kd-Tree.
     * This is a wrapper function to the {@link model.KdTree#search(Data, Node) search}
     * function that performs a search algorithm on the kd-Tree
     * 
     * @param key The data-point to be searched for in the kd-Tree
     * @return The depth of the data-point in the kd-Tree.
     * @throws NullPointerException if the key value is null.
     */
    public int search(Data<K,V> key) {
        if(key == null)
            throw new NullPointerException("data-point value is null");
        
        key.setDepth(0);
        search(key, this.root);
        return key.depth();
    }

    /**
     * Performs a search algorithm similar to BST search on the kd-Tree, 
     * starting from the root of the tree, and recursively traversing it.
     * 
     * @param key the data-point to be found in the tree
     * @param root the root of the kd-Tree
     * @return the root.
     */
    public Node<Data<K,V>> search(Data<K,V> key, Node<Data<K,V>> root){
        if(root == null)
            return root;

        if(key.eq(root.data()))
            return root;
        
        if(key.compare(root.data()) >= 0) {
            key.setDepth(key.depth()+1);
            return search(key, root.right());
        }
        else if(key.compare(root.data()) < 0 ) {
            key.setDepth(key.depth()+1);
            return search(key, root.left());
        }
        
        return root;
    }

    /**
     * Searches for a given data-point in the kd-Tree.
     * This is a wrapper function to the {@link model.KdTree#find(Data, Node) find}
     * function that performs a search algorithm on the kd-Tree
     * 
     * @param key The data-point to be searched for in the kd-Tree
     * @return The depth of the data-point in the kd-Tree.
     * @throws NullPointerException if the key value is null.
     */
    public boolean find(Data<K,V> key) {
        if(key == null)
            throw new NullPointerException("data-point is null");

        return find(key, this.root);
    }
    
    protected boolean find(Data<K,V> key, Node<Data<K,V>> root) {
        if(root == null) return false;
        
        if(key.eq(root.data())) return true;

        if(key.compare(root.data()) >= 0) {
            key.setDepth(key.depth()+1);
            return find(key, root.right());
        }
        else if(key.compare(root.data()) < 0 ) {
            key.setDepth(key.depth()+1);
            return find(key, root.left());
        }

        return key.eq(root.data());
    }

    /**
     * This function checks if a given node's data falls within a specified rectangular region of x and y values.
     * 
     * @param lb The lower bound of the range to check for intersection.
     * @param ub The upper bound of the range to check for intersection.
     * @param node The node parameter is an instance of the Node class, which contains a Data object as
     * its data field.
     * @return True if the point lies within the open rectangular region specified, false otherwise.
     */
    public boolean intersects(Data<K,V> lb, Data<K,V> ub, Node<Data<K,V>> node) {
        return node.data().x().compareTo(lb.x()) > 0 && node.data().x().compareTo(ub.x()) < 0 
            && node.data().y().compareTo(lb.y()) > 0 && node.data().y().compareTo(ub.y()) < 0; 
    }

   /**
    * This function returns a list of nodes within a given range of data values, starting from the root
    * node. This is a wrapper function to the {@link model.KdTree#rangeQuery(Data, Data, Node) rangeQuery}
    * function.
    * 
    * @param lb The lower bound of the range query.
    * @param ub The upper bound of the range query.
    * @return A List of nodes that lie within the open rectangular region specified by the lower bound
    *         the upper bound respectively.
    */
    public List<Data<K,V>> rangeQuery(Data<K,V> lb, Data<K,V> ub) {
        return rangeQuery(lb, ub, this.root);
    }

    
    protected List<Data<K,V>> rangeQuery(Data<K,V> lb, Data<K,V> ub, Node<Data<K,V>> node) {
        List<Data<K,V>> points = new ArrayList<>();

        if(node == null) return points;

        if(intersects(lb, ub, node)) 
            points.add(node.data());
        
        points.addAll(rangeQuery(lb, ub, node.left()));
        points.addAll(rangeQuery(lb, ub, node.right()));

        return points;
    }


    /*=================Getters - Setters=================*/
    public Node<Data<K, V>> getRoot() { return root; }

    public void setRoot(Node<Data<K, V>> root) { this.root = root; }
    
    
}
