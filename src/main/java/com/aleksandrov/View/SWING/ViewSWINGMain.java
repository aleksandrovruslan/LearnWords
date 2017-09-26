package com.aleksandrov.View.SWING;

import com.aleksandrov.View.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aleksandrov.Controllers.ControllerMain;
import com.aleksandrov.Model.ModelMain;
import com.aleksandrov.View.Observer;

import javax.swing.*;
import java.awt.*;

public class ViewSWINGMain implements View, Observer {

    private static final Logger log = LogManager.getLogger(ViewSWINGMain.class.getName());
    private ModelMain model;
    private ControllerMain controller;
    private JFrame frame;
    private JButton learn;

    public ViewSWINGMain(ModelMain model){
        this.model = model;
        controller = new ControllerMain(model);
        model.registerObserver(this);
        create();
        log.debug("create ViewSWINGMain");
    }

    @Override
    public void update() {
        if(model.getExit()){
            frame.dispose();
            model.removeObserver(this);
        }
        frame.setEnabled(model.getEnable());
        learn.setEnabled(model.isEmptyBase());
    }

    private void create(){
        frame = new JFrame("Test English words.");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        frame.add(panel);

        learn = new JButton("Learn Words");
        learn.setEnabled(model.isEmptyBase());
        learn.addActionListener(a -> controller.learn());
        panel.add(learn);

        JButton add = new JButton("Add");
        add.addActionListener(a -> controller.add());
        panel.add(add);

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(a -> controller.cancel());
        panel.add(cancel);

        frame.setSize(200,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
