package com.aleksandrov.Controllers;

import com.aleksandrov.Model.ModelLearn;

public class ControllerLearn implements Controller {

    private ModelLearn model;

    public ControllerLearn(ModelLearn model){
        this.model = model;
    }

    public void answerAdd(String answer) {
        model.answerAdd(answer);
    }

    public void cancel() {
        model.cancel();
    }

    public void prompt() {
        model.prompt();
    }

    public void ok() {
        model.ok();
    }

    public void nextTest() {
        model.nextText();
    }
}
