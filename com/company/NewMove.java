package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class NewMove {
    public static ArrayList<Travel> travels;

    public static void newDay() throws IOException {
        income();
        manufacture();
    }
    public static void travel(Map map) {
        for (Travel travel : NewMove.travels) {
            travel.travel(map);
        }
    }

    private static void manufacture() throws IOException {
        for (Hexagon aHexagonWithFort : Map.hexagonWithFort) {
            for (Manufacture manufacture : aHexagonWithFort.getFort().getManufactures()) {
                if (manufacture.newDay()) {
                    Element information = new Element(manufacture.getID(), manufacture.getNumber());
                    if ((Element.containStaticId(information.getID(), Element.ID_BUILDING) != -1)) {
                        aHexagonWithFort.getFort().addBuilding(information);
                        aHexagonWithFort.getFort().build(information,File_processing.building_file);
                    }
                    else if ((Element.containStaticId(information.getID(), Element.ID_RECRUIT_RESOURCE) != -1) ||
                            (Element.containStaticId(information.getID(),Element.ID_BUILDING_SIMPLE_RESOURCE)) !=-1)
                        aHexagonWithFort.getFort().addResourceBase(new ResourceBase(information.getID(), information.getNumber(), 0, 0));
                    else if (Element.containStaticId(information.getID(), Element.ID_RECRUIT) != -1) {
                        if (aHexagonWithFort.getPlayerArmy()==null)
                            aHexagonWithFort.createPlayerArmy();
                        aHexagonWithFort.addArmy(new Army());
                    }
                    else
                        System.out.println();//ошибка нет такого id
                }
            }
            aHexagonWithFort.getFort().clearManufacture();
        }
    }


    private static void income() throws IOException {
        for (Hexagon hexagon:Map.hexagonWithFort) {
            for (ResourceBase resource : hexagon.getFort().getResourceBases()) {
                if (Element.containStaticId(resource.getID(),Element.ID_BUILDING_SIMPLE_RESOURCE)!=-1) {
                    if (resource.getStore() < resource.getNumber() + resource.getIncome())
                        resource.setNumber(resource.getStore());
                    else
                        resource.addNumber(resource.getIncome());
                }
                else if (Element.containStaticId(resource.getID(),Element.ID_RECRUIT_RESOURCE)!=-1){
                    int max = hexagon.getFort().getMaxIncomeResource(resource.getID(),File_processing.RESOURCE_MILITARY);
                    if (max>=1) {
                        hexagon.getFort().payFromCreateResource(resource.getID(), max, File_processing.RESOURCE_MILITARY);
                        resource.addNumber(max*File_processing.file_date.getSpecificResources(resource.getID()).get(0).getNumber());
                    }
                }
            }
        }
    }
}
