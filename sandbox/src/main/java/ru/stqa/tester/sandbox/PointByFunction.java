package ru.stqa.tester.sandbox;

public class PointByFunction {
  public double x1, x2;
  public double y1, y2;


  public static void main(String[] args) {


    PointByFunction p1 = new PointByFunction();
    PointByFunction p2 = new PointByFunction();
    p1.x1 = 2;
    p2.y1 = 3;
    p1.x2 = 4;
    p2.y2 = 7;

    System.out.println("Co-ordinates of the first point = " + "[ " + p1.x1 + " , " + p1.x2 + " ]");
    System.out.println("Co-ordinates of the second point = " + "[ " + p2.y1 + " , " + p2.y2 + " ]");
    System.out.println();
    System.out.println("Dictance beetwen Point1 and Point2 = " + distance(p1, p2));

  }

  public static double distance(PointByFunction p1, PointByFunction p2) {
    double ResultPowPoint1 = Math.pow(p1.x2 - p1.x1, 2); //Math.pow(liczba,b)// 1 do potegi 2 = 1
    double ResultPowPoint2 = Math.pow(p2.y2 - p2.y1, 2); //Math.pow(liczba,b)// 1 do potegi 2 = 1

    double ResultOfAddPowPoint1Point2 = ResultPowPoint1 + ResultPowPoint2;
    double ResultDistance = Math.sqrt(ResultOfAddPowPoint1Point2);
    return ResultDistance;

  }
}
