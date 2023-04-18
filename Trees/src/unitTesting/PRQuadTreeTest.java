package unitTesting;

import org.junit.Before;
import org.junit.Test;

import model.PRData;
import model.PRQuadTree;
import modelTesting.Config;

public class PRQuadTreeTest {

    private PRQuadTree<Integer> pr;
    
    @Before
    public void setUp() {
        pr = new PRQuadTree<>(Config.N_MIN, Config.N_MAX, Config.N_MIN, Config.N_MAX);
    }

    @Test
    public void testFind() {
        pr.insert(new PRData<>(5, 3, 1));
        pr.insert(new PRData<>(90, 34, 8));
        pr.insert(new PRData<>(5, 99, 23));
        pr.insert(new PRData<>(54, 2, 1));
        pr.insert(new PRData<>(128, 193, 457));
        
        assert pr.findTest(new PRData<>(5, 3));
        assert pr.findTest(new PRData<>(90, 34));
        assert pr.findTest(new PRData<>(5, 99));
        assert pr.findTest(new PRData<>(54, 2));
        assert pr.findTest(new PRData<>(128, 193));
    }

}
