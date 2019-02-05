package ru.stqa.tester.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillAddressBookEntry(new ContactData("Piotr", "Czerko", "Niko SA", "Area Sales Manager", "Prosta 25, 89-001 Warszawa", "502698700", "25894700", "piotr.czerko@niko.com"));
    app.getContactHelper().updatedContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
