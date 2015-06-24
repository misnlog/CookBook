package pjv.cookbook.gui.panels;

import com.sun.javafx.sg.prism.NGRectangle;
import com.thoughtworks.xstream.XStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.text.PlainDocument;
import net.miginfocom.swing.MigLayout;
import pjv.cookbook.gui.entrypoint.GUI;
import pjv.cookbook.model.Recipe;
import pjv.cookbook.utils.XMLHelper;

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
    JTextField hashTag1;
    JTextField hashTag2;
    JTextField hashTag3;
    JTextField hashTag4;
    JTextField hashTag5;
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
        this.gui = (GUI) frame;
        this.name = new JTextField();
        this.directions = new JTextArea(10, 10);       
        directions.setLineWrap(true);
        directions.setWrapStyleWord(true);

        this.ingredients = new JTextArea(10, 5);
        ingredients.setLineWrap(true);
        ingredients.setWrapStyleWord(true);
        
        this.timeOfPreparation = new JTextField();
        timeOfPreparation.setMaximumSize(new Dimension(70, 40));
        this.timeOfCooking = new JTextField();
        timeOfCooking.setMaximumSize(new Dimension(70, 40));
        this.hashTag1 = new JTextField("#");
        hashTag1.setMaximumSize(new Dimension(200, 40));

        this.hashTag2 = new JTextField("#");
        hashTag2.setMaximumSize(new Dimension(200, 40));

        this.hashTag3 = new JTextField("#");
        hashTag3.setMaximumSize(new Dimension(200, 40));

        this.hashTag4 = new JTextField("#");
        hashTag4.setMaximumSize(new Dimension(200, 40));

        this.hashTag5 = new JTextField("#");
        hashTag5.setMaximumSize(new Dimension(200, 40));

        this.category = new JComboBox();
        category.setEditable(true);
        category.setModel(new DefaultComboBoxModel(new String[]{"Select Category", "Appetizers", "Beef", "Beverages", "Desserts", "Fish", "Pasta", "Pork", "Poultry", "Salads", "Soups"}));
        this.imageButton = new JButton("Load image");

        this.saveButton = new JButton("Save");
        saveButton.setMaximumSize(new Dimension(250, 40));
        this.labelName = new JLabel("Name:");
        this.labelIngredients = new JLabel("Ingredients:");
        this.labelDirections = new JLabel("Directions:");
        this.labelTimeOfPreparation = new JLabel("Preparation time in minutes:  ");
        this.labelTimeOfCooking = new JLabel("Cooking time in minutes:");
        this.labelHashTag1 = new JLabel("Key words:");
        this.labelcategory = new JLabel("Category: ");
        this.helper = new XMLHelper(new XStream());
        this.collectionPanel = new CollectionPanel(gui);

        add(labelcategory);
        add(category);
        add(labelName);
        add(name);
        add(labelIngredients);
        add(ingredients);
        add(labelDirections);
        add(directions);
        add(labelTimeOfPreparation);
        add(timeOfPreparation);
        add(labelTimeOfCooking);
        add(timeOfCooking);
        add(labelHashTag1);
        add(hashTag1);
        add(new JLabel());
        add(hashTag2);
        add(new JLabel());
        add(hashTag3);
        add(new JLabel());
        add(hashTag4);
        add(new JLabel());
        add(hashTag5);
        add(imageButton);
        add(saveButton);

        PlainDocument doc1 = (PlainDocument) timeOfPreparation.getDocument();
        doc1.setDocumentFilter(new MyIntFilter());
        PlainDocument doc2 = (PlainDocument) timeOfCooking.getDocument();
        doc2.setDocumentFilter(new MyIntFilter());

        imageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("images", "jpg", "png", "gif", "PNG", "JPG");
                fileChooser.setFileFilter(filter);
                int returnVal = fileChooser.showOpenDialog(null);
                imageFile = fileChooser.getSelectedFile();
                if (returnVal == JFileChooser.APPROVE_OPTION) {

                    JLabel labelImageName = new JLabel(fileChooser.getSelectedFile().getName());
                    add(labelImageName);
                    imageName = fileChooser.getSelectedFile().getName();
                    revalidate();
                    repaint();

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

                        if (category.getSelectedItem().equals("Select Category")) {
                            JOptionPane.showMessageDialog(gui, "Please select category.");
                        } else {

                            String[] hashTagsArray = new String[5];
                            hashTagsArray[0] = hashTag1.getText().replace("#", "");
                            hashTagsArray[1] = hashTag2.getText().replace("#", "");
                            hashTagsArray[2] = hashTag3.getText().replace("#", "");
                            hashTagsArray[3] = hashTag4.getText().replace("#", "");
                            hashTagsArray[4] = hashTag5.getText().replace("#", "");

                            long timestamp = System.currentTimeMillis();

                            recipe = new Recipe(name.getText(), category.getSelectedItem().toString(), ingredients.getText(), directions.getText(), timeOfPreparation.getText(), timeOfCooking.getText(), imageName, hashTagsArray, timestamp);

                            try {
                                helper.saveRecipe(recipe);
                            } catch (IOException ex) {
                                Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            Path tmpImagePath = Paths.get(System.getProperty("java.io.tmpdir") + File.separator + "image.png");

                            if (imageFile != null) {
                                try {

                                    Files.copy(imageFile.toPath(), helper.getImagePath(recipe), REPLACE_EXISTING);
                                    if (new File(tmpImagePath.toString()).exists()) {
                                        Files.delete(tmpImagePath);
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else if (new File(System.getProperty("java.io.tmpdir") + File.separator + "image.png").exists()) {

                                Path target = helper.getImagePath(recipe);
                                try {
                                    Files.move(tmpImagePath, target, REPLACE_EXISTING);
                                } catch (IOException ex) {
                                    Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                            gui.remove(gui.imagePanel);
                            gui.remove(gui.scroll);
                            gui.imagePanel = new SavedRecipePanel(gui);
                            gui.add(gui.imagePanel, BorderLayout.CENTER);
                            gui.revalidate();
                            gui.repaint();

                        }

                    }
                });
    }

    class MyIntFilter extends DocumentFilter {

        @Override
        public void insertString(FilterBypass fb, int offset, String string,
                AttributeSet attr) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);

            if (test(sb.toString())) {
                super.insertString(fb, offset, string, attr);
            } else {
                // warn the user and don't allow the insert
            }
        }

        private boolean test(String text) {
            try {
                Integer.parseInt(text);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text,
                AttributeSet attrs) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if (test(sb.toString())) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                // warn the user and don't allow the insert
            }

        }

        public void remove(FilterBypass fb, int offset, int length)
                throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.delete(offset, offset + length);

            if (test(sb.toString())) {
                super.remove(fb, offset, length);
            } else {
                // warn the user and don't allow the insert
            }

        }
    }

}
