package ru.stqa.tester.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.tester.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {
  private WebDriver wd;

  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void submitAddressBookEntry() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillAddressBookEntry(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getCompanyAddress());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }
}
