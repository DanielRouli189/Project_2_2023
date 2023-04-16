package modelTesting;

import model.*;


/**
 * The {@code App} class is the main class of this application, responsible for
 * generating tests for the kd-Tree and PR-Quadtree data structures.
 * 
 * @author nrouli
 * 
 */
public class App {
    
    /* The Singleton instance of the App class. */
    private static App app = null;

    
    private KdTree<Integer,Integer> kdTree;

    public App() {
        kdTree = new KdTree<>();
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
    }
}
