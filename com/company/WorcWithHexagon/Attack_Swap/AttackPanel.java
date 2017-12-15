package com.company.WorcWithHexagon.Attack_Swap;

import com.company.Army;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AttackPanel extends JPanel {
    private Row[] rows;

    public AttackPanel(Army army, String address, int rowsNumber, boolean enemy) throws IOException {
        setLayout(new GridLayout(rowsNumber, 1));
        rows = new Row[rowsNumber];
        if (enemy) {
            for (int i = 0; i < rowsNumber; i++) {
                rows[i] = new Row(army, i + 1, address);
            }
        } else {
            for (int i = 0; i < rowsNumber; i++) {
                rows[i] = new Row(army, rowsNumber - i, address);
            }
        }
        for (int i = 0; i < rowsNumber; i++) {
            add(rows[i]);
        }
    }
    public Row[] getRows() {
        return rows;
    }
    public void setRows(Row[] rows) {
        this.rows = rows;
    }
}
