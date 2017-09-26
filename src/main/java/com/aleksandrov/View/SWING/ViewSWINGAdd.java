package com.aleksandrov.View.SWING;

import com.aleksandrov.Controllers.ControllerAdd;
import com.aleksandrov.Model.ModelAdd;
import com.aleksandrov.View.Observer;
import com.aleksandrov.View.View;

import javax.swing.*;
import java.awt.*;

public class ViewSWINGAdd implements View, Observer {

    private ModelAdd model;
    private ControllerAdd controller;
    private JFrame frame;
    private JLabel englishLabel;
    private JLabel russianLabel;
    private JTextField englishTextField;
    private JButton englishAddButton;
    private JTextField russianTextField;
    private JButton russianAddButton;
    private JButton cancelButton;
    private JButton clearButton;
    private JButton addWordButton;


    public ViewSWINGAdd(ModelAdd model) {
        this.model = model;
        controller = new ControllerAdd(model);
        model.registerObserver(this);
        create();
    }

    @Override
    public void update() {
        if(model.getExit()){
            model.removeObserver(this);
            frame.dispose();
        }
        frame.setEnabled(model.getEnable());
        englishLabel.setText(model.getEnglish());
        russianLabel.setText(model.getRussian());
    }

    private void create() {
        frame = new JFrame("Test English words.");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        frame.add(panel);

        englishLabel = new JLabel();
        englishLabel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        russianLabel = new JLabel();
        russianLabel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        englishTextField = new JTextField(10);
        englishAddButton = new JButton("Add");
        englishAddButton.addActionListener(a -> controller.englishAdd(englishTextField.getText()));
        russianTextField = new JTextField(10);
        russianAddButton = new JButton("Add");
        russianAddButton.addActionListener(a -> controller.russianAdd(russianTextField.getText()));
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(a -> controller.cancel());
        clearButton = new JButton("Clear");
        clearButton.addActionListener(a -> controller.clear());
        addWordButton = new JButton("Add word");
        addWordButton.addActionListener(a -> controller.addWord());

        panel.add(englishLabel, new GridBagConstraints(0, 0, 2, 2, 0.1, 0.1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 0, 5), 0, 0));
        panel.add(russianLabel,new GridBagConstraints(0, 2, 2, 4, 0.1, 0.1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 0, 5), 0, 0));
        panel.add(englishTextField,new GridBagConstraints(2, 0, 1, 1, 0.1, 0.1,
                GridBagConstraints.SOUTH, GridBagConstraints.NONE,
                new Insets(5, 5, 0, 5), 0, 0));
        panel.add(englishAddButton,new GridBagConstraints(2, 1, 1, 1, 0.1, 0.1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(5, 5, 0, 5), 0, 0));
        panel.add(russianTextField,new GridBagConstraints(2, 2, 1, 1, 0.1, 0.1,
                GridBagConstraints.SOUTH, GridBagConstraints.NONE,
                new Insets(5, 5, 0, 5), 0, 0));
        panel.add(russianAddButton,new GridBagConstraints(2, 3, 1, 1, 0.1, 0.1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(5, 5, 0, 5), 0, 0));
        panel.add(cancelButton,new GridBagConstraints(0, 6, 1, 1, 0.1, 0.1,
                GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        panel.add(clearButton,new GridBagConstraints(1, 6, 1, 1, 0.1, 0.1,
                GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        panel.add(addWordButton,new GridBagConstraints(2, 6, 1, 1, 0.1, 0.1,
                GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
