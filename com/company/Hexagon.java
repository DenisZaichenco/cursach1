package com.company;

import com.company.WorcWithHexagon.Attack_Swap.Swap_Attack_Travel_Panel;
import com.company.WorcWithHexagon.DialogWindow;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Hexagon extends JButton  implements ActionListener{
    private static boolean travel_mod = false;
    public static int travel_x;
    public static int travel_y;
    public static boolean end_point_active = false;

    private int type_from_relief;
    private Fort fort;
    private Army enemy;
    private Army playerArmy;
    private ArrayList<Army> travelArmy;
    private int x;
    private int y;

    public Hexagon(int type_from_relief,int x,int y,String name){
        super(name);
        this.x = x;
        this.y = y;
        this.type_from_relief = type_from_relief;

        //setBackground(Color.blue);
        addActionListener(this);
    }

    public int getType_from_relief() {
        return type_from_relief;
    }
    public void setType_from_relief(int type_from_relief) {
        this.type_from_relief = type_from_relief;
    }

    public void createFort(){
        fort = new Fort();
        Map.hexagonWithFort.add(Hexagon.this);
        System.out.println(x+" "+y);
    }
    public void createEnemy(){
        enemy = new Army();
    }
    public void createPlayerArmy(){
        playerArmy = new Army();
    }
    public void createTravelArmy(){
        travelArmy = new ArrayList<>();
    }

    public Fort getFort() {
        return fort;
    }
    public Army getEnemy() {
        return enemy;
    }
    public Army getPlayerArmy() {
        return playerArmy;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public ArrayList<Army> getTravelArmy() {
        return travelArmy;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        try {
            if (!travel_mod)
            DialogWindow.dialogWindow = new DialogWindow(Hexagon.this,Map.startGameMap);
            if (travel_mod) {
                travel_x = x;
                travel_y = y;
                end_point_active = true;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public void addArmy(Army army) {
        int number;
        for (int i = 0; i <army.getUnits().size() ; i++) {
            if ((number = Element.containID(army.getUnits().get(i).getID(),playerArmy.getUnits()))!=-1){
                playerArmy.getUnits().get(number).addNumber(army.getUnits().get(i).getNumber());
            }
            else
                playerArmy.addUnit(army.getUnits().get(i));
        }
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public static void active_travel_mod(){
        travel_mod = true;
    }
    public static void deactivate_travel_mod(){
        travel_mod = false;
    }

    @Override
    public String toString() {
        return type_from_relief+" "+x+" "+y;
    }
}
