package ru.stqa.tester.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;
import ru.stqa.tester.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/obrazek.png");
    ContactData contact = new ContactData()
            .withFirstname("Kamila").withLastname("Potocka").withPhoto(photo).withCompany("Niko").withTitle("Finance and Administration Manager")
            .withCompanyAddress("Prosta 12, 00-850 Warszawa").withHomePhone("225118967").withMobilePhone("502698990").withWorkPhone("225894990")
            .withEmail("kamila.potocka@niko.com").withEmail2("kamila.potocka@gmail.com").withGroup("[none]");
    app.contact().create(contact, true);
    assertEquals(app.contact().count(), before.size() + 1);
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/obrazek.png");
    ContactData contact = new ContactData()
            .withFirstname("Kamila'").withLastname("Potocka").withPhoto(photo).withTitle("Finance and Administration Manager").withCompany("Niko")
            .withCompanyAddress("Prosta 12, 00-850 Warszawa").withHomePhone("225118967").withMobilePhone("502698990").withWorkPhone("225894990")
            .withEmail("kamila.potocka@niko.com").withEmail2("kamila.potocka@gmail.com").withGroup("[none]");
    app.contact().create(contact, true);
    assertEquals(app.contact().count(), before.size());
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
