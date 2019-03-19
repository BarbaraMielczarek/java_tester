package ru.stqa.tester.mantis.appmanager;

import org.apache.commons.net.telnet.TelnetClient;
import ru.stqa.tester.mantis.model.MailMessage;

import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class JamesHelper {

  private ApplicationManager app;

  private TelnetClient telnet;
  private InputStream in;
  private PrintStream out;

  private Session mailSession;
  private Store store;
  private String mailserver;

  public JamesHelper(ApplicationManager app){
    this.app = app;
    telnet = new TelnetClient();
    mailSession = Session.getDefaultInstance(System.getProperties());
  }

  public  boolean doesUserExist (String name){
    initTelnetSession();
    write("verify " + name);
    String result = readUntil("exist");
    closeTelnetSession();
    return result.trim().equals("User " + name + " exist");
  }

  public void createUser(String name, String password) {
    initTelnetSession();
    write("adduser " + name + " " + password);
    String result = readUntil("User " + name + " added");
    closeTelnetSession();
  }

  public void deleteUser(String name){
    initTelnetSession();
    write("deluser " + name);
    String result = readUntil("User " + name + " deleted");
    closeTelnetSession();
  }

  private void initTelnetSession() {
    mailserver = app.getProperty("mailserver.host");
    int port = Integer.parseInt(app.getProperty("mailserver.port"));
    String login = app.getProperty("mailserver.adminlogin");
    String password = app.getProperty("mailserver.password");
    try {
      telnet.connect(mailserver, port);
      in = telnet.getInputStream();
      out = new PrintStream(telnet.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }

    //Don't know why it doesn't allow login at the first attempt
    readUntil("Login id: ");
    write("");
    readUntil("Password:");
    write(password);

    //Second login attempt, must be successful
    readUntil("Login id: ");
    write("");
    readUntil("Password:");
    write(password);

    //Read velcome messge
    readUntil("welcome " + login + ". Help for a list of commands");

  }



  private String readUntil(String pattern) {
    try {
    char lastChar = pattern.charAt(pattern.length() - 1);
    StringBuffer sb = new StringBuffer();

      char ch = (char) in.read();
      while (true){
        System.out.println(ch);
        sb.append(ch);
        if(ch == lastChar){
          if(sb.toString().endsWith(pattern)){
            return sb.toString();
          }
        }
        ch = (char) in.read();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private void write(String value) {
    try {
      out.println(value);
      out.flush();
      System.out.println(value);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private  void closeTelnetSession(){
    write ("quit");
  }

  public  void drainEmail(String username, String password) throws MessagingException {
    Folder inbox = openInbox(username, password);
    for (Message message : inbox.getMessages()){
      message.setFlag(Flags.Flag.DELETED,true);
    }
    closeFolder(inbox);
  }

  private Folder openInbox(String username, String password) throws MessagingException {
    store = mailSession.getStore("pop3");
    store.connect(mailserver, username, password);
    Folder folder = store.getDefaultFolder().getFolder("INBOX");
    folder.open(Folder.READ_WRITE);
    return folder;

  }

  private void closeFolder(Folder folder) throws MessagingException {
    folder.close(true);
    store.close();
  }



  public List<MailMessage> waitForMail(String user, String password, int i) {
    return null;
  }
}
