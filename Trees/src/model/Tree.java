package model;

public interface Tree<T extends Comparable<T>> {
    
    public T insert(T key);
    public T search(T key);
    public void remove(T key);
    
}
