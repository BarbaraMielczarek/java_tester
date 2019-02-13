package ru.stqa.tester.sandbox;

public class Primes {

  public static boolean isPrime(int n) {

    for (int i = 2; i < n ; i++) { // i = i + 1 to i +=1, a jesli powiekszam o 1 to i++
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }


  public static boolean isPrimeFast(int n) {

    for (int i = 2; i < n /2 ; i++) { // i = i + 1 to i +=1, a jesli powiekszam o 1 to i++
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeFaster(int n) {

    int m = (int) Math.sqrt(n);
    for (int i = 2; i < m ; i++) { // i = i + 1 to i +=1, a jesli powiekszam o 1 to i++
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }


  public static boolean isPrimeWhile(int n) {

    int i = 2;
    while (i < n && n % i != 0) {
      i++;
    }
    return i == n;
  }

  public static boolean isPrime(long n) {

    for (long i = 2; i < n ; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
