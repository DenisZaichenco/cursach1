package com.company.WorcWithHexagon.Attack_Swap;

import com.company.Army;
import com.company.File_processing;
import com.company.Squat;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class FitePanel extends JDialog {
    private AttackPanel playerPanel;
    private AttackPanel enemyPanel;
    public FitePanel(Army player, Army enemy, int rowNumber, String address_of_recruit)throws IOException{
        setSize(650,645);
        setResizable(false);

        playerPanel = new AttackPanel(player,address_of_recruit,rowNumber,false);
        enemyPanel = new AttackPanel(enemy,address_of_recruit,rowNumber,true);

        playerPanel.setBounds(5,5,560,295);
        enemyPanel.setBounds(5,305,560,295);

        createInitiative(rowNumber);

        JPanel background = new JPanel();
        background.setBackground(Color.white);

        JPanel commandPanel = createCommandPanel();

        JButton attack = new JButton("Attack");
        attack.setBounds(570,569,30,30);
        attack.addActionListener(e -> {
           Squat.activeSquat.setNumber(0);
           deadUnit(playerPanel,rowNumber);
           deadUnit(enemyPanel,rowNumber);
            // System.out.println("Attack");
        });

        JButton menu = new JButton("?");
        menu.setBounds(625,5,15,15);
        menu.addActionListener(e -> {
            System.out.println("Work");
        });

        add(playerPanel);
        add(enemyPanel);

        add(menu);
        add(attack);
        add(commandPanel);

        add(background);
        attackUnite(enemyPanel,playerPanel.getRows()[2].getSquatPanels().get(0),rowNumber,Color.green);
        setBackground(Color.white);
        setVisible(true);
    }
    private JPanel createCommandPanel(){
        JPanel commandPanel = new JPanel();
        JButton attack = new JButton();
        attack.addActionListener(e -> {
            Squat.activeSquat.attack(Squat.attackSquat);
            Squat.attackSquat.attack(Squat.activeSquat);
        });
        commandPanel.setLayout(new CardLayout(1,1));
        commandPanel.add(attack);
        return commandPanel;
    }
    private JScrollPane createInitiativePanel(int rowNumber){
        JPanel initiative = new JPanel();
        int max = 0;
        for (int i = 0; i <rowNumber; i++) {
            max += playerPanel.getRows()[i].getSquatPanels().size();
            max += enemyPanel.getRows()[i].getSquatPanels().size();
        }
        ArrayList<Squat> initiativeElements = new ArrayList<>();
        initiative.setLayout(new GridLayout(max,1));
        for (int i = 0; i < rowNumber; i++) {
            initiativeElements.addAll(playerPanel.getRows()[i].getSquatPanels());
            initiativeElements.addAll(enemyPanel.getRows()[i].getSquatPanels());
        }
        initiativeElements.sort(COMPARE_BY_COUNT);
        Collections.reverse(initiativeElements);
        for (Squat initiativeElement : initiativeElements) {
            initiative.add(initiativeElement);
        }
        for (int i = 0; i <initiativeElements.size() ; i++) {
            initiativeElements.get(i).setNumber_of_initiative_list(i+1);
        }
        return new JScrollPane(initiative);
    }
    private void createInitiative(int rowNumber){
        int max = 0;
        for (int i = 0; i <rowNumber; i++) {
            max += playerPanel.getRows()[i].getSquatPanels().size();
            max += enemyPanel.getRows()[i].getSquatPanels().size();
        }
        ArrayList<Squat> initiativeElements = new ArrayList<>();
        for (int i = 0; i < rowNumber; i++) {
            initiativeElements.addAll(playerPanel.getRows()[i].getSquatPanels());
            initiativeElements.addAll(enemyPanel.getRows()[i].getSquatPanels());
        }
        initiativeElements.sort(COMPARE_BY_COUNT);
        Collections.reverse(initiativeElements);
        for (int i = 0; i <initiativeElements.size() ; i++) {
            initiativeElements.get(i).setNumber_of_initiative_list(i+1);
        }
    }
    private final Comparator<Squat> COMPARE_BY_COUNT = new Comparator<Squat>() {
        @Override
        public int compare(Squat lhs, Squat rhs) {
            return lhs.getUnite().getInitiative() - rhs.getUnite().getInitiative();
        }
    };
    private void attackUniteActive(AttackPanel enemy,Color color,int startRow,int endRow){
        for (int i = startRow; i < endRow; i++) {
            if (enemy.getRows()[i] != null)
            for (int j = 0; j < enemy.getRows()[i].getSquatPanels().size(); j++) {
                if(enemy.getRows()[i].getSquatPanels().get(j)!=null)
                    enemy.getRows()[i].getSquatPanels().get(j).setBackground(color);
            }
        }
    }
    private void attackUnite(AttackPanel enemy, Squat activeUnit,int rowNumber,Color color){
        int startRow = 0;
        int row = activeUnit.getUnite().getRow();
        while (enemy.getRows() == null && startRow!=rowNumber){
            startRow++;
        }
        if (startRow+row<=rowNumber){
            attackUniteActive(enemy,color,startRow,startRow+row);
        }
        else if (startRow+row>rowNumber){
            attackUniteActive(enemy,color,startRow,rowNumber);
        }
    }
    private void deadUnit(AttackPanel panel,int rowNumber){
        for (int i = 0; i <rowNumber ; i++) {
            if (panel.getRows()[i]!=null){
                for (int j = 0; j <panel.getRows()[i].getSquatPanels().size() ; j++) {
                    if (panel.getRows()[i].getSquatPanels().get(j).getNumber()==0){
                        for (int k =j+1; k <panel.getRows()[i].getSquatPanels().size()-1; k++) {
                            panel.getRows()[i].getSquatPanels().set(k-1,panel.getRows()[i].getSquatPanels().get(k));
                        }
                    }
                }
            }
        }
    }
}