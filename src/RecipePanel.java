package src;

import src.GUI;
import src.AddPanel;
import com.thoughtworks.xstream.XStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
class RecipePanel extends JPanel {

    JButton editButton;
    JButton deleteButton;
    GUI gui;
    XMLHelper helper;
    AddPanel addPanel;
    Recipe recipe;
    JLabel name;
    JLabel category;
    JTextArea ingredients;
    JTextArea directions;
    JLabel timeOfPreparation;
    JLabel timeOfCooking;
    JLabel hashtags;
    JLabel lingredients;
    JLabel ldirections;

    public RecipePanel(JFrame frame, Recipe recipe) {
        setBackground(new Color(248, 228, 159));
        setLayout(new MigLayout("wrap", "[fill, grow]"));
        this.gui = (GUI) frame;
        this.editButton = new JButton("Edit");
        editButton.setMaximumSize(new Dimension(130, 40));
        this.deleteButton = new JButton("Delete");
        deleteButton.setMaximumSize(new Dimension(130, 40));
        this.recipe = new Recipe();
        this.name = new JLabel(recipe.getName());
        name.setFont(new Font("Calibri", 10, 30));
        name.setForeground(new Color(35, 116,104));
        this.category = new JLabel("Category: " + recipe.getCategory());
        category.setFont(new Font("Calibri", 10, 20));
        category.setForeground(new Color(220,86,74));
        this.lingredients = new JLabel("Ingredients: ");
        lingredients.setFont(new Font("Calibri", 10, 15));
        lingredients.setForeground(new Color(108, 92, 89));
        this.ingredients = new JTextArea(recipe.getIngredients() + "\n");
        ingredients.setEditable(false);
        ingredients.setBackground(new Color(248, 228, 159));
        ingredients.setLineWrap(true);
        ingredients.setOpaque(false);
        ingredients.setBorder(BorderFactory.createEmptyBorder());
        this.ldirections = new JLabel("Directions: ");
        ldirections.setFont(new Font("Calibri", 10, 15));
        ldirections.setForeground(new Color(108, 92, 89));
        this.directions = new JTextArea(recipe.getDirections() + "\n");
        directions.setEditable(false);
        directions.setBackground(new Color(248, 228, 159));
        directions.setLineWrap(true);
        directions.setOpaque(false);
        directions.setBorder(BorderFactory.createEmptyBorder());
        this.timeOfPreparation = new JLabel("Preparation time: " + recipe.getTimeOfPreparation() + " min");
        timeOfPreparation.getFont().deriveFont(64.0f);
        this.timeOfCooking = new JLabel("Cooking time: " + recipe.getTimeOfCooking() + " min");
        String tmp = "";
        for (int i = 0; i < recipe.getHashtags().length; i++) {
            if (!recipe.getHashtags()[i].equals("")) {
                tmp += "#" + recipe.getHashtags()[i] + " ";
            }
        }
        this.hashtags = new JLabel("<html><b>" + tmp + "<b></html>");
        hashtags.setForeground(new Color(108, 92, 89));

        add(name);
        add(new JLabel(""));
        add(category);
        add(new JLabel(""));
        add(lingredients);
        add(ingredients);
        add(new JLabel(""));
        add(ldirections);
        add(directions);
        add(new JLabel(""));
        if (!recipe.getTimeOfPreparation().equals("")) {
            add(timeOfPreparation);
        }
        if (!recipe.getTimeOfCooking().equals("")) {

            add(timeOfCooking);
        }
        add(new JLabel(""));
        add(hashtags);
        add(new JLabel(""));
        add(new JLabel(""));
        add(editButton);
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                helper = new XMLHelper(new XStream());
                helper.deleteRecipe(recipe);

                gui.remove(gui.imagePanel);
                try {
                    gui.imagePanel = new CollectionPanel(gui);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                gui.add(gui.imagePanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        editButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                gui.remove(gui.imagePanel);
                try {
                    addPanel = new AddPanel(gui);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                addPanel.name.setText(recipe.getName());
                addPanel.category.setSelectedItem(recipe.getCategory());
                addPanel.ingredients.setText(recipe.getIngredients());
                addPanel.directions.setText(recipe.getDirections());
                addPanel.timeOfPreparation.setText(recipe.getTimeOfPreparation());
                addPanel.timeOfCooking.setText(recipe.getTimeOfCooking());
                //addPanel.hashTags.setText(recipe.getHashtags().toString());
                // pridat image - nazov
                addPanel.hashTag1.setText(recipe.getHashtags()[0]);
                addPanel.hashTag2.setText(recipe.getHashtags()[1]);
                addPanel.hashTag3.setText(recipe.getHashtags()[2]);
                addPanel.hashTag4.setText(recipe.getHashtags()[3]);
                addPanel.hashTag5.setText(recipe.getHashtags()[4]);
                        
                gui.imagePanel = addPanel;
                gui.add(gui.imagePanel, BorderLayout.CENTER);
                gui.revalidate();
                repaint();
            }
        });

    }
}
