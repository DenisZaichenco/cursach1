package com.company;

import javax.swing.*;

public class BagDialogWindow extends JDialog {
public static void fileReadError(String address){
    JOptionPane.showMessageDialog(null,"Error from reading file "+address,"Read Error",JOptionPane.ERROR_MESSAGE);
}
public static void dontFoundType(String type){
    JOptionPane.showMessageDialog(null,"Error from reading type "+type,"Error from type",JOptionPane.ERROR_MESSAGE);
}
}
