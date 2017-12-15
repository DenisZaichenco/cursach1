package com.company;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Squat extends JButton {
    public static Squat activeSquat;
    public static Squat attackSquat;
    private Unite unite;
    private int number;
    private int number_of_initiative_list;
    private int healthOfLastUnit;

    public Squat(Unite unite, int number, int id) throws IOException {
        File_processing.file_date.setAddress(File_processing.recruit_file);
        setText(File_processing.file_date.getName(id));
        this.number = number;
        this.unite = unite;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (activeSquat == null)
                    activeSquat = Squat.this;
                else if (!Squat.this.equals(activeSquat))
                    attackSquat = Squat.this;
                System.out.println(activeSquat);
                System.out.println(activeSquat.number);
                System.out.println(attackSquat);
                System.out.println(number_of_initiative_list);
            }
        });
    }

    private double damage(Squat enemy, boolean attack) {
        double coefficientDamage = 1 + 0.01 * (unite.getAttack() - enemy.getUnite().getDefense());
        if (coefficientDamage > 1.5)//крит
            return coefficientDamage * ((Math.random() * 1000) % 2) + 1;
        else if (coefficientDamage < 0.5)//промах
            return coefficientDamage * (1 / ((Math.random() * 1000) % 2) + 1);
        else //обычная атака
            return coefficientDamage;
    }

    public Unite getUnite() {
        return unite;
    }
    public int getNumber() {
        return number;
    }
    public int getHealthOfLastUnit() {
        return healthOfLastUnit;
    }
    public int getNumber_of_initiative_list() {
        return number_of_initiative_list;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setHealthOfLastUnit(int healthOfLastUnit) {
        this.healthOfLastUnit = healthOfLastUnit;
    }
    public void setNumber_of_initiative_list(int number_of_initiative_list) {
        this.number_of_initiative_list = number_of_initiative_list;
    }

    public void attack(Squat enemy) {
        int allHP;
        if (getHealthOfLastUnit() == 0)
            allHP = (int) ((enemy.getUnite().getHealth() * enemy.getNumber() + getHealthOfLastUnit()) - (Math.random() * (unite.getMax_damage() - unite.getMin_damage()) + unite.getMin_damage()) * damage(enemy, true));
        else
            allHP = (int) ((enemy.getUnite().getHealth() * (enemy.getNumber() - 1) + getHealthOfLastUnit()) - (Math.random() * (unite.getMax_damage() - unite.getMin_damage()) + unite.getMin_damage()) * damage(enemy, true));
        enemy.setNumber(allHP / enemy.getUnite().getHealth());
        if ((allHP = allHP % enemy.getUnite().getHealth()) != 0) {
            enemy.setNumber(enemy.getNumber() + 1);
            setHealthOfLastUnit(allHP);
        }
    }

    @Override
    public String toString() {
        return unite.toString() + "," + number;
    }
}