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

       Hexagon hexagon = new Hexagon(1,1,1,"");
       hexagon.createFort();
       hexagon.getFort().addResourceBase(new ResourceBase(101,1120,100,100));
       System.out.println(hexagon.getFort().maxIncomeMilitaryResource(201,File_processing.RESOURCE_MILITARY));
       hexagon.getFort().addManufactures(new Manufacture(301,10,2));
       hexagon.getFort().addManufactures(new Manufacture(302,10,2));
       hexagon.getFort().addManufactures(new Manufacture(303,10,2));
       NewMove.newDay();
       NewMove.newDay();
        System.out.println(Map.hexagonWithFort.get(0).getFort().getManufactures().get(0));
        for (Element building:hexagon.getFort().getBuildings()) {
            System.out.println(building);
        }
        for (ResourceBase resourceBase:hexagon.getFort().getResourceBases()) {
            System.out.println(resourceBase);
        }
        for (Element limit:hexagon.getFort().getRecruit_limit()){
            System.out.println(limit);
        }
    }
}
