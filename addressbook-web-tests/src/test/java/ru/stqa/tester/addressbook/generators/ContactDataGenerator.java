package ru.stqa.tester.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.tester.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {

    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateCounts(count);
    save(contacts, new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact: contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",contact.getFirstname(),contact.getLastname(),contact.getPhoto(),
              contact.getTitle(),contact.getCompany(),contact.getCompanyAddress(),
              contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
              contact.getEmail(),contact.getEmail2(),contact.getEmail3(),contact.getGroup()));
    }
    writer.close();
  }

  private List<ContactData> generateCounts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    File photo = new File("src/test/resources/obrazek.png");
    for (int i = 0; i < count; i++){
      contacts.add(new ContactData().withFirstname(String.format("Kamila %s", i)).withLastname(String.format("Potocka %s", i)).withPhoto(photo)
              .withTitle(String.format("Finance and Administration Manager")).withCompany(String.format("Niko")).withCompanyAddress(String.format("Prosta 12, 00-850 Warszawa"))
              .withHomePhone(String.format("22511896%s", i)).withMobilePhone(String.format("50269899%s", i)).withWorkPhone(String.format("22589499%s", i))
              .withEmail(String.format("kamila.potocka%s@niko.com", i)).withEmail2(String.format("kamila.potocka%s@gmail.com", i)).withEmail3(String.format("kamila.potocka%s@onet.com", i))
              .withGroup(String.format("[none]")));
    }
    return contacts;
  }
}
