package cookbook;


import com.thoughtworks.xstream.XStream;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
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
public class AddPanel extends JPanel {

        GUI gui;
        JTextField name;
        JTextArea ingredients;
        JTextArea directions;
        JTextField timeOfPreparation;
        JTextField timeOfCooking;
        JButton imageButton;
        JTextField hashTags;
        JComboBox category;

        JLabel labelName;
        JLabel labelIngredients;
        JLabel labelDirections;
        JLabel labelTimeOfPreparation;
        JLabel labelTimeOfCooking;
        JLabel labelHashTag1;
        JLabel labelcategory;

        JTextArea textarea;
        JLabel labeltextare;
        JButton saveButton;
        XMLHelper helper;
        File imageFile;
        CollectionPanel collectionPanel;
        Recipe recipe;
        String imageName;

        public AddPanel(JFrame frame) throws IOException {
            setBackground(new Color(248, 228, 159));

            setLayout(new MigLayout("wrap 2", "[][fill, grow]"));
            this.name = new JTextField();
            //name.setBorder( new EtchedBorder( EtchedBorder.LOWERED ) );
            this.directions = new JTextArea(10, 10);
            this.ingredients = new JTextArea(10, 5);
            this.timeOfPreparation = new JTextField();
            this.timeOfCooking = new JTextField();
            this.hashTags = new JTextField();

            this.category = new JComboBox();
            category.setEditable(true);
            category.setModel(new DefaultComboBoxModel(new String[]{"Select Category", "Appetizers", "Beef", "Beverages", "Desserts", "Fish", "Pasta", "Pork", "Poultry", "Salads", "Soups"}));
            this.imageButton = new JButton("Load image");
            this.gui = (GUI) frame;
            this.saveButton = new JButton("Save");

            this.labelName = new JLabel("Name:");

            this.labelIngredients = new JLabel("Ingredients:");
            this.labelDirections = new JLabel("Directions:");
            this.labelTimeOfPreparation = new JLabel("Preparation time:");
            this.labelTimeOfCooking = new JLabel("Cooking time:");
            this.labelHashTag1 = new JLabel("Key words:");
            this.labelcategory = new JLabel("Category: ");
            this.helper = new XMLHelper(new XStream());
            this.collectionPanel = new CollectionPanel(gui);

            List<JComponent> components = new ArrayList<JComponent>();

            components.add(labelcategory);
            components.add(category);
            components.add(labelName);
            components.add(name);
            components.add(labelIngredients);
            components.add(ingredients);
            components.add(labelDirections);
            components.add(directions);
            components.add(labelTimeOfPreparation);
            components.add(timeOfPreparation);
            components.add(labelTimeOfCooking);
            components.add(timeOfCooking);
            components.add(labelHashTag1);
            components.add(hashTags);

            components.add(imageButton);
            components.add(saveButton);

            for (JComponent c : components) {
                this.add(c);
            }
           
            imageButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {

                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("images", "jpg", "png", "gif", "PNG", "JPG");
                    fileChooser.setFileFilter(filter);
                    int returnVal = fileChooser.showOpenDialog(null);
                    imageFile = fileChooser.getSelectedFile();
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        
                            JLabel labelImageName = new JLabel("You chose to open this file: " + fileChooser.getSelectedFile().getName());
                            add(labelImageName);
                            imageName = fileChooser.getSelectedFile().getName();
                           

                        } else {
                            JOptionPane.showMessageDialog(null, "select .jpg,.png or. gif");
                        }                    
                }
            }
            );

            saveButton.addActionListener(
                    new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            String[] hashTagsArray = hashTags.getText().split("\\s+");

                            recipe = new Recipe(name.getText(), category.getSelectedItem().toString(), ingredients.getText(), directions.getText(), timeOfPreparation.getText(), timeOfCooking.getText(), imageName, hashTagsArray, System.currentTimeMillis());

                            BufferedImage myPicture = null;
                            try {
                                myPicture = ImageIO.read(new File(imageFile.getAbsolutePath()));
                            } catch (IOException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                            add(picLabel);
                            revalidate();
                            repaint();
                            collectionPanel.add(picLabel);
                            collectionPanel.revalidate();
                            collectionPanel.repaint();

                            try {
                                helper.saveRecipe(recipe);
                                /*try d
                                 BufferedWriter outfile = new BufferedWriter(new FileWriter("text.txt"));
                        
                                 outfile.write(name.getText());
                                 outfile.newLine();
                                 outfile.newLine();
                                 outfile.write("Category: ");
                                 outfile.write(category.getSelectedItem().toString());
                                 outfile.write("Ingredients:");
                                 outfile.newLine();
                                 outfile.write(ingredients.getText());
                                 outfile.newLine();
                                 outfile.newLine();
                                 outfile.write("Directions:");
                                 outfile.newLine();
                                 outfile.write(directions.getText());
                                 outfile.newLine();
                                 outfile.newLine();
                                 outfile.write("Preparation time: ");
                                 outfile.write(timeOfPreparation.getText());
                                 outfile.newLine();
                                 outfile.write("Cooking time: ");
                                 outfile.write(timeOfCooking.getText());
                                 outfile.newLine();
                                 outfile.write("#");
                                 outfile.write(hashTag1.getText());
                                 outfile.close();

                                 } catch (IOException ex) {
                                 Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                 }
                        
                                 gui.remove(imagePanel);
                                 gui.imagePanel = new SuccessfullySavedPanel(gui);
                                 gui.add(imagePanel, BorderLayout.CENTER);
                                 revalidate();
                                 repaint();*/
                            } catch (IOException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    });
        }
    }
