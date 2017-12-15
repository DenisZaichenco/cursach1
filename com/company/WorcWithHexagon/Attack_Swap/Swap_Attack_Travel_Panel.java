package com.company.WorcWithHexagon.Attack_Swap;

import com.company.*;
import com.company.WorcWithHexagon.DialogWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Swap_Attack_Travel_Panel extends JDialog implements ActionListener{
    public static final String SWAP = "Swap";
    public static final String ATTACK = "Attack";
    public static final String TRAVEL = "Travel";
    public static ArrayList<Element> set_Of_Elements;
    private JButton ok;
    private JButton cancel;
    private String type_of_panel;
    private ArrayList<SplitPanel> elements;
    private Hexagon hexagon;
    private int way = 5;

    public Swap_Attack_Travel_Panel(String type_of_panel, Hexagon hexagon, String address, Map map)throws IOException{
        this.hexagon = hexagon;
        this.type_of_panel = type_of_panel;
        elements = new ArrayList<>();
        JScrollPane scrollPane = new JScrollPane(mainPanel(type_of_panel,hexagon,address));
        scrollPane.setBounds(5,5,485,500);

        ok = new JButton();
        ok.setBounds(5,535,40,40);
        ok.addActionListener(this);

        cancel = new JButton("Cancel");
        cancel.setBounds(447,535,40,40);
        cancel.addActionListener(e -> {
            try {
                DialogWindow.dialogWindow.dispose();
                DialogWindow.dialogWindow = new DialogWindow(hexagon,map);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        if (type_of_panel.equals(SWAP)) {
            JComboBox<Hexagon> list = new JComboBox<>();
            for (Hexagon hexagon1:Map.hexagonWithFort) {
                list.addItem(hexagon1);
            }
            list.setBounds(55,535,380,40);
            add(list);
        }

        add(scrollPane);
        add(ok);
        add(cancel);
        add(new JPanel());

        setMinimumSize(new Dimension(500,620));
        setResizable(false);
        setVisible(true);
    }
    private JPanel mainPanel(String type_of_panel, Hexagon hexagon, String address_with_name_element) throws IOException {
        JPanel informationPanel = new JPanel();
        switch (type_of_panel) {
            case SWAP:
                informationPanel.setLayout(new GridLayout(hexagon.getFort().getResourceBases().size(),1));
                for (ResourceBase resource:hexagon.getFort().getResourceBases()) {
                    elements.add(new SplitPanel(new Element(resource.getID(),resource.getNumber()),address_with_name_element));
                    informationPanel.add(elements.get(elements.size()-1));
                }
                break;
            case ATTACK:
            case TRAVEL:
                informationPanel.setLayout(new GridLayout(hexagon.getPlayerArmy().getUnits().size(),1));
                for (Element unit: hexagon.getPlayerArmy().getUnits()){
                    informationPanel.add(new SplitPanel(new Element(unit.getID(),unit.getNumber()),address_with_name_element));
                }
                break;
            default:
                BagDialogWindow.dontFoundType(type_of_panel);
                return null;
        }
        return informationPanel;
    }

    public ArrayList<SplitPanel> getElements() {
        return elements;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        set_Of_Elements = new ArrayList<>();
        switch (type_of_panel){
            case SWAP:
                for (SplitPanel element : elements) {
                    Map.hexagonWithFort.get(0).getFort().addManufactures(new Manufacture(element.getManufacture().getID(), element.getManufacture().getNumber(), way));
                    set_Of_Elements.add(element.getManufacture());
                }
                break;
            case ATTACK:

                break;
            case TRAVEL:
                for (SplitPanel unit: elements)
                    set_Of_Elements.add(unit.getManufacture());
                DialogWindow.dialogWindow.setVisible(false);
                Hexagon.active_travel_mod();
                while (!Hexagon.end_point_active){}
                if (NewMove.travels==null)
                    NewMove.travels = new ArrayList<>();
                NewMove.travels.add(new Travel(new Army(set_Of_Elements),hexagon.getX(),hexagon.getY(),Hexagon.travel_x,Hexagon.travel_y));
                Hexagon.deactivate_travel_mod();
                break;
            default:
                BagDialogWindow.dontFoundType(type_of_panel);
        }
    }
}
