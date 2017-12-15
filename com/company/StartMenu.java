package com.company;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends JDialog {
    StartMenu() {
        JLayeredPane lp = getLayeredPane();
        JPanel levels = new JPanel();
        levels.setBorder(BorderFactory.createTitledBorder("Menu"));
        levels.setBackground(Color.blue);
        levels.setBounds(375, 175, 300, 300);

        JButton newGame = new JButton("New game");
        newGame.setBounds(425, 200, 200, 50);
        JButton loadGame = new JButton("Load game");
        loadGame.setBounds(425, 300, 200, 50);
        JButton saveGame = new JButton("Save game");
        saveGame.setBounds(425, 400, 200, 50);

        lp.add(levels, JLayeredPane.PALETTE_LAYER);
        lp.add(newGame, JLayeredPane.POPUP_LAYER);
        lp.add(loadGame, JLayeredPane.POPUP_LAYER);
        lp.add(saveGame, JLayeredPane.POPUP_LAYER);
        //add(new JPanel());

        setMinimumSize(new Dimension(700, 700));
        setVisible(true);
    }
}
