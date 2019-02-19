package ru.stqa.tester.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.goTo().gotoHomePage();

    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData(
            "Kamila", "Potocka", "Finance and Administration Manager ",
            "Niko", "Prosta 12, 00-850 Warszawa",
            "502698990", "225894990", "kamila.potocka@niko.com", "test10");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
