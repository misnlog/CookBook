/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import com.thoughtworks.xstream.XStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.Configuration;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Mišel
 */
public class CategoryPanel extends JPanel {

    private String category;
    GUI gui;

    public CategoryPanel(String categry, Frame frame) {
        setBackground(new Color(248, 228, 159));
        this.category = categry;
        this.gui = (GUI) frame;
        JMenu menu = new JMenu("Recent Files");
        setLayout(new MigLayout("wrap", "[fill, grow]"));

        try {
            Path startPath = Paths.get(System.getProperty("user.dir") + File.separator + ".recipes" + File.separator + getCategory() + File.separator);
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir,
                        BasicFileAttributes attrs) {
                    String nameDir = dir.toString();
                    String substring = nameDir.substring(nameDir.lastIndexOf(File.separator) + 1);

                    String segments[] = substring.split("_");

                    String name = segments[0];

                    if (!name.equals(category)) {
                        //Map<String, String> recipes = new HashMap<String, String>();
                        //recipes.put(name, substring);
                        JLabel label = new JLabel(name);
                        label.setName(nameDir);
                        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        //label.setFont(new Font("Times New Roman", 0, 15));
                        label.addMouseListener(new MouseAdapter() {

                            @Override
                            public void mouseClicked(MouseEvent me) {
                                //Paths.get(label.getName());
                                //display recipe - dorobit
                                System.out.println(label.getName());
                                System.out.println(label.getText());
                                JTextArea area = new JTextArea();
                                FileReader fileReader = null;
                                try {
                                    fileReader = new FileReader(label.getName() + File.separator + label.getText());
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(CategoryPanel.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                XStream xstream = new XStream();
                                xstream.alias("Recipe", Recipe.class);
                                //File inputFile = new File(label.getName() + File.separator + label.getText());
                                Recipe loadedRecipe = (Recipe) xstream.fromXML(fileReader);

                                gui.remove(gui.imagePanel);
                                gui.imagePanel = new RecipePanel(gui, loadedRecipe);
                                gui.add(gui.imagePanel, BorderLayout.CENTER);
                                gui.revalidate();
                                gui.repaint();
                            }

                        });
                        add(label);
                        revalidate();

                    }

                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static void main(String[] args) throws HeadlessException, IOException {
        new CategoryPanel("Desserts", null);

    }

}
