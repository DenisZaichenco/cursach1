package com.company.WorcWithHexagon.Building_Recruit;

import com.company.Element;
import com.company.File_processing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Information_Panel extends JPanel {
    private ArrayList<Element> costList;
    private int ID;

    public Information_Panel(String address_of_file, String address_of_image, int id) throws IOException {
        ID = id;
        setLayout(new FlowLayout());
        File_processing.file_date.setAddress(address_of_file);
        setBorder(BorderFactory.createTitledBorder(File_processing.file_date.getName(id)));
        costList = File_processing.file_date.getCost(id);
        JLabel cost = new JLabel();
        for (Element aCostList : costList) {
            cost.setText(cost.getText() + " " + aCostList);
        }
        JTextArea information = new JTextArea(3, 20);
        information.setLineWrap(true);
        information.setWrapStyleWord(true);
        information.setEditable(false);
        information.setText(File_processing.file_date.getInformation(id));

        //add(new JLabel());
        add(cost);
        //add(image);
        add(information);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Building_Recruit_Panel.id_of_need_element = ID;
                System.out.println(Building_Recruit_Panel.id_of_need_element);
            }
        });
    }
    public int getID() {
        return ID;
    }
    public ArrayList<Element> getCostList() {
        return costList;
    }
}