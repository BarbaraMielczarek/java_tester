package ru.stqa.tester.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoAddNew();
    app.getContactHelper().fillAddressBookEntry(new ContactData("Anna", "Adamczyk", "Marketing Assistant ", "Niko", "Prosta 12, 00-850 Warszawa", "502698880", "225894880", "anna.adamczyk@niko.com"));
    app.getContactHelper().submitAddressBookEntry();
  }

}
