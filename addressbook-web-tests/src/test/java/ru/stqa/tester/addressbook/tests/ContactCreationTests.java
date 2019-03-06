package ru.stqa.tester.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;
import ru.stqa.tester.addressbook.model.Contacts;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    BufferedReader reader = new BufferedReader (new FileReader( new File ("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while ( line != null){
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return  contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
  }


  @Test(dataProvider ="validContactsFromXml" )
  public void testContactCreation(ContactData contact) throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    /*File photo = new File("src/test/resources/obrazek.png");
    ContactData contact = new ContactData()
            .withFirstname("Kamila").withLastname("Potocka").withPhoto(photo).withCompany("Niko").withTitle("Finance and Administration Manager")
            .withCompanyAddress("Prosta 12, 00-850 Warszawa").withHomePhone("225118967").withMobilePhone("502698990").withWorkPhone("225894990")
            .withEmail("kamila.potocka@niko.com").withEmail2("kamila.potocka@gmail.com").withGroup("[none]");*/
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
