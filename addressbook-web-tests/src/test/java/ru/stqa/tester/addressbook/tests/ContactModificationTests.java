package ru.stqa.tester.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();

    int before = app.getContactHelper().getContactCount();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData(
              "Kamila", "Potocka", "Finance and Administration Manager ",
              "Niko", "Prosta 12, 00-850 Warszawa",
              "502698990", "225894990", "kamila.potocka@niko.com", "test10"), true);
    }
    app.getContactHelper().initContactModification(before - 1 );
    app.getContactHelper().fillContactForm(new ContactData(
            "Marcin", "Widzew", "Niko SA", "Area Sales Manager",
            "Prosta 25, 89-001 Warszawa",
            "502698900", "225894900", "marcin.widzew@niko.com", null), false);
    app.getContactHelper().updatedContactModification();
    app.getContactHelper().returnToHomePage();

    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before);
  }
}
