package ru.stqa.tester.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;
import ru.stqa.tester.addressbook.model.GroupData;
import ru.stqa.tester.addressbook.model.Groups;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test1"));
    }
    Groups groups = app.db().groups();
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      File photo = new File("src/test/resources/obrazek.png");
      app.contact().create(new ContactData()
              .withFirstname("Kamila").withLastname("Potocka").withPhoto(photo).withTitle("Finance and Administration Manager").withCompany("Niko")
              .withCompanyAddress("Prosta 12, 00-850 Warszawa").withHomePhone("225894990").withMobilePhone("502698990").withWorkPhone("225894990")
              .withEmail("kamila.potocka@niko.com").withEmail2("kamila.potocka@gmail.com").inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testContactDetails() {

    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);

    assertThat(margeDetails(contactInfoFromEditForm), equalTo(cleanedPhones(contactInfoFromDetailsForm.getName())));
  }

  private String margeTitle(ContactData contact) {
    return Arrays.asList((contact.getFirstname() + " " + contact.getLastname()).trim(),contact.getTitle(), contact.getCompany(), contact.getCompanyAddress())
            .stream().filter((n) -> !n.equals("")).collect(Collectors.joining("\n"));
  }

  private String margePhonesForDetails(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((p) -> !p.equals("")).map(ContactPhoneEmailAddressTests::cleaned).collect(Collectors.joining("\n"));
  }

  private String margeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((t) -> !t.equals("")).collect(Collectors.joining("\n"));
  }

  private String margeDetails(ContactData contact) {
    return Arrays.asList(margeTitle(contact), margePhonesForDetails(contact), margeEmails(contact))
            .stream().filter((d) -> !d.equals("")).collect(Collectors.joining("\n"));
  }

  public String cleanedPhones(String name) {
    return name.replaceAll("[A-Z]: ", "").replaceAll("\n\n", "\n")
            .replaceAll("\n\nMember of: [.]* *[0-9]*", "");//.replaceAll("\n\nMember of: test[0-9]*", "");
  }

}
