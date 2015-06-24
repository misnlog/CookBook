/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjv.cookbook.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pjv.cookbook.gui.entrypoint.GUI;

/**
 *
 * @author Mišel
 */
public class SavedRecipePanel extends JPanel { 
    
        JButton button;
        JLabel label;
        GUI gui;

        public SavedRecipePanel(JFrame frame) {
            setBackground(new Color(248, 228, 159));
            setLayout(new FlowLayout());
            this.gui = (GUI) frame;
            this.label = new JLabel("Your recipe was successfully saved.");
            this.button = new JButton("Add another recipe");
            add(label);
            add(button);

            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    gui.remove(gui.imagePanel);
                    gui.remove(gui.scroll);
                    try {
                        gui.imagePanel = new AddPanel(gui);
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    gui.add(gui.imagePanel, BorderLayout.CENTER);
                    gui.revalidate();
                    gui.repaint();

                }
            });

        }
    }