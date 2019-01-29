package ru.stqa.tester.sandbox;

public class Point {
  public double x;
  public double y;


  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }


  public double distance(Point p2) {

    double distanceX = x - p2.x;
    double distanceY = y - p2.y;

    return Math.sqrt(distanceX * distanceX + distanceY * distanceY);

  }
}
