/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjv.cookbook.gui.panels;

import com.thoughtworks.xstream.XStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import pjv.cookbook.gui.entrypoint.GUI;
import pjv.cookbook.model.Recipe;

/**
 *
 * @author Mišel
 */
public class CategoryPanel extends JPanel {

    private String category;
    GUI gui;
    BufferedImage imageIcon;

    public CategoryPanel(String categry, Frame frame) {
        setBackground(new Color(248, 228, 159));
        this.category = categry;
        this.gui = (GUI) frame;
        JMenu menu = new JMenu("Recent Files");
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        try {
            Path startPath = Paths.get(System.getProperty("user.dir") + File.separator + ".recipes" + File.separator + getCategory() + File.separator);
            File file = new File(startPath.toString());

            if (!file.exists()) {
                file.mkdirs();
                /*gui.remove(gui.imagePanel);
                 JPanel panel = new JPanel();
                 panel.add(new JLabel("No recipes found."));
                 gui.imagePanel = panel;
                 gui.add(gui.imagePanel, BorderLayout.CENTER);
                 gui.revalidate();
                 gui.repaint();*/

            } else {

                Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        String nameDir = dir.toString();
                        String substring = nameDir.substring(nameDir.lastIndexOf(File.separator) + 1);
                        String segments[] = substring.split("_");
                        String name = segments[0];
                        File file = new File(nameDir);

                        if (!name.equals(category)) {

                            JLabel label = new JLabel(name);

                            if (new File(nameDir + File.separator + "image.jpg").exists()) {

                                imageIcon = ImageIO.read(new File(nameDir + File.separator + "image.jpg"));
                            } else {
                                imageIcon = ImageIO.read(getClass().getClassLoader().getResource("images/foodicon.png"));
                            }

                            double ratio = (double) imageIcon.getWidth() / imageIcon.getHeight();
                            int width = (int) (150 * ratio);

                            BufferedImage resizedImageIcon = resize(imageIcon, width, 150);

                            label.setIcon(new ImageIcon(resizedImageIcon));
                            label.setHorizontalAlignment(JLabel.CENTER);
                            label.setVerticalAlignment(JLabel.CENTER);
                            label.setHorizontalTextPosition(JLabel.CENTER);
                            label.setVerticalTextPosition(JLabel.BOTTOM);
                            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                            label.setName(nameDir);
                            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            label.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
                            // label.setForeground(new Color(35, 116, 104));    
                            label.addMouseListener(new MouseAdapter() {

                                @Override
                                public void mouseClicked(MouseEvent me) {

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

                                    Recipe loadedRecipe = (Recipe) xstream.fromXML(fileReader);
                                    try {
                                        fileReader.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(CategoryPanel.class.getName()).log(Level.SEVERE, null, ex);
                                    }

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

    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

}
