package mathia;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Assert;
import org.junit.Test;

public class UtilTest {
    @Test
    public void linearizedCordToMiddle() throws Exception {
        Vector2D subject;
        subject = Util.linearizedCordToMiddle(4, 3, 1);
        Assert.assertEquals(new Vector2D(1.5f, 1.5f), subject);
        subject = Util.linearizedCordToMiddle(0, 3, 1);
        Assert.assertEquals(new Vector2D(0.5f, 0.5f), subject);
        subject = Util.linearizedCordToMiddle(8, 3, 1);
        Assert.assertEquals(new Vector2D(2.5f, 2.5f), subject);
        subject = Util.linearizedCordToMiddle(3, 3, 1);
        Assert.assertEquals(new Vector2D(0.5f, 1.5f), subject);
    }

    @Test
    public void linearizedCord() throws Exception {
        Assert.assertEquals(4, Util.linearizedCord(1, 1, 3));
        Assert.assertEquals(8, Util.linearizedCord(2, 2, 3));
        Assert.assertEquals(0, Util.linearizedCord(0, 0, 3));
        Assert.assertEquals(1, Util.linearizedCord(1, 0, 3));
        Assert.assertEquals(3, Util.linearizedCord(0, 1, 3));
    }

}