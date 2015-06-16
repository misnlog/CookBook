package cookbook;


import cookbook.GUI;
import cookbook.AddPanel;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mišel
 */
public class SuccessfullySavedPanel extends JPanel {

        JButton button;
        JLabel label;
        GUI gui;

        public SuccessfullySavedPanel(JFrame frame) {
            setBackground(new Color(248, 228, 159));
            setLayout(new FlowLayout());
            this.gui = (GUI) frame;
            this.label = new JLabel("Your recipe has been successfully saved.");
            this.button = new JButton("Add another recipe");
            add(label);
            add(button);

            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    gui.remove(gui.imagePanel);
                    try {
                        gui.imagePanel = new AddPanel(gui);
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    gui.add(gui.imagePanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();

                }
            });

        }
    }