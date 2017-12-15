package com.company.WorcWithHexagon.Attack_Swap;

import com.company.Element;
import com.company.File_processing;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SplitPanel extends JPanel{
    //private int useElementNumber = 0;
    private Element manufacture;
    public SplitPanel(Element element, String address) throws IOException{
        manufacture = new Element(element.getID(),0);
        File_processing.file_date.setAddress(address);
        setBorder(BorderFactory.createTitledBorder(File_processing.file_date.getName(element.getID())));
        setLayout(new FlowLayout());

        JTextField information = new JTextField("0",4);
        information.setHorizontalAlignment(JTextField.CENTER);
        information.setEditable(false);
        JSlider slider = new JSlider(JSlider.HORIZONTAL,0,element.getNumber(),manufacture.getNumber());
            slider.setMajorTickSpacing(element.getNumber()/4);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            slider.addChangeListener(e -> {
                manufacture.setNumber(((JSlider)e.getSource()).getValue());
                information.setText(""+manufacture.getNumber());
            });

        JButton dec = new JButton("<");
            dec.addActionListener(e -> {
                if (manufacture.getNumber()!=0) {
                    manufacture.removeNumber(1);
                    slider.setValue(manufacture.getNumber());
                }
            });

        JButton inc = new JButton(">");
            inc.addActionListener(e -> {
            if (manufacture.getNumber()!=element.getNumber()) {
                manufacture.addNumber(1);
                slider.setValue(manufacture.getNumber());
            }
        });

        add(slider);
        add(dec);
        add(information);
        add(inc);
    }
    public Element getManufacture() {
        return manufacture;
    }
}
