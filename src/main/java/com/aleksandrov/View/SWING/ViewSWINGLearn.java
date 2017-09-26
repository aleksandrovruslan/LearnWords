package com.aleksandrov.View.SWING;


import com.aleksandrov.Controllers.ControllerLearn;
import com.aleksandrov.View.View;
import com.aleksandrov.Model.ModelLearn;
import com.aleksandrov.View.Observer;

import javax.swing.*;
import java.awt.*;

public class ViewSWINGLearn implements View, Observer {

    private ModelLearn model;
    private ControllerLearn controller;
    private JFrame frame;
    private JLabel english;
    private JLabel russian;
    private JTextField answer;
    private JButton answerAdd;
    private JButton cancel;
    private JButton prompt;
    private JButton ok;

    public ViewSWINGLearn(ModelLearn model){
        this.model = model;
        controller = new ControllerLearn(model);
        model.registerObserver(this);
        create();
    }

    @Override
    public void update() {
        if(model.getExit()){
            frame.dispose();
            model.removeObserver(this);
        }
        frame.setEnabled(model.getEnable());
        if(model.isPromptEnable()){
            JOptionPane.showMessageDialog(frame, model.getPrompt());
        }
        if(model.isAnswer()){
            JOptionPane.showMessageDialog(frame, model.getAnswerCorrect() ? "Answer true" : "Answer false");
            controller.nextTest();
        }
        english.setText(model.getEnglish());
        russian.setText(model.getRussian());
    }

    private void create(){
        frame = new JFrame("Test English words.");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        frame.add(panel);

        english = new JLabel(model.getEnglish());
        english.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        russian = new JLabel(model.getRussian());
        russian.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        answer = new JTextField(10);
        answerAdd = new JButton("Add");
        answerAdd.addActionListener(a -> controller.answerAdd(answer.getText()));
        cancel = new JButton("Cancel");
        cancel.addActionListener(a -> controller.cancel());
        prompt = new JButton("Prompt");
        prompt.addActionListener(a -> controller.prompt());
        ok = new JButton("Ok");
        ok.addActionListener(a -> controller.ok());

        panel.add(english, new GridBagConstraints(0, 0, 3, 3, 0.65, 0.5,
                GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        panel.add(russian, new GridBagConstraints(3, 0, 3, 3, 0.35, 0.5,
                GridBagConstraints.NORTHEAST, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        panel.add(answer, new GridBagConstraints(0, 3, 3, 1, 0.0, 0.1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(5, 5, 0, 5), 0, 5));
        panel.add(answerAdd, new GridBagConstraints(3, 3, 3, 1, 0.0, 0.1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(5, 5, 0, 5), 0, 0));
        panel.add(cancel, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.1,
                GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        panel.add(prompt, new GridBagConstraints(2, 4, 2, 1, 0.0, 0.1,
                GridBagConstraints.SOUTH, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        panel.add(ok, new GridBagConstraints(4, 4, 2, 1, 0.0, 0.1,
                GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
