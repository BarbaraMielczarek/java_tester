package ru.stqa.tester.addressbook;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String title;
  private final String company;
  private final String companyAddress;
  private final String mobile;
  private final String workPhone;
  private final String email;

  public ContactData(String firstname, String lastname, String title, String company, String companyAddress, String mobile, String workPhone, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.title = title;
    this.company = company;
    this.companyAddress = companyAddress;
    this.mobile = mobile;
    this.workPhone = workPhone;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getCompanyAddress() {
    return companyAddress;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail() {
    return email;
  }
}
