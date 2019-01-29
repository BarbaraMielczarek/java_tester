package ru.stqa.tester.sandbox;

public class MyPoint {
  public static void main(String[] args) {


    Point p1 = new Point(-3,5);

    Point p2 = new Point(7,-1);

    System.out.println("Co-ordinates of the first point = " + "(" + p1.x + " , " +  p1.y + ")");
    System.out.println("Co-ordinates of the first point = " + "(" + p2.x + " , " +  p2.y + ")");
    System.out.println();
    System.out.println("Distance between Point p1 and Point p2 is: " + p1.distance(p2));
  }
}
