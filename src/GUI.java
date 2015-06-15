
import com.thoughtworks.xstream.XStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
public class GUI extends JFrame {

    ButtonPanel panel;
    JPanel imagePanel;
    Dimension dimension;

    public GUI() throws HeadlessException, IOException {

        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }

        setTitle("Your Personal Cook Book");
        setSize(1000, 900);
        //Background
        dimension = this.getBounds().getSize();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Mišel\\Documents\\NetBeansProjects\\cookBook\\background.png");
        Image image = imageIcon.getImage();
        Image tmp = image.getScaledInstance(dimension.width - 220, dimension.height - 50, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(tmp);

        this.imagePanel = new JPanel();

        imagePanel.add(new JLabel(imageIcon));
        this.panel = new ButtonPanel(this);
        pack();
        setLayout(new BorderLayout());

        add(panel, BorderLayout.EAST);
        add(imagePanel, BorderLayout.CENTER);

        setSize(1000, 900);
        setVisible(true);
        //setResizable(false);

        //Look and Feel
        //Listenery
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });

        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent ce) {
                super.componentResized(ce); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }

    public static void main(String[] args) throws HeadlessException, IOException {
        new GUI();
        //new TmpFrame();

    }

    class ButtonPanel extends JPanel {

        JButton b1;
        JButton b2;
        JButton b3;
        JButton b4;
        JFrame book;
        GUI gui;

        public ButtonPanel(JFrame frame) throws HeadlessException, IOException {

            setBackground(new Color(187, 217, 189));
            this.gui = (GUI) frame;

            this.b1 = new JButton("Collection");
            this.b2 = new JButton("Search for recipe");
            this.b3 = new JButton("Add recipe");
            JButton[] buttons = new JButton[3];
            buttons[0] = b1;
            buttons[1] = b2;
            buttons[2] = b3;

            Box box = Box.createVerticalBox();

            for (JButton b : buttons) {
                b.setForeground(new Color(40, 124, 108));
                b.setAlignmentX(Component.LEFT_ALIGNMENT);
                b.setPreferredSize(new Dimension(200, 200));
                box.add(Box.createVerticalStrut(8));
                box.add(b);
            }

            add(box);

            b1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    gui.remove(imagePanel);
                    gui.imagePanel = new CollectionPanel(gui);
                    gui.add(imagePanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
            });

            b2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    gui.remove(imagePanel);
                    gui.imagePanel = new SearchPanel(gui);
                    gui.add(imagePanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
            });

            b3.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    gui.remove(imagePanel);
                    gui.imagePanel = new AddPanel(gui);
                    gui.add(imagePanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
            });
        }
    }

    class SearchPanel extends JPanel {

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

    class AddPanel extends JPanel {

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

        public AddPanel(JFrame frame) {
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
            //spytat sa Tonka na foreach

            imageButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {

                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("images", "jpg", "gif", "png");
                    fileChooser.setFileFilter(filter);
                    int returnVal = fileChooser.showOpenDialog(null);
                    imageFile = fileChooser.getSelectedFile();
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        if (imageFile.getName().endsWith(".jpg") || imageFile.getName().endsWith(".gif")) {
                            JOptionPane.showMessageDialog(null, "Image file");
                            System.out.println("You chose to open this file: "
                                    + fileChooser.getSelectedFile().getName());

                        } else {
                            JOptionPane.showMessageDialog(null, "select .jpg,.bmp or. gif");
                        }

                    }
                }
            }
            );

            saveButton.addActionListener(
                    new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            String[] hashTagsArray = hashTags.getText().split("\\s+");

                            Recipe recipe = new Recipe(name.getText(), category.getSelectedItem().toString(), ingredients.getText(), directions.getText(), timeOfPreparation.getText(), timeOfCooking.getText(), TOOL_TIP_TEXT_KEY, hashTagsArray, System.currentTimeMillis());
///opytat sa tonka ako pridat obrazok sem
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

    class CollectionPanel extends JPanel {

        JButton button;
        GUI gui;

        public CollectionPanel(JFrame frame) {
            setBackground(new Color(248, 228, 159));
            setLayout(new FlowLayout());
        }

    }

    class SuccessfullySavedPanel extends JPanel {

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
                    gui.remove(imagePanel);
                    gui.imagePanel = new AddPanel(gui);
                    gui.add(imagePanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();

                }
            });

        }
    }
}
