package ru.stqa.tester.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoAddNew();
    app.getContactHelper().fillAddressBookEntry(new ContactData("Anna", "Malec", "Human Resource Specialist", "Niko", "Prosta 12, 00-850 Warszawa", "502698780", "225894780", "anna.malec@niko.com"));
    app.getContactHelper().submitAddressBookEntry();
  }


}
