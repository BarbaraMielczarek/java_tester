package ru.stqa.tester.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;
import ru.stqa.tester.addressbook.model.Contacts;
import ru.stqa.tester.addressbook.model.GroupData;
import ru.stqa.tester.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test1"));
    }
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      File photo = new File("src/test/resources/obrazek.png");
      app.contact().create(new ContactData()
              .withFirstname("Kamila").withLastname("Potocka").withPhoto(photo).withTitle("Finance and Administration Manager").withCompany("Niko")
              .withCompanyAddress("Prosta 12, 00-850 Warszawa").withHomePhone("225894990").withMobilePhone("502698990").withWorkPhone("225894990")
              .withEmail("kamila.potocka@niko.com").withEmail2("kamila.potocka@gmail.com"), true);
    }
  }

  @Test
  public void addContactToGroupTest ()
  {
    app.contact().cuurentGroupPage();
    Contacts before = app.db().contacts();
    ContactData contactAddedToGroup = before.iterator().next();
    app.contact().addContactToGroup(contactAddedToGroup);

    assertThat(app.contact().count(), equalTo(before.size()+1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(contactAddedToGroup)));
  }

}


