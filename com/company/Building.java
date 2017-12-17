package com.company;

public class Building extends Element {
    private boolean work;
    private int workPlaceUse;
    public Building(int ID, int number,int workPlaceUse) {
        super(ID, number);
        this.workPlaceUse = workPlaceUse;
    }
     public void work(){
        work = true;
     }
     public void rest(){
         work = false;
     }

    public void setWorkPlaceUse(int workPlaceUse) {
        this.workPlaceUse = workPlaceUse;
    }
    public int getWorkPlaceUse() {
        return workPlaceUse;
    }
    public void addWorkerFromBuilding(int number){

    }
}
