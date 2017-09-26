package com.aleksandrov.Controllers;

import com.aleksandrov.Model.ModelAdd;

public class ControllerAdd implements Controller {

    private ModelAdd model;
    public ControllerAdd(ModelAdd model) {
        this.model = model;
    }

    public void cancel() {
        model.cancel();
    }

    public void englishAdd(String english) {
        model.setEnglish(english);
    }

    public void russianAdd(String russian) {
        model.setRussian(russian);
    }

    public void clear() {
        model.clear();
    }

    public void addWord() {
        model.addWord();
    }
}