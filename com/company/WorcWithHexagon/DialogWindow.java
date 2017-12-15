package com.company.WorcWithHexagon;
import com.company.*;
import com.company.WorcWithHexagon.Attack_Swap.Swap_Attack_Travel_Panel;
import com.company.WorcWithHexagon.Building_Recruit.Building_Recruit_Panel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DialogWindow extends JDialog {
    public static JDialog dialogWindow;
    public DialogWindow(Hexagon hexagon,Map map)throws IOException{
        setLayout(new GridLayout(1,6));

        JButton building = new JButton(new ImageIcon("src\\image\\build.png"));
        building.addActionListener(e -> {
            try {
                dialogWindow.dispose();
                dialogWindow = new Building_Recruit_Panel(hexagon,Building_Recruit_Panel.BUILDING, File_processing.type_from_relief);
            } catch (IOException e1) {
                BagDialogWindow.fileReadError(File_processing.recruit_file);
            }
        });
        add(building);

        JButton recruit = new JButton(new ImageIcon("src\\image\\hire.png"));
        recruit.addActionListener(e -> {
            try {
                dialogWindow.dispose();
                dialogWindow = new Building_Recruit_Panel(hexagon,Building_Recruit_Panel.RECRUIT,"");
            } catch (IOException e1) {
                BagDialogWindow.fileReadError("");
            }
        });
        add(recruit);

        JButton swap = new JButton("swap");
        swap.addActionListener(e -> {
            try {
                dialogWindow.dispose();
                dialogWindow = new Swap_Attack_Travel_Panel(Swap_Attack_Travel_Panel.SWAP,hexagon,File_processing.resource,map);
            } catch (IOException e1) {
                BagDialogWindow.fileReadError(File_processing.resource);
            }
        });
        add(swap);

        JButton attack = new JButton(new ImageIcon("src\\image\\attack.png"));
        attack.addActionListener(e -> {
            try {
                dialogWindow.dispose();
                dialogWindow = new Swap_Attack_Travel_Panel(Swap_Attack_Travel_Panel.ATTACK,hexagon,File_processing.recruit_file,map);
            } catch (IOException e1) {
                BagDialogWindow.fileReadError(File_processing.recruit_file);
            }
        });
        add(attack);

        JButton travel = new JButton(new ImageIcon("src\\image\\travel.png"));
        travel.addActionListener(e -> {
            try {
                dialogWindow.dispose();
                dialogWindow = new Swap_Attack_Travel_Panel(Swap_Attack_Travel_Panel.TRAVEL,hexagon,File_processing.recruit_file,map);
            } catch (IOException e1) {
                BagDialogWindow.fileReadError(File_processing.recruit_file);
            }
        });
        add(travel);

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> DialogWindow.dialogWindow.dispose());
        add(cancel);

        if (hexagon.getFort() == null) {
            building.setEnabled(false);
            swap.setEnabled(false);
        }
        if (hexagon.getFort() == null || hexagon.getFort().getRecruit_limit()==null)
            recruit.setEnabled(false);

        if (hexagon.getPlayerArmy()==null)
            travel.setEnabled(false);


        setNotActiveAttackButton(attack,map,hexagon);


        setSize(450,100);
        setResizable(false);
        setVisible(true);
    }
    private void setNotActiveAttackButton(JButton attackButton,Map map,Hexagon hexagon){
        if (hexagon.getPlayerArmy()!=null) {
            boolean maxX = hexagon.getX() == (map.getMAX_X()-1);
            boolean maxY = hexagon.getY() == (map.getMAX_Y()-1);
            boolean minX = hexagon.getX() == 0;
            boolean minY = hexagon.getY() == 0;

            System.out.println(hexagon.getX()+" "+hexagon.getY());
            if (maxX & maxY) {
                if (upEnemy(hexagon,map) & leftEnemy(hexagon,map))
                    attackButton.setEnabled(false);
            }
            else if (minX & minY){
                if (rightEnemy(hexagon,map) & downEnemy(hexagon,map))
                    attackButton.setEnabled(false);
            }
            else if (maxX & minY){
                if (leftEnemy(hexagon,map) & downEnemy(hexagon,map))
                    attackButton.setEnabled(false);
            }
            else if (maxY & minX){
                if (upEnemy(hexagon,map) & rightEnemy(hexagon,map))
                    attackButton.setEnabled(false);
            }
            else if (maxX){
                if (upEnemy(hexagon,map) & downEnemy(hexagon,map) & leftEnemy(hexagon,map))
                    attackButton.setEnabled(false);
            }
            else if (maxY){
                if (upEnemy(hexagon,map) & leftEnemy(hexagon,map) & rightEnemy(hexagon,map))
                    attackButton.setEnabled(false);
            }
            else if (minY){
                if (rightEnemy(hexagon,map) & downEnemy(hexagon,map) & leftEnemy(hexagon,map))
                    attackButton.setEnabled(false);
            }
            else if (minX){
                if (upEnemy(hexagon,map) & downEnemy(hexagon,map) & rightEnemy(hexagon,map))
                    attackButton.setEnabled(false);
            }
            else
                if (upEnemy(hexagon,map) & downEnemy(hexagon,map) & rightEnemy(hexagon,map) & leftEnemy(hexagon,map))
                    attackButton.setEnabled(false);
        }

    }
    private boolean rightEnemy(Hexagon hexagon,Map map){
        if (map.getHexagon(hexagon.getX()+1,hexagon.getY()).getEnemy()!=null) {
            map.getHexagon(hexagon.getX() + 1, hexagon.getY()).setBackground(Color.green);
            return false;
        }
        return true;
    }
    private boolean leftEnemy(Hexagon hexagon,Map map){
        if (map.getHexagon(hexagon.getX()-1,hexagon.getY()).getEnemy()!=null){
            map.getHexagon(hexagon.getX()-1,hexagon.getY()).setBackground(Color.green);
            return false;
        }
        return true;
    }
    private boolean downEnemy(Hexagon hexagon,Map map){
        if (map.getHexagon(hexagon.getX(),hexagon.getY()+1).getEnemy()!=null){
            map.getHexagon(hexagon.getX(),hexagon.getY()+1).setBackground(Color.green);
            return false;
        }
        return true;
    }
    private boolean upEnemy(Hexagon hexagon,Map map){
        if (map.getHexagon(hexagon.getX(),hexagon.getY()-1).getEnemy()!=null){
            map.getHexagon(hexagon.getX(),hexagon.getY()-1).setBackground(Color.green);
            return false;
        }
        return true;
    }
}