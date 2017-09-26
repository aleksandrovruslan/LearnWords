package com.aleksandrov.Base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.HashSet;

public class TestEnglishWord implements Test, Serializable  {

    private static final Logger log = LogManager.getLogger(TestEnglishWord.class.getName());
    private String englishWord;
    private HashSet<String> russianWords;

    public TestEnglishWord(String english, HashSet<String> russian){
        englishWord = english;
        russianWords = (HashSet<String>) russian.clone();
    }

    public String getEnglishWord(){
        return englishWord;
    }

    public HashSet<String> getRussianWords(){
        return (HashSet<String>) russianWords.clone();
    }
}