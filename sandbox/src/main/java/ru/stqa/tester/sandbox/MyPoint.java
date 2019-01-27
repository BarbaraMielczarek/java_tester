package ru.stqa.tester.sandbox;

public class MyPoint {
  public static void main(String[] args) {


    Point point = new Point(2, 3, 4, 7);

    System.out.println("Co-ordinates of the first point = " + "[ " + point.x1 + " , " + point.x2 + " ]");
    System.out.println("Co-ordinates of the second point = " + "[ " + point.y1 + " , " + point.y2 + " ]");
    System.out.println();
    System.out.println("Dictance beetwen Point1 and Point2 = " + point.distance());

  }
}
