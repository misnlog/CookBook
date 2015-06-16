package cookbook;


import cookbook.GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mišel
 */
public class SearchPanel extends JPanel {

        JTextField textField1;
        JTextField textField2;
        JTextField textField3;
        JTextField textField4;
        JTextField textField5;
        JButton okButton;
        JButton plusButton1;
        JButton plusButton2;
        JButton plusButton3;
        JButton plusButton4;
        Box verticalBox;
        Box horizontalBox;
        int counter;
        JPanel centerPanel;
        JComboBox comboBox;
        GUI gui;
        int i = 0;

        public SearchPanel(JFrame frame) {
            setBackground(new Color(248, 228, 159));
            setLayout(new BorderLayout());
            comboBox = new JComboBox();
            comboBox.setModel(new DefaultComboBoxModel(new String[]{"Select Category", "Appetizers", "Beef", "Beverages", "Desserts", "Fish", "Pasta", "Pork", "Poultry", "Salads", "Soups"}));
            this.gui = (GUI) frame;
            centerPanel = new JPanel();
            centerPanel.setLayout(new FlowLayout());
            this.textField1 = new JTextField("Enter key word");
            this.textField2 = new JTextField("Enter key word");
            this.textField3 = new JTextField("Enter key word");
            this.textField4 = new JTextField("Enter key word");
            this.textField5 = new JTextField("Enter key word");

            this.okButton = new JButton("Search");
            this.plusButton1 = new JButton("+");
            this.plusButton2 = new JButton("+");
            this.plusButton3 = new JButton("+");
            this.plusButton4 = new JButton("+");

            this.verticalBox = Box.createVerticalBox();
            this.horizontalBox = Box.createHorizontalBox();
            horizontalBox.add(textField1);
            horizontalBox.add(plusButton1);

            verticalBox.add(horizontalBox);

            centerPanel.add(verticalBox);
            centerPanel.add(okButton);
            JPanel wrapPanel = new JPanel();
            wrapPanel.setLayout(new GridBagLayout());
            wrapPanel.add(comboBox);
            wrapPanel.add(centerPanel);
            this.add(wrapPanel, BorderLayout.NORTH);

            //this.add(comboBox, BorderLayout.NORTH);
            //this.add(centerPanel, BorderLayout.CENTER);
            //listenery
            plusButton1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    Box plusBox = Box.createHorizontalBox();
                    plusBox.add(textField2);
                    plusBox.add(plusButton2);
                    verticalBox.add(plusBox);
                    i++;
                    revalidate();
                }
            });

            plusButton2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    Box plusBox = Box.createHorizontalBox();
                    plusBox.add(textField3);
                    plusBox.add(plusButton3);
                    verticalBox.add(plusBox);
                    revalidate();
                }
            });

            plusButton3.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    Box plusBox = Box.createHorizontalBox();
                    plusBox.add(textField4);
                    plusBox.add(plusButton4);
                    verticalBox.add(plusBox);
                    revalidate();
                }
            });

            plusButton4.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    Box plusBox = Box.createHorizontalBox();
                    plusBox.add(textField5);
                    verticalBox.add(plusBox);
                    revalidate();
                }
            });

            textField1.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent fe) {
                    textField1.setText(null);
                }

                @Override
                public void focusLost(FocusEvent fe) {

                }
            });

            textField2.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent fe) {
                    textField2.setText(null);
                }

                @Override
                public void focusLost(FocusEvent fe) {

                }
            });

            textField3.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent fe) {
                    textField3.setText(null);
                }

                @Override
                public void focusLost(FocusEvent fe) {

                }
            });

            textField4.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent fe) {
                    textField4.setText(null);
                }

                @Override
                public void focusLost(FocusEvent fe) {

                }
            });

            textField5.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent fe) {
                    textField5.setText(null);
                }

                @Override
                public void focusLost(FocusEvent fe) {

                }
            });

        }  

    }