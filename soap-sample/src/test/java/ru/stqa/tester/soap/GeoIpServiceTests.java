package ru.stqa.tester.soap;


import com.lavasoft.GeoIPService;
import com.lavasoft.GetCountryISO2ByName;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    GetCountryISO2ByName ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("83.7.176.216");
    assertEquals(ipLocation.getCountryName(), "PL");


  }
}
