package com.aleksandrov.Controllers;


import com.aleksandrov.Model.ModelMain;

public class ControllerMain implements Controller {

    private ModelMain model;

    public ControllerMain(ModelMain model){
        this.model = model;
    }

    public void learn() {
        model.learn();
    }

    public void cancel() {
        model.cancel();
    }

    public void add() {
        model.add();
    }
}
