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
        kd.insert(new Data<>(90, 34, 8));
        kd.insert(new Data<>(5, 3, 1));
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
        kd.insert(new Data<>(90, 34, 8));
        kd.insert(new Data<>(5, 3, 1));
        kd.insert(new Data<>(5, 99, 23));
        kd.insert(new Data<>(54, 2, 1));
        kd.insert(new Data<>(128, 193, 457));
        
        assert kd.find(new Data<>(5, 3));
        assert kd.find(new Data<>(90, 34));
        assert kd.find(new Data<>(5, 99));
        assert kd.find(new Data<>(54, 2));
        assert kd.find(new Data<>(128, 193));
    }

    @Test
    public void testIntersects(){
        kd.insert(new Data<>(90, 34, 8));
        kd.insert(new Data<>(5, 3, 1));
        kd.insert(new Data<>(5, 20, 23));
        kd.insert(new Data<>(54, 2, 1));
        kd.insert(new Data<>(128, 193, 457));

        assert !kd.intersects(new Data<>(1,1), new Data<>(4, 3), kd.getRoot());
        assert !kd.intersects(new Data<>(1,1), new Data<>(90, 33), kd.getRoot().right());
        assert !kd.intersects(new Data<>(7,101), new Data<>(9, 430), kd.getRoot().right().right());
    }

    
    @Test
    public void testRangeQuery() {
        kd.insert(new Data<>(90, 34, 8));
        kd.insert(new Data<>(5, 3, 1));
        kd.insert(new Data<>(5, 20, 23));
        kd.insert(new Data<>(54, 2, 1));
        kd.insert(new Data<>(128, 193, 457));

        assert kd.rangeQuery(new Data<>(1,1), new Data<>(4, 3)).isEmpty();
        assert kd.rangeQuery(new Data<>(0,0), new Data<>(129, 194)).size() == 5;
        assert kd.rangeQuery(new Data<>(1,1), new Data<>(127, 192)).size() == 4;
        assert kd.rangeQuery(new Data<>(1,1), new Data<>(89, 33)).size() == 3;
        assert kd.rangeQuery(new Data<>(1,1), new Data<>(6, 101)).size() == 2;
        assert kd.rangeQuery(new Data<>(1,1), new Data<>(6, 4)).size() == 1;

    }

}
