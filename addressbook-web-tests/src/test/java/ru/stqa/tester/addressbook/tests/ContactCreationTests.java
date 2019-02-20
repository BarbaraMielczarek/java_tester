package ru.stqa.tester.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();

    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Kamila").withLastname("Potocka").withTitle("Finance and Administration Manager ").withCompany("Niko")
            .withCompanyAddress("Prosta 12, 00-850 Warszawa").withMobile("502698990").withWorkPhone("225894990").withEmail("kamila.potocka@niko.com").withGroup("test10");
    app.contact().create(contact, true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
