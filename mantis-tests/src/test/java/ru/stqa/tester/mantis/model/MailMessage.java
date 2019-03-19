package ru.stqa.tester.mantis.model;

public class MailMessage {

  public String to;
  public String text;

  public MailMessage (String to, String text){
    this.to = to;
    this.text = text;
  }
}
