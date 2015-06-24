/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjv.cookbook.gui.panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Mišel
 */
public class CategoryPanel extends ParentPanel {

    private String category;  
    JLabel recipeLabel;   

    
    public CategoryPanel(String category, JFrame frame) {
        super(frame);         
        setBackground(new Color(248, 228, 159));
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));        
        this.category = category;     

        try {
            Path startPath = Paths.get(System.getProperty("user.dir") + File.separator + ".recipes" + File.separator + getCategory() + File.separator);
            File file = new File(startPath.toString());

            if (!file.exists()) {
                file.mkdirs();
                //povedat uzivatelovi ze kategoria je prazdna

            } else {

                Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        String nameDir = dir.toString();
                        String substring = nameDir.substring(nameDir.lastIndexOf(File.separator) + 1);
                        String segments[] = substring.split("_");
                        String name = segments[0];                        

                        if (!name.equals(category)) {
                            recipeLabel = new JLabel(name);  
                            setCategoryLabel(recipeLabel, nameDir);
                            add(recipeLabel);
                            if (gui == null){
                    System.out.println("categorypanel - gui is null");
                }
                            gui.revalidate();
                            gui.repaint();
                        }
                        return FileVisitResult.CONTINUE;
                    }
                }
                );
            }
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
}
