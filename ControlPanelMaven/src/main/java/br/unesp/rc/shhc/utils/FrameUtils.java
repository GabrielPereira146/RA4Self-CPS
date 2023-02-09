/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.shhc.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FrameUtils {

    private FrameUtils() {

    }

    /**
     * Este método é responsável por centralizar um JFrame na tela.
     * 
     * @param f é frame a ser centralizado.
     */
    public static void center(JFrame f) {
        Dimension screenSize
                = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(
                (screenSize.width - f.getWidth()) / 2,
                (screenSize.height - f.getHeight()) / 2);
        
    }

    public static void setLookMetal(JFrame f) {
        final String lnfName = "javax.swing.plaf.metal.MetalLookAndFeel";
        try {
            UIManager.setLookAndFeel(lnfName);
            SwingUtilities.updateComponentTreeUI(f);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            System.out.println("Message: " + ex.getMessage());
        }
    }
}
