package model;

public interface Tree<T extends Comparable<T>> {

    public int insert(T key);
    public int search(T key);
    public int remove(T key);
    
}
