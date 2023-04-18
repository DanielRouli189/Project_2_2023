package unitTesting;

import org.junit.Before;
import org.junit.Test;

import model.Data;
import model.KdTree;

public class KdTreeTest {

    private KdTree<Integer,Integer> kd;
    @Before
    public void setUp() {
        kd = new KdTree<>();
    }


    @Test
    public void testSearch() {
        kd.insert(new Data<>(5, 3, 1));
        kd.insert(new Data<>(90, 34, 8));
        kd.insert(new Data<>(5, 99, 23));
        kd.insert(new Data<>(54, 2, 1));
        kd.insert(new Data<>(128, 193, 457));
        
        assert kd.search(new Data<>(5, 3), kd.getRoot()) != null;
        assert kd.search(new Data<>(90, 34), kd.getRoot()) != null;
        assert kd.search(new Data<>(5, 99), kd.getRoot()) != null;
        assert kd.search(new Data<>(54, 2), kd.getRoot()) != null;
        assert kd.search(new Data<>(128, 193), kd.getRoot()) != null;
    }

    @Test
    public void testFind() {
        kd.insert(new Data<>(5, 3, 1));
        kd.insert(new Data<>(90, 34, 8));
        kd.insert(new Data<>(5, 99, 23));
        kd.insert(new Data<>(54, 2, 1));
        kd.insert(new Data<>(128, 193, 457));
        
        assert kd.find(new Data<>(5, 3), kd.getRoot());
        assert kd.find(new Data<>(90, 34), kd.getRoot());
        assert kd.find(new Data<>(5, 99), kd.getRoot());
        assert kd.find(new Data<>(54, 2), kd.getRoot());
        assert kd.find(new Data<>(128, 193), kd.getRoot());
    }

}
