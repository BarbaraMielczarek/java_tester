package ru.stqa.tester.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();

    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData()
            .withFirstname("Kamila").withLastname("Potocka").withTitle("Finance and Administration Manager ").withCompany("Niko")
            .withCompanyAddress("Prosta 12, 00-850 Warszawa").withMobile("502698990").withWorkPhone("225894990").withEmail("kamila.potocka@niko.com").withGroup("test10");
    app.contact().create(contact, true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
