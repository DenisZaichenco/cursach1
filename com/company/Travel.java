package com.company;

public class Travel {
    private Army army;
    private int end_x;
    private int end_y;    private int x;
    private int y;
    public Travel(Army army, int x, int y, int end_x, int end_y){
        this.army = army;
        this.x = x;
        this.y = y;
        this.end_x = end_x;
        this.end_y = end_y;
    }

    public void travel(Map map) {
        if ((end_x == x) & (end_y == y)) {
           if (map.getHexagon(end_x,end_y).getPlayerArmy() == null)
               map.getHexagon(end_x,end_y).createPlayerArmy();
            map.getHexagon(end_x,end_y).addArmy(army);
            map.getHexagon(end_x,end_y).getTravelArmy().remove(army);
        } else {
            if (map.getHexagon(x, y).getTravelArmy() == null)
                map.getHexagon(x, y).createTravelArmy();
            else
                map.getHexagon(x, y).getTravelArmy().add(army);

            if (x > end_x) {
                if (map.getHexagon(x - 1, y).getTravelArmy() == null)
                    map.getHexagon(x - 1, y).createTravelArmy();
                map.getHexagon(x - 1, y).getTravelArmy().add(army);
                map.getHexagon(x, y).getTravelArmy().remove(army);
                x -= 1;
            } else if (x < end_x) {
                if (map.getHexagon(x + 1, y).getTravelArmy() == null)
                    map.getHexagon(x + 1, y).createTravelArmy();
                map.getHexagon(x + 1, y).getTravelArmy().add(army);
                map.getHexagon(x, y).getTravelArmy().remove(army);
                x += 1;
            } else if (y > end_y) {
                if (map.getHexagon(x, y - 1).getTravelArmy() == null)
                    map.getHexagon(x, y - 1).createTravelArmy();
                map.getHexagon(x, y - 1).getTravelArmy().add(army);
                map.getHexagon(x, y).getTravelArmy().remove(army);
                y -= 1;
            } else if (y < end_y) {
                if (map.getHexagon(x, y + 1).getTravelArmy() == null)
                    map.getHexagon(x, y + 1).createTravelArmy();
                map.getHexagon(x, y + 1).getTravelArmy().add(army);
                map.getHexagon(x, y).getTravelArmy().remove(army);
                y += 1;
            }
        }
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getEnd_X() {
        return end_x;
    }
    public int getEnd_Y() {
        return end_y;
    }
    public Army getArmy() {
        return army;
    }
}
