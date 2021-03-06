package ru.stqa.tester.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.tester.addressbook.model.ContactData;
import ru.stqa.tester.addressbook.model.Contacts;
import ru.stqa.tester.addressbook.model.Groups;

import java.io.File;
import java.util.List;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void submitAddressBookEntry() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getCompanyAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    }else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }


    private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  /* public void initContactModification(int index) {
     wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
     *//*click(By.xpath("//img[@alt='Edit']"));*//*
  }*/

  public void updatedContactModification() {
    click(By.xpath("//input[@name='update']"));
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }


  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
    submitAddressBookEntry();
    contacCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    updatedContactModification();
    contacCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    contacCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contacCache = null;

  public Contacts all() {
    if (contacCache != null) {
      return new Contacts(contacCache);
    }
    contacCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      contacCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withCompanyAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
    }
    return new Contacts(contacCache);
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String company = wd.findElement(By.name("company")).getAttribute("value");
    String title = wd.findElement(By.name("title")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    File photo = new File("src/test/resources/obrazek.png");
    /* initAttachFileById(contact.getPhoto());*/
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withPhoto(photo).withCompany(company).withTitle(title).withCompanyAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }

 /* private void initAttachFileById(File file) {
    wd.findElement(By.xpath("//input[@name='photo']")).sendKeys(file.getAbsolutePath());
  }
*/

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    //alternatywa
    //wd.findElement(By.xpath(String.format("/input[@value='%s']/../../td[8]/a",id))).click();
    //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    //wd.findElement(By.xpath(String.format("a[href='edit.php?id=%s']", id))).click();

  }

  public ContactData infoFromDetailsForm(ContactData contact) {
    initContactDetailsById(contact.getId());
    String name = wd.findElement(By.id("content")).getText();
    return new ContactData().withId(contact.getId()).withName(name);
  }

  private void initContactDetailsById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(6).findElement(By.tagName("a")).click();
  }



  public void cuurentGroupPage()
  {
    wd.findElement(By.name("group")).click();
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("test 1");
    wd.findElement(By.xpath("//option[@value='65']")).click();
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
  }
  public void addContactToGroup (ContactData contact)
  {

    selectContactById(contact.getId());
    chooseGroupAndAddContact ();

  }

  public void chooseGroupAndAddContact ()
  {

    wd.findElement(By.name("to_group")).click();
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText("test 1");
    wd.findElement(By.xpath("(//option[@value='71'])[2]")).click();
    wd.findElement(By.name("add")).click();
    wd.findElement(By.linkText("group page \"test 1\"")).click();
  }

}
