package com.company;

import com.company.WorcWithHexagon.Attack_Swap.AttackPanel;
import com.company.WorcWithHexagon.Attack_Swap.FitePanel;
import com.company.WorcWithHexagon.Attack_Swap.Swap_Attack_Travel_Panel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args)throws IOException{
       Hexagon hexagon = new Hexagon(1,0,0,"");
       hexagon.createFort();
       hexagon.createPlayerArmy();
       hexagon.getFort().addBuilding(new Element(301,1));
       hexagon.getFort().addResourceBase(new ResourceBase(101,100,100,100));
       Army army = new Army();
        for (int i = 0; i <=6 ; i++) {
            army.addUnit(new Element(401+i, 1));
        }
       new FitePanel(army,army,3,File_processing.recruit_file);
       hexagon.addArmy(army);
       Map.startGameMap = new Map(10,10);
    }
}
