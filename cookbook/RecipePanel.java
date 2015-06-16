package cookbook;


import cookbook.GUI;
import cookbook.AddPanel;
import com.thoughtworks.xstream.XStream;
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
class RecipePanel extends JPanel {

        JButton editButton;
        JButton deleteButton;
        GUI gui;
        XMLHelper helper;
        AddPanel addPanel;
        Recipe recipe;

        public RecipePanel(JFrame frame) {
            setBackground(new Color(248, 228, 159));
            setLayout(new FlowLayout());
            this.editButton = new JButton("Edit");
            this.deleteButton = new JButton("Delete");
            this.recipe = new Recipe();

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
                    addPanel.hashTags.setText(recipe.getHashtags().toString());
                    // pridat image - nazov
                    gui.imagePanel = addPanel;
                    gui.add(gui.imagePanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
            });

        }
    }
