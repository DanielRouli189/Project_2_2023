package model;

/**
 * 
 * 
 * @author nrouli
 */
public class KdTree<K extends Comparable<K>, V extends Comparable<V>> {

    /** The root of the kdTree */
    private Node<Data<K,V>> root;

    public KdTree() {
        this.root = null;
    }

   /**
    * Inserts a new node with a given key into a kd-Tree.
    * 
    * @param key The value to be inserted into the kd-Tree
    * @return The method is returning an integer value of 0.
    */
    public int insert(Data<K,V> key) {
        if(key == null)
            throw new IllegalArgumentException("key value is null");
        root = insert(key, root);
        return 0;
    }

    /**
     * This is a recursive function that inserts a new node with a given key into a kd-tree.
     * 
     * @param key The value to be inserted into the kd-tree.
     * @param root The root node of the binary search tree where the new key will be inserted.
     * @return The method is returning a Node object, which is the root of the binary search tree after
     * inserting the new key.
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

    public int search(Data<K,V> key) {
        if(key == null)
            throw new IllegalArgumentException("key value is null");
        
        search(key, this.root);
        return 0;
    }

    private Node<Data<K,V>> search(Data<K,V> key, Node<Data<K,V>> root){
        if(root == null)
            return root;

        if(key.compare(root.data()) == 0)
            return root;
        
        if(key.compare(root.data()) > 0) {
            key.setDepth(key.depth()+1 );
            return search(key, root.right());
        }
        else if(key.compare(root.data()) < 0 ) {
            key.setDepth(key.depth() + 1);
            return search(key, root.left());
        }
        
        return root;
    }

    public int remove(Data<K,V> key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
    
}
