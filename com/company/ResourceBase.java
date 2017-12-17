package com.company;

public class ResourceBase extends Element {
    private int store;
    private int income;

    ResourceBase(int ID, int number, int store, int income) {
        super(ID, number);
        this.store = store;
        this.income = income;
    }

    public int getStore() {
        return store;
    }
    public void addStore(int number) {
        this.store += number;
    }
    public boolean canRemoveStore(int number) {
        return this.store - number >= 0;
    }
    public void removeStore(int store){
        this.store -= store;
    }

    public int getIncome() {
        return income;
    }
    public void addIncome(int number) {
        this.income += number;
    }
    public boolean canRemoveIncome(int number) {
        return this.income - number >= 0;
    }
    public void removeIncome(int income){
        this.income -= income;
    }
    @Override
    public String toString() {
        return super.toString() + "," + store + "," + income;
    }
}
