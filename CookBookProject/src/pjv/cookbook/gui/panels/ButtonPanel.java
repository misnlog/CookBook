package pjv.cookbook.gui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pjv.cookbook.gui.entrypoint.GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mišel
 */
public class ButtonPanel extends ParentPanel {

        JButton b1;
        JButton b2;
        JButton b3;
        JButton b4;
        JFrame book;
        GUI gui;

        public ButtonPanel(JFrame frame) throws HeadlessException, IOException {
            super(frame);
            setBackground(new Color(187, 217, 189));           

            this.b1 = new JButton("Collection");
            this.b2 = new JButton("Search for recipe");
            this.b3 = new JButton("Add recipe");
            JButton[] buttons = new JButton[3];
            buttons[0] = b1;
            buttons[1] = b2;
            buttons[2] = b3;

            Box box = Box.createVerticalBox();

            for (JButton b : buttons) {
                b.setForeground(new Color(44, 141, 126));
                b.setAlignmentX(Component.LEFT_ALIGNMENT);
                b.setPreferredSize(new Dimension(200, 200));
                box.add(Box.createVerticalStrut(8));
                box.add(b);
            }

            add(box);

            b1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {                   
                    JPanel panel = null;
                    try {
                        panel = new CollectionPanel(gui);
                        if (gui == null){
                    System.out.println("button - gui is null");
                }
                    } catch (IOException ex) {
                        Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    updatePanel(panel);
                }
            });

            b2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    JPanel panel = null;
                    try {
                        panel = new SearchPanel(gui);
                    } catch (HeadlessException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    updateScrollPanel(panel);
                }
            });

            b3.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {                    
                    JPanel panel = null;
                    try {
                        panel = new AddPanel(gui);
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    updateScrollPanel(panel);
                }
            });
        }
}
