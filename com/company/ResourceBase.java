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
    public boolean removeStore(int number) {
        if ((this.store - number) < 0)
            return false;
        else {
            this.store -= number;
            return true;
        }
    }

    public int getIncome() {
        return income;
    }
    public void addIncome(int number) {
        this.income += number;
    }
    public boolean removeIncome(int number) {
        if ((this.income - number) < 0)
            return false;
        else {
            this.income -= number;
            return true;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "," + store + "," + income;
    }
}
