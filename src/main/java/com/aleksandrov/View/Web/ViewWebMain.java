package com.aleksandrov.View.Web;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aleksandrov.Model.ModelMain;
import com.aleksandrov.View.Observer;
import com.aleksandrov.View.View;

public class ViewWebMain implements View, Observer {

    private static final Logger log = LogManager.getLogger(ViewWebMain.class.getName());
    private ModelMain model;

    public ViewWebMain(ModelMain model) {
        this.model = model;
        log.debug("create ViewWebMain");
    }

    @Override
    public void update() {

    }
}
