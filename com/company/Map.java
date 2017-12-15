package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Map extends JFrame {
    public static Map startGameMap;
    private int MAX_X;
    private int MAX_Y;
    private Hexagon[][] map;
    private JButton newDay;
    public static ArrayList<Hexagon> hexagonWithFort = new ArrayList<>();

    public Map(int column/*колонки |*/, int row/*ряд-*/) {
        setSize(600, 600);
        setLayout(new GridLayout(column, row));
        setMinimumSize(new Dimension(600, 600));
       setResizable(false);
        MAX_X = column;
        MAX_Y = row;
        map = new Hexagon[column][row];
        //JPanel panel = new JPanel();
       // panel.setLayout(new GridLayout(column, row));
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {

                Hexagon hexagon = new Hexagon(1,i,j,"");
                hexagon.createFort();
                hexagon.createPlayerArmy();
                hexagon.getFort().addBuilding(new Element(301,1));
                hexagon.getFort().addResourceBase(new ResourceBase(101,100,100,100));
                Army army = new Army();
                for (int k = 0; k <=3 ; k++) {
                    army.addUnit(new Element(401+k, 1));
                }
                hexagon.addArmy(army);

                add(map[i][j] = hexagon);// new Hexagon(10,i,j,i+" "+j),i,j);
                System.out.println(map[i][j].getType_from_relief());
            }
        }
        for (int i = 0; i <10 ; i++) {
            map[(int) (Math.random()*column)][(int) (Math.random()*row)].createEnemy();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        System.out.println("this: " + this.getWidth() + " " + this.getHeight());
        //System.out.println("panel: " + panel.getWidth() + " " + panel.getHeight());
        this.pack();
        this.setVisible(true);
    }

    public Hexagon[][] getMap() {
        return map;
    }

    public Hexagon getHexagon(int x, int y) {
        return map[x][y];
    }

    public int getMAX_X() {
        return MAX_X;
    }

    public int getMAX_Y() {
        return MAX_Y;
    }

    public void setHexagon(int x, int y, Hexagon hexagon) {
        if ((x>(MAX_X-1)) || (y>(MAX_Y-1)))
            System.out.println("Not correct information");
        else{
            hexagon.setX(x);
            hexagon.setY(y);
            map[x][y] = hexagon;
        }
    }

    public void setMap(Hexagon[][] map) {
        this.map = map;
    }

    public void setMAX_X(int MAX_X) {
        this.MAX_X = MAX_X;
    }

    public void setMAX_Y(int MAX_Y) {
        this.MAX_Y = MAX_Y;
    }

    private JButton newDayButton() {
        JButton jButton = new JButton();
        jButton.addActionListener(e -> {

        });
        return jButton;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}