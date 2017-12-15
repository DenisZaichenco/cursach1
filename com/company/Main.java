package com.company;

import com.company.WorcWithHexagon.Attack_Swap.FitePanel;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
       /*Hexagon hexagon = new Hexagon(1,0,0,"");
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
       Map.startGameMap = new Map(10,10);*/
        Fort fort = new Fort();
        fort.addResourceBase(new ResourceBase(101,1120,100,100));
        System.out.println(fort.maxIncomeMilitaryResource(201,File_processing.RESOURCE_MILITARY));
    }
}
