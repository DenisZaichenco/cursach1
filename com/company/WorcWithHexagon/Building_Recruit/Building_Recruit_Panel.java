package com.company.WorcWithHexagon.Building_Recruit;

import com.company.*;
import com.company.WorcWithHexagon.DialogWindow;
import javafx.beans.Observable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Building_Recruit_Panel extends JDialog {
    public static final String BUILDING = "Building";
    public static final String RECRUIT = "Recruit";
    public static int id_of_need_element;

    private Observable observable;

    public Building_Recruit_Panel(Hexagon hexagon, String type_of_panel,String address_of_file) throws IOException{
        JScrollPane scrollPane = new JScrollPane(informationPanels(hexagon,type_of_panel,address_of_file));
        scrollPane.setBounds(5,5,485,500);

        JSlider slider = new JSlider();
        slider.setBounds(5,510,485,20);

        JButton ok = new JButton();
        ok.setBounds(10,535,40,40);
        ok.addActionListener(e -> {

        });

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(440,535,40,40);
        cancel.addActionListener(e -> {
            try {
                DialogWindow.dialogWindow.dispose();
                DialogWindow.dialogWindow = new DialogWindow(hexagon,Map.startGameMap);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        add(scrollPane);
        add(slider);
        add(ok);
        add(cancel);
        add(new JPanel());

        setMinimumSize(new Dimension(500,620));
        setResizable(false);
        setVisible(true);

    }
    private JPanel informationPanels(Hexagon hexagon, String type_of_panel, String address_of_file) throws IOException{
        JPanel informationPanel = new JPanel();
        switch (type_of_panel){
            case BUILDING:
                File_processing.file_date.setAddress(address_of_file);
                Element.elements = File_processing.file_date.getSpecificResources(hexagon.getType_from_relief());
                informationPanel.setLayout(new GridLayout(Element.elements.size(),1));
                id_of_need_element = Element.elements.get(0).getID();
                for (Element building:Element.elements){
                    informationPanel.add(new Information_Panel(File_processing.building_file,"",building.getID()));//!!!
                }
                break;
            case RECRUIT:
                informationPanel.setLayout(new GridLayout(Element.ID_RECRUIT.length,1));
                id_of_need_element = Element.ID_RECRUIT[0];
                for (int i = 0; i <Element.ID_RECRUIT.length ; i++) {
                    informationPanel.add(new Information_Panel(File_processing.recruit_file,"",Element.ID_RECRUIT[i]));//!!!
                }
                break;
            default:
                BagDialogWindow.dontFoundType(type_of_panel);
                return null;
        }
        return  informationPanel;
    }
}