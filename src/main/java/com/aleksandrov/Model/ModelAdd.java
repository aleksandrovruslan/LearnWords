package com.aleksandrov.Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aleksandrov.Base.Base;
import com.aleksandrov.Base.TestEnglishWord;
import com.aleksandrov.View.Observer;
import com.aleksandrov.View.ViewCreator;

import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class ModelAdd implements Model,Observable {

    private static final Logger log = LogManager.getLogger(ModelAdd.class.getName());
    private HashMap<String, String> properties = new HashMap<>();
    private Base base;
    private HashSet<Observer> observers = new HashSet<>();
    private boolean exit = false;
    private boolean enable = true;
    private Stack stack;
    private String english;
    private LinkedHashSet<String> russian = new LinkedHashSet<>();

    public ModelAdd(Stack stack, HashMap<String, String> properties, Base base) {
        this.stack = stack;
        this.properties = properties;
        this.base = base;
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

    public boolean getExit(){
        return exit;
    }

    public void cancel() {
        exit = true;
        stack.popup(this);
        notifyObservers();
    }

    public void setEnglish(String english) {
        if(this.english == null){
            if(!english.trim().equals("")){
                this.english = english.trim().toLowerCase();
                notifyObservers();
            }
        }
    }

    public String getEnglish(){
        if(english == null){
            return "";
        } else {
            return english;
        }
    }

    public void setRussian(String russian) {
        if(!russian.trim().equals("")){
            if(this.russian.add(russian.trim().toLowerCase()))
                notifyObservers();
        }
    }

    public String getRussian(){
        StringBuilder str = new StringBuilder();
        if(!russian.isEmpty()){
            str.append("<html>");
            for(String s:russian){
                str.append(s);
                str.append("<br>");
            }
            str.append("</html>");
        }else{
            str.append("");
        }
        return str.toString();
    }

    public void clear() {
        english = null;
        russian.clear();
        notifyObservers();
    }

    public void addWord() {
        if(!(english == null) && !russian.isEmpty()){
            if(log.isDebugEnabled()){
                log.debug("english - " + getEnglish() + ", russian - " + getRussian());
            }
            base.setTest(new TestEnglishWord(english, russian));
            english = null;
            russian.clear();
            notifyObservers();
            JOptionPane.showMessageDialog(null, "Word added.");
        } else{
            JOptionPane.showMessageDialog(null, "Word do not added, check conditions");
        }
    }
}
