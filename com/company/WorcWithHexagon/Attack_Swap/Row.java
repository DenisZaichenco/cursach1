package com.company.WorcWithHexagon.Attack_Swap;

import com.company.Army;
import com.company.File_processing;
import com.company.Squat;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Row extends JPanel {
    private ArrayList<Squat> squatPanels;

    public Row(Army army, int row, String address) throws IOException {
        squatPanels = new ArrayList<>();
        File_processing.file_date.setAddress(address);
        for (int i = 0; i < army.getUnits().size(); i++) {
            if (File_processing.file_date.getRowFromUnit(army.getUnits().get(i).getID()) == row) {
                squatPanels.add(new Squat(File_processing.file_date.getInformationAboutUnite(army.getUnits().get(i).getID()), army.getUnits().get(i).getNumber(), army.getUnits().get(i).getID()));
                squatPanels.get(squatPanels.size() - 1).getUnite().setRow(File_processing.file_date.getRowFromUnit(army.getUnits().get(i).getID()));
            }
        }
        setLayout(new GridLayout(1, squatPanels.size()));
        for (Squat squatPanel : squatPanels) {
            add(squatPanel);
        }
    }
    public Row() { }

    public ArrayList<Squat> getSquatPanels() {
        return squatPanels;
    }
    public void addSquatPanels(Squat squat) {
        squatPanels.add(squat);
    }
    public void setSquatPanels(int index,Squat squat){
        squatPanels.set(index,squat);
    }
}
