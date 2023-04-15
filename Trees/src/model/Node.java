package model;

public class Node<T>{
    
    /** The content of the node */
    private T data;

    /** The left child of the node */
    private Node<T> left;

    /** The right child of the node */
    private Node<T> right;

    /** The depth of the node in the tree*/
    private int depth;

    /**
     * Creates a new instance of the {@code Node} class.
     * @param data
     */
    public Node(T data){
        this.data = data;
        depth = 0;
    }

    /*========= Getters - Setters =========*/
    public T data() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> left() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> right() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public int depth() {
        return depth;
    }

    public void setDepth(int depth){
        this.depth = depth;
    }

}
