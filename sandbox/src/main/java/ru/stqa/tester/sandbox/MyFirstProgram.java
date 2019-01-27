package ru.stqa.tester.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {

    hello("World");
    hello("User");
    hello("Barbara");

    double len = 5;
    System.out.println("Powierzchnia kwadratu o boku " + len + " = " + area(len));

    double a = 4;
    double b = 6;
    System.out.println("Powierzchnia prostokata o bokach " + a + " i " + b + " = " + area(a, b));
  }

  public static void hello(String somebody) {

    System.out.println("Hello, " + somebody + "!");
  }

  public static double area(double len) {
    return len * len;
  }

  public static double area(double a, double b) {
    return a - b;
  }
}