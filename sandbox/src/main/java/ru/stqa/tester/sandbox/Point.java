package ru.stqa.tester.sandbox;

public class Point {
  public double x1, x2;
  public double y1, y2;


  public Point(double x1, double x2, double y1, double y2) {
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
  }

  public double distance() {

    double ResultPoint1 = x2 - x1;//3-2=1
    double ResultPoint2 = y2 - y1;//7-4=3
    double ResultPowPoint1 = Math.pow(ResultPoint1, 2); //Math.pow(liczba,b)// 1 do potegi 2 = 1
    double ResultPowPoint2 = Math.pow(ResultPoint2, 2); //Math.pow(liczba,b)// 1 do potegi 2 = 1

    double ResultOfAddPowPoint1Point2 = ResultPowPoint1 + ResultPowPoint2;
    double ResultDistance = Math.sqrt(ResultOfAddPowPoint1Point2);
    return ResultDistance;

  }
}
