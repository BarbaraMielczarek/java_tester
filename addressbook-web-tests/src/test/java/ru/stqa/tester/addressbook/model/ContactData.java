package ru.stqa.tester.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;


@XStreamAlias("contact")
public class ContactData {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  @Expose
  private String firstname;
  @Expose
  private String lastname;
  @Expose
  private String title;
  @Expose
  private String company;
  @Expose
  private String companyAddress;
  @Expose
  private String homePhone;
  @Expose
  private String mobilePhone;
  @Expose
  private String workPhone;
  private String allPhones;
  @Expose
  private String email;
  @Expose
  private String email2;
  @Expose
  private String email3;
  private String allEmails;
  @Expose
  private String group;
  private String name;
  @XStreamOmitField
  private File photo;


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withCompanyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
    return this;
  }
  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return  this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return  this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
  public ContactData withName(String name) {
    this.name = name;
    return  this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public int getId() {
    return id;
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

  public String getCompanyAddress() { return companyAddress; }

  public String getHomePhone() {return homePhone;}

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getAllPhones() {return allPhones;}

  public String getEmail() {
    return email;
  }

  public String getEmail2() {return email2;}

  public String getEmail3() {return email3;}

  public String getAllEmails() {return allEmails;}

  public String getGroup() {return group;}

  public String getName() {return name;}

  public File getPhoto() {return photo;}

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }




}
