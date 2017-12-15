package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class Fort {
    private ArrayList<ResourceBase> resourceBases;
    private ArrayList<Element> buildings;
    private ArrayList<Manufacture> manufactures;
    private ArrayList<Element> recruit_limit;

    Fort() {
        resourceBases = new ArrayList<>();
        buildings = new ArrayList<>();
        manufactures = new ArrayList<>();
        recruit_limit = new ArrayList<>();
    }

    public void addResourceBase(ResourceBase resource) {
        for (ResourceBase resourceBase : resourceBases) {
            if (resourceBase.getID() == resource.getID()) {
                resourceBase.addNumber(resource.getNumber());
                resourceBase.addStore(resource.getStore());
                resourceBase.addIncome(resource.getIncome());
                return;
            }
        }
        resourceBases.add(resource);//resourceBases.trimToSize();
    }
    public void addBuilding(Element building) {
        int number;
        if ((number = Element.containID(building.getID(), buildings)) != -1) {
            buildings.get(number).addNumber(building.getNumber());
        } else
            buildings.add(building);
    }
    public void addManufactures(Manufacture manufacture) {
        manufactures.add(manufacture);
    }
    public void addRecruit_Limit(Element limit) {
        int number;
        if ((number = Element.containID(limit.getID(), recruit_limit)) != -1) {
            recruit_limit.get(number).addNumber(limit.getNumber());
        } else
            recruit_limit.add(limit);
    }

    public boolean removeResourceBase(ResourceBase resource) {
        for (ResourceBase resourceBase : resourceBases) {
            if (resourceBase.getID() == resource.getID()) {
                return resourceBase.removeNumber(resource.getNumber()) &&
                        resourceBase.removeStore(resource.getStore()) &&
                        resourceBase.removeIncome(resource.getIncome());
            }
        }
        return true;
    }
    public boolean removeBuilding(Element building) {
        int number;
        return (number = Element.containID(building.getID(), buildings)) != -1 && buildings.get(number).removeNumber(building.getNumber());
    }
    public boolean removeManufactures(Manufacture manufacture) {
        return manufactures.remove(manufacture);// manufactures.trimToSize();
    }
    public boolean removeRecruite_Limit(Element limit) {
        int number;
        return (number = Element.containID(limit.getID(), recruit_limit)) != -1 && recruit_limit.get(number).removeNumber(limit.getNumber());
    }

    public ArrayList<ResourceBase> getResourceBases() {
        return resourceBases;
    }
    public ArrayList<Element> getBuildings() {
        return buildings;
    }
    public ArrayList<Manufacture> getManufactures() {
        return manufactures;
    }
    public ArrayList<Element> getResourceBasesElement() {
        ArrayList<Element> element_base = new ArrayList<>();
        for (ResourceBase resourceBase : resourceBases) {
            element_base.add(new Element(resourceBase.getID(), resourceBase.getNumber()));
        }
        return element_base;
    }
    public ArrayList<Element> getRecruit_limit() {
        return recruit_limit;
    }

    public int containIdManufacture(int id) {
        for (int i = 0; i < manufactures.size(); i++) {
            if (manufactures.get(i).getID() == id)
                return i;
        }
        return -1;
    }
    public int containIdResourceBase(int id){
        for (int i = 0; i < resourceBases.size(); i++) {
            if (resourceBases.get(i).getID() == id)
                return i;
        }
        return -1;
    }

    public int maxIncomMilitaryResourse(int id,String address_of_military_resource) throws IOException{
        int max = Integer.MAX_VALUE;
        int number;
        File_processing.file_date.setAddress(address_of_military_resource);
        Element.elements = File_processing.file_date.getCost(id);
        for (int i = 0; i <Element.elements.size() ; i++) {
            if ((number = containIdResourceBase(Element.elements.get(i).getID()))==-1)
                return 0;
            else if ((number = resourceBases.get(number).getNumber()/Element.elements.get(i).getNumber())<max){
                max = number;
            }
        }
        /*if (containIdResourceBase(ID)==-1)
            resourceBases.add(new ResourceBase(ID,0,0,0));*/

        return max;
    }
}