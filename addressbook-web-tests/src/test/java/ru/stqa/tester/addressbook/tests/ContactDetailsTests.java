package ru.stqa.tester.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Kamila").withLastname("Potocka").withTitle("Finance and Administration Manager ").withCompany("Niko")
              .withCompanyAddress("Prosta 12, 00-850 Warszawa").withHomePhone("225894990").withMobilePhone("502698990").withWorkPhone("225894990")
              .withEmail("kamila.potocka@niko.com").withEmail2("kamila.potocka@gmail.com").withGroup("test10"), true);
    }
  }

  @Test
  public void testContactDetails (){
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);

    assertThat(margeDetails(contactInfoFromEditForm), equalTo(contactInfoFromDetailsForm));
  }


  private String margeNames(ContactData contact) {
    return Arrays.asList(contact.getName(), contact.getLastname(), contact.getTitle(), contact.getCompany(),contact.getCompanyAddress())
            .stream().collect(Collectors.joining("\n"));
  }

  private String margePhones (ContactData contact){
    return Arrays.asList("H: " + contact.getHomePhone(), "M: " + contact.getMobilePhone(), "W: " + contact.getWorkPhone())
            .stream().collect(Collectors.joining("\n"));
  }

  private String margeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((t) -> !t.equals(""))
            .map(ContactPhoneEmailAddressTests::cleaned).collect(Collectors.joining("\n"));
  }

  private String margeDetails (ContactData contact){
   return Arrays.asList(margeNames(contact), margePhones(contact), margeEmails(contact), "Member of: " + contact.getGroup())
    .stream().collect(Collectors.joining("\n\n"));

  }

  /*public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[A-Z]", "").replaceAll(":", "");
  }*/
}
