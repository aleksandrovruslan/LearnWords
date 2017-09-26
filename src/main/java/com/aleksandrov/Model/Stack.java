package com.aleksandrov.Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class Stack {

    private static final Logger log = LogManager.getLogger(Stack.class.getName());
    private LinkedList<Model> models = new LinkedList<>();

    /**
     * добавляем новую модель в конец списка
     * предыдущая становиться неактивной
     * @param model
     */
    public void push(Model model){
        try {
            models.getLast().setEnable(false);
        }catch (Exception e){

        }
        models.addLast(model);
    }

    /**
     * удаляем из стека последнюю модель
     * и делаем активной следущую
     * @param model
     */
    public void popup(Model model){
        if(!models.isEmpty() && models.getLast().equals(model)){
            models.removeLast();
            if(!models.isEmpty()) {
                models.getLast().setEnable(true);
            }
        }else {
            log.error("incorrect model");
        }
    }

}
