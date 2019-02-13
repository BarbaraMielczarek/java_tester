package ru.stqa.tester.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

  @Test (enabled = false)
    public void testPrime() {
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test (enabled = false)
  public void testPrimeFast() {
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test
  public void testPrimeFaster() {
    Assert.assertTrue(Primes.isPrimeFaster(Integer.MAX_VALUE));
  }


  @Test
  public void testNonPrime() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
  }

  @Test (enabled = false)
  public void testPrimeLong() {
   long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }


}
