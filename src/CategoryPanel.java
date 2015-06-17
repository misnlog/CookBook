/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author Mišel
 */
public class CategoryPanel extends JPanel {
    
    private String category;
    GUI gui;
    String nameDir;
    String substring;
    String name;

    public CategoryPanel(String categry, Frame frame) {
        setBackground(new Color(248, 228, 159));
        this.category = categry;
        this.gui = (GUI) frame;
        JMenu menu = new JMenu("Recent Files");
        
        
        
        try {
            Path startPath = Paths.get(System.getProperty("user.dir") + File.separator + ".recipes" + File.separator + getCategory() + File.separator);
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir,
                        BasicFileAttributes attrs) {
                    nameDir = dir.toString();
                    substring = nameDir.substring(nameDir.lastIndexOf(File.separator) + 1);
                    
                    
                    String segments[] = substring.split("_");

                    name = segments[0];
                   
                    if(!name.equals(category)){
                        Map<String, String> recipes = new HashMap<String, String>();
                        recipes.put(name, substring);
                        JLabel label = new JLabel(name);
                        label.setName(nameDir);
                        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));                        
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
    
    class RecentDir extends AbstractAction {

        Path dir;
        JLabel label;

        public RecentDir(Path dir, JLabel label) {
            this.dir = dir;
            this.label = label;
            this.putValue(Action.NAME, name);
            this.putValue(Action.SHORT_DESCRIPTION, nameDir);
        }

        public void actionPerformed(ActionEvent e) {
            label.setText(name);

        }

        public Action getAction() {
            return this;
        }

    }   
    
}