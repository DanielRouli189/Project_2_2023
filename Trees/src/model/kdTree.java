package model;

public class KdTree<K extends Comparable<K>, V extends Comparable<V>> {

    /** The root of the kdTree */
    private Node<Data<K,V>> root;

    public KdTree() {
        this.root = null;
    }

   /**
    * This is a Java function that inserts a key into a binary search tree and returns 0.
    * 
    * @param key The key is the value that needs to be inserted into the binary search tree.
    * @return The method is returning an integer value of 0.
    */
    public int insert(Data<K,V> key) {
      root = insert(key, root);
      return 0;
    }

    /**
     * This is a recursive function that inserts a new node with a given key into a binary search tree.
     * 
     * @param key The value to be inserted into the binary search tree.
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
