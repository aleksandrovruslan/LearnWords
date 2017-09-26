package com.aleksandrov.Model;

import com.aleksandrov.Base.Base;
import com.aleksandrov.Base.BaseCreator;
import com.aleksandrov.View.Observer;
import com.aleksandrov.View.ViewCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;

public class ModelMain implements Observable, Model{

    private static final Logger log = LogManager.getLogger(ModelMain.class.getName());
    private HashMap<String, String> properties = new HashMap<>();
    private Base base;
    private HashSet<Observer> observers = new HashSet<>();
    private boolean exit = false;
    private boolean enable = true;
    private Stack stack;

    public ModelMain(Stack stack){
        this.stack = stack;
        properties = Settings.getSettings().getProperties();
        log.debug(properties.get("base"));
        base = BaseCreator.getBase(properties.get("base"));
        stack.push(this);
        ViewCreator.createView(properties.get("view"), this);
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
        for(Observer o:observers) {
            o.update();
        }
    }

    public void learn() {
        new ModelLearn(stack, properties, base);
    }

    public void add() {
        new ModelAdd(stack, properties, base);
    }

    public void cancel() {
        exit = true;
        stack.popup(this);
        base.closeBase();
        notifyObservers();
    }

    public boolean getExit(){
        return exit;
    }

    public boolean isEmptyBase(){
        return base.isEmpty();
    }

    /**
     * change the activity of the current view
     * @param enable
     */
    @Override
    public void setEnable(boolean enable){
        this.enable = enable;
        notifyObservers();
    }

    public boolean getEnable(){
        return enable;
    }

}