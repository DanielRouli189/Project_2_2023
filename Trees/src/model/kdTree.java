package model;

/**
 * The {@code KdTree} class represents a generic kd-Tree data structure 
 * that performs the operations of {@link #KdTree.insert(key) insertion}, 
 * {@link #KdTree.search(key) searching}, and {@link #KdTree.remove(key) deletion}.
 *  
 * 
 * @author nrouli
 * @since 2023-04
 */
public class KdTree<K extends Comparable<K>, V extends Comparable<V>> {

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
     * 
     * @param key The data-point to be searched for in the kd-Tree
     * @return The depth of the data-point in the kd-Tree.
     * @throws IllegalArgumentException if the key value is null.
     */
    public int search(Data<K,V> key) {
        if(key == null)
            throw new IllegalArgumentException("data-point value is null");
        
        key.setDepth(0);
        search(key, this.root);
        return key.depth();
    }

    private Node<Data<K,V>> search(Data<K,V> key, Node<Data<K,V>> root){
        if(root == null)
            return root;

        if(key.equals(root.data()) == 0)
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
     * In case I have enough time this will be implemented
     * @param key
     * @return
     */
    public int remove(Data<K,V> key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
    
}
