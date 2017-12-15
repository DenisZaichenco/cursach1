package com.company;

import java.util.ArrayList;

public class Element {
    private int ID;
    private int number;
    public static ArrayList<Element> elements = new ArrayList<>();
    public static final int[] ID_BUILDING_RESOURCE = new int[]{101,102,103,104};
    public static final int[] ID_RECRUIT_RESOURCE = new int[]{201,202,203,204,205,206,207,208,209,210,211,212};
    public static final int[] ID_BUILDING = new int[]{301,302,303};
    public static final int[] ID_RECRUIT = new int[]{401,402,403,404,405,406,407};
    public static final int ID_TIME = 105;

    public Element(int ID, int number){
        this.ID = ID;
        this.number = number;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumber() {
        return number;
    }
    public void addNumber(int number){
        this.number += number;
    }
    public boolean removeNumber(int number){
        if ((this.number - number)<0)
            return false;
        else{
            this.number -= number;
            return true;
        }
    }
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return ID+","+number;
    }
    public static int containID(int id, ArrayList<Element> setElements){
        for (int i = 0; i <setElements.size() ; i++) {
            if (setElements.get(i).getID()==id)
                return i;
        }
        return -1;
    }
    static int containStaticId(int id,int[] ID_ARRAY){
        for (int i = 0; i <ID_ARRAY.length ; i++) {
            if (ID_ARRAY[i] == id)
                return i;
        }
        return -1;
    }
}
/*
    static Resource Wood(int number) {
        return new Resource(101, number);
    }
    static Resource Stone(int number) {
        return new Resource(102, number);
    }
    static Resource Tool(int number) {
        return new Resource(103, number);
    }
    static Resource Builder(int number){
        return new Resource(104,number);
    }
    static Resource Time(int number){
        return new Resource(105,number);
    }
    static Resource Iron(int number){
        return new Resource(106,number);
    }

    static Resource Sword(int number) {
        return new Resource(201, number);
    }
    static Resource Spear(int number) {
        return new Resource(202, number);
    }
    static Resource Halberd(int number) {
        return new Resource(203, number);
    }
    static Resource Bow(int number) {
        return new Resource(204, number);
    }
    static Resource Crossbow(int number) {
        return new Resource(205, number);
    }
    static Resource Wildfire(int number) {
        return new Resource(206, number);
    }
    static Resource Horse(int number) {
        return new Resource(207, number);
    }
    static Resource Armor(int number) {
        return new Resource(208, number);
    }
    static Resource Shield(int number) {
        return new Resource(209, number);
    }
    static Resource Mail(int number) {
        return new Resource(210, number);
    }
    static Resource Ballista(int number) {
        return new Resource(211, number);
    }
    static Resource Catapult(int number) {
        return new Resource(212, number);
    }
    static Resource Recruit(int number){
        return new Resource(213,number);
    }

    static Resource Foot(int number){//пехота
        return new Resource(214,number);
    }
    static Resource Cavalry(int number){
        return new Resource(215,number);
    }
    static Resource Thrower(int number){
        return new Resource(216,number);
    }
    static Resource Mechanical(int number){
        return new Resource(217,number);
    }
 */
