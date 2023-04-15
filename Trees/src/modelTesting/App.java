package modelTesting;

import model.*;


/**
 * The {@code App} class is the main class of this application, responsible for
 * generating tests for the kd-Tree and PR Quadtree data structures.
 * 
 * @author nrouli
 * 
 */
public class App {

    private static final int[] TEST_VALUES = {200, 500, 1000, 10000, 30000, 50000, 70000, 100000};
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
        
    }
}
