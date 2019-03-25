package ru.stqa.tester.soap;


import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class GeoIpServiceTests {

  @Test (enabled = false)
  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("83.7.176.216");
    assertEquals(geoIP.getCountryCode(), "PL");

  }
}
