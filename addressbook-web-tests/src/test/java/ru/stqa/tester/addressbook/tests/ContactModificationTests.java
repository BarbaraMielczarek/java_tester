package ru.stqa.tester.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Kamila").withLastname("Potocka").withTitle("Finance and Administration Manager ").withCompany("Niko")
              .withCompanyAddress("Prosta 12, 00-850 Warszawa").withMobile("502698990").withWorkPhone("225894990").withEmail("kamila.potocka@niko.com").withGroup("test10"), true);
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Marcin").withLastname("Widzew").withCompany("Niko SA").withTitle("Area Sales Manager")
            .withCompanyAddress("Prosta 25, 89-001 Warszawa").withMobile("502698900").withWorkPhone("225894900").withEmail("marcin.widzew@niko.com").withGroup(null);
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
