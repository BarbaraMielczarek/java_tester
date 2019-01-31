package ru.stqa.tester.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    gotoAddNew();
    fillAddressBookEntry(new ContactData("Magdalena", "Ptak", "Network Administrator", "Niko", "Prosta 12, 00-850 Warszawa", "502698730", "225894713", "magdalena.ptak@niko.com"));
    submitAddressBookEntry();
  }


}
