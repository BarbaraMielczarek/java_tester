package ru.stqa.tester.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Kamila").withLastname("Potocka").withTitle("Finance and Administration Manager ").withCompany("Niko")
              .withCompanyAddress("Prosta 12, 00-850 Warszawa").withMobile("502698990").withWorkPhone("225894990").withEmail("kamila.potocka@niko.com").withGroup("test10"), true);
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("Marcin").withLastname("Widzew").withCompany("Niko SA").withTitle("Area Sales Manager")
            .withCompanyAddress("Prosta 25, 89-001 Warszawa").withMobile("502698900").withWorkPhone("225894900").withEmail("marcin.widzew@niko.com").withGroup(null);
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
