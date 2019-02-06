package ru.stqa.tester.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData(
            "Maciej", "Widzew", "Niko SA", "Area Sales Manager",
            "Prosta 25, 89-001 Warszawa",
            "502698900", "225894900", "maciej.widzew@niko.com", null), false);
    app.getContactHelper().updatedContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
