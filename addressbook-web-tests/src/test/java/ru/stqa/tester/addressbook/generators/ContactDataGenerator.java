package ru.stqa.tester.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
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

  @Parameter(names = "-d", description = "Data  format")
  public String format;

  public static void main(String[] args) throws IOException {

    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateCounts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();//
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getPhoto(),
                contact.getTitle(), contact.getCompany(), contact.getCompanyAddress(),
                contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getGroup()));
      }
    }
  }

  private List<ContactData> generateCounts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();

    for (int i = 0; i < count; i++) {
      //File photo = new File("src/test/resources/obrazek.png");
      contacts.add(new ContactData().withFirstname(String.format("Kamila %s", i)).withLastname(String.format("Potocka %s", i)).withPhoto(new File(String.format("src/test/resources/obrazek.png")))
              .withTitle(String.format("Finance and Administration Manager")).withCompany(String.format("Niko")).withCompanyAddress(String.format("Prosta 12, 00-850 Warszawa"))
              .withHomePhone(String.format("22511896%s", i)).withMobilePhone(String.format("50269899%s", i)).withWorkPhone(String.format("22589499%s", i))
              .withEmail(String.format("kamila.potocka%s@niko.com", i)).withEmail2(String.format("kamila.potocka%s@gmail.com", i)).withEmail3(String.format("kamila.potocka%s@onet.com", i))
              .withGroup(String.format("[none]")));
    }
    return contacts;
  }
}
