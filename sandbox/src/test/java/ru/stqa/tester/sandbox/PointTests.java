package ru.stqa.tester.sandbox;

        import org.testng.Assert;
        import org.testng.annotations.Test;

public class PointTests {


  @Test
  public void testDistance() {

    Point p1 = new Point(2, 5);
    Point p2 = new Point(5, 9);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }
}
