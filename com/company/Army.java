package com.company;

import java.util.ArrayList;

public class Army {
    private ArrayList<Element> units;

    public Army(){
        units = new ArrayList<>();
    }
    public Army(ArrayList<Element> units){
        this.units = units;
    }

    public void addUnit(Element unit){
        units.add(unit);
    }
    public boolean removeUnit(int id,int number){
        int position;
        return (position = Element.containID(id,units)) != -1 && units.get(position).removeNumber(number);
    }
    public ArrayList<Element> getUnits() {
        return units;
    }
    public boolean containIdUnit(int id){
        for (Element unit : units) {
            if (unit.getID() == id)
                return true;
        }
        return false;
    }
}