package com.company.WorcWithHexagon;

import com.company.File_processing;
import com.company.Hexagon;
import com.company.WorcWithHexagon.Building_Recruit.Building_Recruit_Panel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Start_Panel extends JPanel {
    private JButton build;
    private JButton recruitment;
    private JButton swap;
    private JButton attack;
    public Start_Panel(Hexagon hexagon){
        setLayout(new GridLayout(1,5));

        JPanel workButton;
        JPanel optionPanel;

        workButton = new JPanel();
        workButton.setLayout(new FlowLayout());

     //   setBuild();
        recruitment(hexagon);
        swap(hexagon);
        attack(hexagon);

        add(build);
        add(recruitment);
        add(swap);
        add(attack);

        optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(2,1));
        optionPanel.add(new JButton("Ok"));
        optionPanel.add(new JButton("Cancel"));


        add(optionPanel);
    }
   /* private void setBuild(){
        build = new JButton("Build");
        build.addActionListener(e -> {
            DialogWindow.dialogWindow.setSize(400,600);
            DialogWindow.dialogWindow.remove(DialogWindow.dialogWindow.getWorkPanel());
            try {
                DialogWindow.dialogWindow.add(new Building_Recruit_Panel(DialogWindow.dialogWindow.getHexagon(),Building_Recruit_Panel.BUILDING, File_processing.building_file));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }*/
    private void recruitment(Hexagon hexagon){
        recruitment = new JButton("Recruitment");
        recruitment.addActionListener(e -> setSize(400,600));
    }
    private void swap(Hexagon hexagon){
        swap = new JButton("Swap");
        swap.addActionListener(e -> setSize(400,600));
    }//обмен
    private void attack(Hexagon hexagon){
        attack = new JButton("Attack");
        attack.addActionListener(e -> setSize(400,600));
    }
}
