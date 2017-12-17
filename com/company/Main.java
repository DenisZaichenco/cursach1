package com.company;

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
       hexagon.getFort().addResourceBase(new ResourceBase(101,1120,1500,100));
       hexagon.getFort().addResourceBase(new ResourceBase(201,0,199,20));
       hexagon.getFort().recruit(new Element(401,100),File_processing.recruit_file);
       hexagon.getFort().addManufactures(new Manufacture(401,20,0));
       hexagon.getFort().addManufactures(new Manufacture(301,10,2));
       hexagon.getFort().addManufactures(new Manufacture(302,10,2));
       hexagon.getFort().addManufactures(new Manufacture(303,10,2));
       NewMove.newDay();
       NewMove.newDay();
       for (int i = 0; i <hexagon.getFort().getManufactures().size() ; i++) {
           System.out.println(hexagon.getFort().getManufactures().get(i));
       }
        /*for (Element building:hexagon.getFort().getBuildings()) {
            System.out.println(building);
        }*/
       for (ResourceBase resourceBase:hexagon.getFort().getResourceBases()) {
           System.out.println(resourceBase);
       }
       NewMove.newDay();
       NewMove.newDay();
       for (ResourceBase resourceBase:hexagon.getFort().getResourceBases()) {
           System.out.println(resourceBase);
       }
        /*for (Element limit:hexagon.getFort().getRecruit_limit()){
            System.out.println(limit);
        }*/
    }
}
