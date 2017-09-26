package com.aleksandrov.Model;

import com.aleksandrov.View.Observer;
import com.aleksandrov.View.ViewCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aleksandrov.Base.Base;
import com.aleksandrov.Base.TestEnglishWord;

import java.util.HashMap;
import java.util.HashSet;

public class ModelLearn implements Model, Observable {

    private static final Logger log = LogManager.getLogger(ModelLearn.class.getName());
    private HashMap<String, String> properties = new HashMap<>();
    private Base base;
    private HashSet<Observer> observers = new HashSet<>();
    private boolean exit = false;
    private boolean enable = true;
    private Stack stack;
    private TestEnglishWord test;
    private String english;
    private HashSet<String> russian = new HashSet<>();
    private String englishAnswer = "";
    private HashSet<String> russianAnswer = new HashSet<>();
    private int lot;
    private String prompt;
    private boolean isPrompt = false;
    private boolean isAnswer = false;
    private boolean answerCorrect = false;


    public ModelLearn(Stack stack, HashMap<String, String> properties, Base base) {
        this.stack = stack;
        this.properties = properties;
        this.base = base;
        refreshTest();
        stack.push(this);
        ViewCreator.createView(properties.get("view"), this);
    }


    @Override
    public void setEnable(boolean enable) {
        this.enable = enable;
        notifyObservers();
    }

    public boolean getEnable(){
        return enable;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer o:observers){
            o.update();
        }
    }

    public void answerAdd(String answer) {
        if(lot == 0){
            if(englishAnswer.equals("")){
                if(!answer.trim().equals("")){
                    englishAnswer = answer.trim().toLowerCase();
                    notifyObservers();
                }
            }
        }else{
            russianAnswer.add(answer.trim().toLowerCase());
            notifyObservers();
        }
    }

    public void cancel() {
        exit = true;
        stack.popup(this);
        notifyObservers();
    }

    public boolean getExit(){
        return exit;
    }

    public void prompt() {
        isPrompt = true;
        prompt = (lot == 0) ? english : collectionToString(russian);
        notifyObservers();
    }

    public boolean isPromptEnable(){
        return isPrompt;
    }

    public String getPrompt(){
        isPrompt = false;
        return prompt;
    }

    public void ok() {
        if(lot == 0){
            answerCorrect = (english.equals(englishAnswer)) ? true : false;
        }else{
            if(russian.size() == russianAnswer.size()){
                answerCorrect = true;
                for(String s:russian){
                    if(!russianAnswer.contains(s)){
                        answerCorrect = false;
                    }
                }
            }else{
                answerCorrect = false;
            }
        }
        isAnswer = true;
        notifyObservers();
    }

    public boolean isAnswer(){
        return isAnswer;
    }

    public boolean getAnswerCorrect(){
        return answerCorrect;
    }

    public String getEnglish(){
        return (lot == 0) ? englishAnswer : english;
    }

    public String getRussian(){
        return  collectionToString(((lot == 0) ? russian : russianAnswer));
    }

    public void nextText() {
        refreshTest();
        notifyObservers();
    }

    /**
     * if lot = 0, then need to answer english
     */
    private void refreshTest(){
        englishAnswer = "";
        russianAnswer.clear();
        isAnswer = false;
        test = (TestEnglishWord) base.getTest();
        lot = (int) (Math.random()*2);
        english = test.getEnglishWord();
        russian = test.getRussianWords();
    }

    private String collectionToString(HashSet<String> collection){
        StringBuilder str = new StringBuilder();
        str.append("<html>");
        log.debug("start StringBuilder russian");
        for(String s:collection){
            str.append(s);
            str.append("<br>");
        }
        str.append("</html>");
        return str.toString();
    }

}
