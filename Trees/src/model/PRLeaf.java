package model;

public class PRLeaf<T> {
    
    private T data;

    public PRLeaf(T data){
        this.data = data;
    }

    public T data() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
