package com.company;

import java.util.ArrayList;

public class NewMove {
    public static ArrayList<Travel> travels;

    public static void newDay(Map map) {
        income();
        // travel(map);
        //manufacture();
    }

    private static void travel(Map map) {
        for (Travel travel : NewMove.travels) {
            travel.travel(map);
        }
    }

    private static void manufacture() {
        for (Hexagon aHexagonWithFort : Map.hexagonWithFort) {
            for (Manufacture manufacture : aHexagonWithFort.getFort().getManufactures()) {
                if (manufacture.newDay()) {
                    Element information = new Element(manufacture.getID(), manufacture.getNumber());
                    aHexagonWithFort.getFort().removeManufactures(manufacture);
                    if ((Element.containStaticId(information.getID(), Element.ID_BUILDING_RESOURCE) != -1) || (Element.containStaticId(information.getID(), Element.ID_RECRUIT_RESOURCE) != -1))
                        aHexagonWithFort.getFort().addResourceBase(new ResourceBase(information.getID(), information.getNumber(), 0, 0));
                    else if (Element.containStaticId(information.getID(), Element.ID_BUILDING) != -1)
                        aHexagonWithFort.getFort().addBuilding(information);
                    else if (Element.containStaticId(information.getID(), Element.ID_RECRUIT) != -1)
                        aHexagonWithFort.addArmy(new Army());
                    else
                        System.out.println();//ошибка нет такого id
                }
            }
        }
    }

    private static void income() {
        Map.hexagonWithFort.trimToSize();
        for (Hexagon hexagon : Map.hexagonWithFort) {
            System.out.println(Map.hexagonWithFort.size());
            for (ResourceBase resourceBase : hexagon.getFort().getResourceBases()) {
                if (resourceBase.getNumber()<=resourceBase.getStore()) {
                    resourceBase.addNumber(resourceBase.getIncome());
                }
            }
        }
    }
}
