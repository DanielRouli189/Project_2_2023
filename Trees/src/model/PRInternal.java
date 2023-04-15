package model;

public class PRInternal<T> {
    
    private PRInternal<T> nw;
    private PRInternal<T> ne;
    private PRInternal<T> sw;
    private PRInternal<T> se;

    public PRInternal() {
        this.nw = null;
        this.ne = null;
        this.sw = null;
        this.se = null;
    }

    public boolean isLeaf() {
        return (nw == null && ne == null && sw == null && se == null);
    }

    public PRInternal<T> getNW() {
        return nw;
    }

    public void setNW(PRInternal<T> nw) {
        this.nw = nw;
    }

    public PRInternal<T> getNE() {
        return ne;
    }

    public void setNE(PRInternal<T> ne) {
        this.ne = ne;
    }

    public PRInternal<T> getSW() {
        return sw;
    }

    public void setSW(PRInternal<T> sw) {
        this.sw = sw;
    }

    public PRInternal<T> getSE() {
        return se;
    }

    public void setSE(PRInternal<T> se) {
        this.se = se;
    }

}
