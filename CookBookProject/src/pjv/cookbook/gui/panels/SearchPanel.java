package pjv.cookbook.gui.panels;

import com.thoughtworks.xstream.XStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
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
    int valueCounter = 0;
    int hashtagCounter = 0;
    Map<String, Integer> recipes;
    String nameDir;
    JRadioButton or;
    JRadioButton and;
    ButtonGroup buttonGroup;
    XMLHelper helper;
    JPanel foundRecipesPanel;

    public SearchPanel(JFrame frame) {
        setBackground(new Color(248, 228, 159));
        this.gui = (GUI) frame;
        setLayout(new MigLayout("wrap 3", "[][fill, grow]"));
        this.category = new JComboBox();
        category.setModel(new DefaultComboBoxModel(new String[]{"Select Category", "Appetizers", "Beef", "Beverages", "Desserts", "Fish", "Pasta", "Pork", "Poultry", "Salads", "Soups"}));
        category.setMaximumSize(new Dimension(200, 40));
        labelcategory = new JLabel("Search in category:   ");
        this.or = new JRadioButton("OR");
        this.and = new JRadioButton("AND");
        this.buttonGroup = new ButtonGroup();
        buttonGroup.add(or);
        buttonGroup.add(and);
        or.setSelected(true);
        or.setBackground(new Color(248, 228, 159));
        and.setBackground(new Color(248, 228, 159));
        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        radioButtonPanel.setBackground(new Color(248, 228, 159));
        radioButtonPanel.setSize(new Dimension(50, 30));
        radioButtonPanel.add(or);
        radioButtonPanel.add(and);

        this.setVisible(true);
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
        this.recipes = new HashMap<String, Integer>();
        this.foundRecipesPanel = new JPanel();
        foundRecipesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        foundRecipesPanel.setBackground(new Color(248, 228, 159));

        add(labelcategory);
        add(category);
        add(new JLabel(""));
        add(labelHashTag1);
        add(radioButtonPanel);
        add(new JLabel(""));
        add(new JLabel(""));
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

        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                if (category.getSelectedItem().equals("Select Category")) {
                    JOptionPane.showMessageDialog(gui, "Please select category.");
                } else {
                    try {
                        Path startPath = Paths.get(System.getProperty("user.dir") + File.separator + ".recipes" + File.separator + category.getSelectedItem().toString() + File.separator);

                        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                            @Override
                            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {

                                nameDir = dir.toString();
                                String substring = nameDir.substring(nameDir.lastIndexOf(File.separator) + 1);

                                String segments[] = substring.split("_");
                                String name = segments[0];

                                String[] hashTagsArray = new String[5];
                                hashTagsArray[0] = hashTag1.getText().replace("#", "").replace(" ", "").toLowerCase();
                                hashTagsArray[1] = hashTag2.getText().replace("#", "").replace(" ", "").toLowerCase();
                                hashTagsArray[2] = hashTag3.getText().replace("#", "").replace(" ", "").toLowerCase();
                                hashTagsArray[3] = hashTag4.getText().replace("#", "").replace(" ", "").toLowerCase();
                                hashTagsArray[4] = hashTag5.getText().replace("#", "").replace(" ", "").toLowerCase();

                                if (!name.equals(category.getSelectedItem().toString())) {

                                    if (hashTagsArray[0].isEmpty() && hashTagsArray[1].isEmpty() && hashTagsArray[2].isEmpty() && hashTagsArray[3].isEmpty() && hashTagsArray[4].isEmpty()) {
                                        recipes.put(nameDir, 1);
                                    } else {
                                        hashtagCounter = 0;
                                        for (int i = 0; i < 5; i++) {
                                            if (!hashTagsArray[i].isEmpty()) {
                                                hashtagCounter++;
                                                if (substring.toLowerCase().contains(hashTagsArray[i])) {
                                                    valueCounter++;
                                                }
                                            }
                                        }
                                        recipes.put(nameDir, valueCounter);
                                        valueCounter = 0;                                                                                
                                    }

                                }
                                
                                return FileVisitResult.CONTINUE;
                            }

                        });
                    } catch (IOException e) {
                        e.printStackTrace();

                    }

                    Map<String, Integer> sortedRecipes = sortMap(recipes);
                    for (Map.Entry<String, Integer> entry : sortedRecipes.entrySet()) {

                        if (and.isSelected()) {
                            if (entry.getValue().equals(hashtagCounter)) {
                                if (!entry.getValue().equals(0)) {
                                String segments[] = entry.getKey().substring(nameDir.lastIndexOf(File.separator) + 1).split("_");
                                String name = segments[0];
                                JLabel label = new JLabel(name);

                                BufferedImage imageIcon = null;

                                if (new File(entry.getKey() + File.separator + "image.jpg").exists()) {

                                    try {
                                        imageIcon = ImageIO.read(new File(entry.getKey() + File.separator + "image.jpg"));
                                    } catch (IOException ex) {
                                        Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    try {
                                        imageIcon = ImageIO.read(getClass().getClassLoader().getResource("images/foodicon.png"));
                                    } catch (IOException ex) {
                                        Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                double ratio = (double) imageIcon.getWidth() / imageIcon.getHeight();
                                int width = (int) (150 * ratio);

                                BufferedImage resizedImageIcon = CategoryPanel.resize(imageIcon, width, 150);

                                label.setIcon(new ImageIcon(resizedImageIcon));
                                label.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
                                label.setHorizontalAlignment(JLabel.CENTER);
                                label.setVerticalAlignment(JLabel.CENTER);
                                label.setHorizontalTextPosition(JLabel.CENTER);
                                label.setVerticalTextPosition(JLabel.BOTTOM);
                                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                label.setName(entry.getKey());
                                //label.setForeground(new Color(35, 116, 104));                         

                                label.addMouseListener(new MouseAdapter() {

                                    @Override
                                    public void mouseClicked(MouseEvent me) {

                                        JTextArea area = new JTextArea();
                                        FileReader fileReader = null;
                                        try {
                                            fileReader = new FileReader(label.getName() + File.separator + label.getText());
                                        } catch (FileNotFoundException ex) {
                                            Logger.getLogger(CategoryPanel.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        XStream xstream = new XStream();
                                        xstream.alias("Recipe", Recipe.class);
                                        Recipe loadedRecipe = (Recipe) xstream.fromXML(fileReader);

                                        gui.remove(gui.imagePanel);
                                        gui.remove(gui.scroll);
                                        try {
                                            gui.imagePanel = new RecipePanel(gui, loadedRecipe);
                                        } catch (HeadlessException ex) {
                                            Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        gui.add(gui.imagePanel, BorderLayout.CENTER);
                                        gui.revalidate();
                                        gui.repaint();
                                    }
                                });
                                foundRecipesPanel.add(label);
                            }
                        }
                                                           
                            

                        } else {

                            if (!entry.getValue().equals(0)) {
                                String segments[] = entry.getKey().substring(nameDir.lastIndexOf(File.separator) + 1).split("_");
                                String name = segments[0];
                                JLabel label = new JLabel(name);

                                BufferedImage imageIcon = null;

                                if (new File(entry.getKey() + File.separator + "image.jpg").exists()) {

                                    try {
                                        imageIcon = ImageIO.read(new File(entry.getKey() + File.separator + "image.jpg"));
                                    } catch (IOException ex) {
                                        Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    try {
                                        imageIcon = ImageIO.read(getClass().getClassLoader().getResource("images/foodicon.png"));
                                    } catch (IOException ex) {
                                        Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                double ratio = (double) imageIcon.getWidth() / imageIcon.getHeight();
                                int width = (int) (150 * ratio);

                                BufferedImage resizedImageIcon = CategoryPanel.resize(imageIcon, width, 150);

                                label.setIcon(new ImageIcon(resizedImageIcon));
                                label.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
                                label.setHorizontalAlignment(JLabel.CENTER);
                                label.setVerticalAlignment(JLabel.CENTER);
                                label.setHorizontalTextPosition(JLabel.CENTER);
                                label.setVerticalTextPosition(JLabel.BOTTOM);
                                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                label.setName(entry.getKey());
                                //label.setForeground(new Color(35, 116, 104));                         

                                label.addMouseListener(new MouseAdapter() {

                                    @Override
                                    public void mouseClicked(MouseEvent me) {

                                        JTextArea area = new JTextArea();
                                        FileReader fileReader = null;
                                        try {
                                            fileReader = new FileReader(label.getName() + File.separator + label.getText());
                                        } catch (FileNotFoundException ex) {
                                            Logger.getLogger(CategoryPanel.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        XStream xstream = new XStream();
                                        xstream.alias("Recipe", Recipe.class);
                                        Recipe loadedRecipe = (Recipe) xstream.fromXML(fileReader);

                                        gui.remove(gui.imagePanel);
                                        gui.remove(gui.scroll);
                                        try {
                                            gui.imagePanel = new RecipePanel(gui, loadedRecipe);
                                        } catch (HeadlessException ex) {
                                            Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        gui.add(gui.imagePanel, BorderLayout.CENTER);
                                        gui.revalidate();
                                        gui.repaint();
                                    }
                                });
                                foundRecipesPanel.add(label);
                            }
                        }
                    }
                    gui.remove(gui.imagePanel);
                    gui.remove(gui.scroll);
                    gui.imagePanel = foundRecipesPanel;
                    gui.add(gui.imagePanel, BorderLayout.CENTER);
                    gui.revalidate();
                    gui.repaint();
                }
            }
        }
        );
    }

    public static Map<String, Integer> sortMap(Map<String, Integer> unsortMap) {

        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

}
