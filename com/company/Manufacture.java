package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class Manufacture extends Element {
    private int time_to_end_production;
    public Manufacture(int ID, int number, int time_to_end_production) {
        super(ID, number);
        this.time_to_end_production = time_to_end_production;
    }

    public int getTime_to_end_production() {
        return time_to_end_production;
    }
    public void setTime_to_end_production(int time_to_end_production) {
        this.time_to_end_production = time_to_end_production;
    }

    public boolean newDay(){
        return --time_to_end_production==0;
    }

    public static int maxManufactureRecruit(Hexagon hexagon, String address_of_recruit, int ID) throws IOException {
        File_processing.file_date.setAddress(address_of_recruit);
        ArrayList<Element> cost = File_processing.file_date.getCost(ID);
        int max = Integer.MAX_VALUE;
        int number;
        int point;
        Element.elements = hexagon.getFort().getResourceBasesElement();
        if ((point = Element.containID(Integer.parseInt(File_processing.file_date.getType(ID)), hexagon.getFort().getRecruit_limit())) != -1) {
            for (Element aCost : cost) {
                if ((number = Element.containID(aCost.getID(), Element.elements)) != -1) {
                    if ((number = (int) Math.floor(hexagon.getFort().getResourceBases().get(number).getNumber() / aCost.getNumber())) < max) {//повертаэ цілу частину при діленн
                        max = number;
                    }
                } else
                    return 0;
            }
            number = hexagon.getFort().getRecruit_limit().get(point).getNumber();
            if (max<number)
                return  max;
            else
                return number;
        }
        return 0;
    }
    public static int maxManufactureBuilding(Hexagon hexagon,String address_of_relief,int ID)throws IOException{//изначально выводятся на екран только здания которые есть в списке доступных к постройке
        int number;
        int max;
        File_processing.file_date.setAddress(address_of_relief);
        Element.elements = File_processing.file_date.getSpecificResources(hexagon.getType_from_relief());
        if ((number = Element.containID(ID,hexagon.getFort().getBuildings()))!=-1){
            int tmp = Element.containID(ID,Element.elements);
            max =  Element.elements.get(tmp).getNumber()-hexagon.getFort().getBuildings().get(number).getNumber();
        }
        else {
            number = Element.containID(ID,Element.elements);
            max = Element.elements.get(number).getNumber();
        }
        if (( number = hexagon.getFort().containIdManufacture(ID))!=-1)
            return max-hexagon.getFort().getManufactures().get(number).getNumber();
        else
            return max;
    }

    @Override
    public String toString() {
        return super.toString()+","+time_to_end_production;
    }
}