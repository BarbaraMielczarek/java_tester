package ru.stqa.tester.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.gotoAddNew();
    app.fillAddressBookEntry(new ContactData("Magdalena", "Ptak", "Network Administrator", "Niko", "Prosta 12, 00-850 Warszawa", "502698730", "225894713", "magdalena.ptak@niko.com"));
    app.submitAddressBookEntry();
  }


}
