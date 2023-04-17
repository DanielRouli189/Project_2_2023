package modelTesting;

import model.*;


/**
 * The {@code App} class is the main class of this application, responsible for
 * generating tests for the kd-Tree and PR-Quadtree data structures.
 * 
 * @author nrouli
 * @since 2023-04
 * 
 */
public class App {
    
    /* The Singleton instance of the App class. */
    private static App app = null;

    
    private KdTree<Integer,Integer> kdTree;
    private PRQuadTree<Integer> prQtree;

    public App() {
        kdTree = new KdTree<>();
        prQtree = new PRQuadTree<>(Config.N_MIN, Config.N_MAX, Config.N_MIN, Config.N_MAX);
    }

    public static App getInstance() {
        return (app == null) ? new App() : app;
    }
    
    public static void main(String[] args) {
        app = App.getInstance();

        app.kdTree.insert(new Data<>(1,7));
        app.kdTree.insert(new Data<>(4,6));
        app.kdTree.insert(new Data<>(7,8));
        app.kdTree.insert(new Data<>(9,9));
        app.kdTree.insert(new Data<>(10,3));
        app.kdTree.insert(new Data<>(1,1));

        int i = app.kdTree.search(new Data<>(1,7));
        System.out.println("key found at depth: "+i);
        i = app.kdTree.search(new Data<>(1,1));
        System.out.println("key found at depth: "+i);

        app.prQtree.insert(new PRData<>(1,7));
        app.prQtree.insert(new PRData<>(4,6));
        app.prQtree.insert(new PRData<>(7,8));
        app.prQtree.insert(new PRData<>(9,9));
        app.prQtree.insert(new PRData<>(10,3));
        app.prQtree.insert(new PRData<>(11,11));

        int flag = app.prQtree.find(new PRData<>(11,11));
        
        System.out.println("data-point was found at depth: "+ flag);
    }
}
