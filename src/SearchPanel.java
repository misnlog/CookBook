package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

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

    JComboBox category;
    JLabel labelcategory;
    GUI gui;
    JTextField hashTag1;
    JTextField hashTag2;
    JTextField hashTag3;
    JTextField hashTag4;
    JTextField hashTag5;
    JLabel labelHashTag1;
    JButton plusButton;
    JButton searchButton;
    int counter = 0;

    public SearchPanel(JFrame frame) {
        setBackground(new Color(248, 228, 159));
        this.gui = (GUI) frame;
        setLayout(new MigLayout("wrap 3", "[][fill, grow]"));
        category = new JComboBox();
        category.setModel(new DefaultComboBoxModel(new String[]{"Select Category", "Appetizers", "Beef", "Beverages", "Desserts", "Fish", "Pasta", "Pork", "Poultry", "Salads", "Soups"}));
        category.setMaximumSize(new Dimension(200, 40));
        labelcategory = new JLabel("Search in category:   ");
        this.labelHashTag1 = new JLabel("Add key words:");
        this.hashTag1 = new JTextField("#");
        //hashTag1.setMaximumSize(new Dimension(200, 40));

        this.hashTag2 = new JTextField("#");
        //hashTag2.setMaximumSize(new Dimension(200, 40));

        this.hashTag3 = new JTextField("#");
        //hashTag3.setMaximumSize(new Dimension(200, 40));

        this.hashTag4 = new JTextField("#");
        //hashTag4.setMaximumSize(new Dimension(200, 40));

        this.hashTag5 = new JTextField("#");
        //hashTag5.setMaximumSize(new Dimension(200, 40));

        this.plusButton = new JButton("+");
        plusButton.setMaximumSize(new Dimension(40, 40));
        this.searchButton = new JButton("Search");

        add(labelcategory);
        add(category);
        add(new JLabel(""));
        add(labelHashTag1);
        add(hashTag1);
        add(plusButton);
        add(new JLabel(""));
        add(searchButton);

        plusButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (counter == 0) {
                    //add(new JLabel(""));
                    add(hashTag2);
                    add(new JLabel(""));
                    add(new JLabel(""));
                    add(searchButton);
                    add(new JLabel(""));
                    revalidate();
                    counter++;
                } else if (counter == 1) {
                    add(new JLabel(""));
                    add(new JLabel(""));
                    add(hashTag3);
                    add(new JLabel(""));
                    add(new JLabel(""));
                    add(searchButton);
                    add(new JLabel(""));
                    revalidate();
                    counter++;
                } else if (counter == 2) {
                    add(new JLabel(""));
                    add(new JLabel(""));
                    add(hashTag4);
                    add(new JLabel(""));
                    add(new JLabel(""));
                    add(searchButton);
                    add(new JLabel(""));
                    revalidate();
                    counter++;
                } else if (counter == 3) {
                    add(new JLabel(""));
                    add(new JLabel(""));
                    add(hashTag5);
                    add(new JLabel(""));
                    add(new JLabel(""));
                    add(searchButton);
                    add(new JLabel(""));
                    revalidate();
                    counter++;
                }

            }
        });

    }

}
