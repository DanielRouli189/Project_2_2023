package model;

/**
 * The {@code KdTree} class represents a generic kd-Tree data structure 
 * that performs the operations of {@link KdTree#insert(Data) insertion}, 
 * {@link KdTree#search(Data) searching}, and {@link KdTree#remove(Data) deletion}.
 *  
 * 
 * @author nrouli
 * @since 2023-04
 */
public class KdTree<K extends Comparable<? super K>, V> {

    /** The root of the kdTree */
    private Node<Data<K,V>> root;

    /** Creates a new instance of the {@code KdTree} class. */
    public KdTree() {
        this.root = null;
    }

   /**
    * Inserts a new node with a given key into a kd-Tree.
    * 
    * @param key The value to be inserted into the kd-Tree
    * @return 
    */
    public int insert(Data<K,V> key) {
        if(key == null)
            throw new IllegalArgumentException("data-point value is null");
        
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
            key.setDepth(key.depth()+1);
            root.setLeft(insert(key, root.left()));
        }
        else {
            key.setDepth(key.depth() + 1);
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
     * 
     * @param key the data-point to be found in the tree
     * @param root the root of the kd-Tree
     * @return the root.
     */
    private Node<Data<K,V>> search(Data<K,V> key, Node<Data<K,V>> root){
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

    private boolean find(Data<K,V> key, Node<Data<K,V>> root) {
        if(root== null)
            return false;
        
        if(key.eq(root.data()))
            return true;

        if(key.compare(root.data()) >= 0) {
            key.setDepth(key.depth()+1);
            return find(key, root.right());
        }
        else if(key.compare(root.data()) < 0 ) {
            key.setDepth(key.depth()+1);
            return find(key, root.left());
        }
        
        return false;
    }

    /**
     * In case I have enough time this will be implemented
     * @param key
     * @return
     */
    public int remove(Data<K,V> key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    /*=================Getters - Setters=================*/
    public Node<Data<K, V>> getRoot() { return root; }

    public void setRoot(Node<Data<K, V>> root) { this.root = root; }

    
    
}
