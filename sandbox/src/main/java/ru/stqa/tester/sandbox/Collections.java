package ru.stqa.tester.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {

    /*
    Tablica
    */

    String[] langs = {"Java", "C#", "Python", "PHP"};

    for (String l : langs) { //int i = 0; i < langs.length; i++
      System.out.println("Chce nauczyc sie jezyka: " + l);//langs[i]
    }

    System.out.println();

    /*
    Lista
    */

    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

    for (String l : languages) {// int i = 0; i < languages.size(); i++
      System.out.println("Chce nauczyc sie jezyka: " + l);//+ languages.get(i)
    }
  }
}

