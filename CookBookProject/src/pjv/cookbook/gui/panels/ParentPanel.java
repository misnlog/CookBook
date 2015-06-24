/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjv.cookbook.gui.panels;

import com.thoughtworks.xstream.XStream;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.SoftBevelBorder;
import pjv.cookbook.gui.entrypoint.GUI;
import static pjv.cookbook.gui.panels.CategoryPanel.resize;
import pjv.cookbook.model.Recipe;
import pjv.cookbook.utils.PanelHelper;

/**
 *
 * @author Mišel
 */
public class ParentPanel extends JPanel implements PanelHelper {

    public GUI gui;

    public ParentPanel(JFrame frame) {
        this.gui = (GUI) frame;
    }
    
    public GUI getGui() {
        return gui;
    }
    
    

    @Override
    public void updatePanel(JPanel panel) {
        gui.remove(gui.imagePanel);
        gui.remove(gui.scroll);
        gui.imagePanel = panel;
        gui.add(gui.imagePanel, BorderLayout.CENTER);
        gui.revalidate();
        gui.repaint();
    }

    @Override
    public void updateScrollPanel(JPanel panel) {
        gui.remove(gui.imagePanel);
        gui.remove(gui.scroll);
        gui.imagePanel = panel;
        gui.scroll = new JScrollPane(gui.imagePanel);
        gui.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gui.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        gui.add(gui.scroll, BorderLayout.CENTER);
        gui.revalidate();
        gui.repaint();
    }

    @Override
    public Recipe loadRecipe(JLabel label) {
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
        return loadedRecipe;
    }

    @Override
    public void setCategoryLabel(JLabel label, String path) {
        label.setIcon(new ImageIcon(loadImage(path)));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        label.setName(path);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        label.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                JPanel panel = new RecipePanel(gui, loadRecipe(label));
                updateScrollPanel(panel);
            }
        });
    }
    
    @Override
    public void setCollectionLabel(JLabel label, BufferedImage iLabel, String category) {
        label.setIcon(new ImageIcon(iLabel));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        label.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                CollectionPanel panel = null;
                try {
                    panel = new CollectionPanel(gui);
                } catch (IOException ex) {
                    Logger.getLogger(ParentPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                panel.setCategory(category);
                if (gui == null){
                    System.out.println("setcollection - gui is null");
                }
                updateScrollPanel(new CategoryPanel(panel.getCategory(), gui));
            }
        });
    }

    @Override
    public BufferedImage loadImage(String path) {
        BufferedImage imageIcon = null;
        if (new File(path + File.separator + "image.jpg").exists()) {
            try {
                imageIcon = ImageIO.read(new File(path + File.separator + "image.jpg"));
            } catch (IOException ex) {
                Logger.getLogger(ParentPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                imageIcon = ImageIO.read(getClass().getClassLoader().getResource("images/foodicon.png"));
            } catch (IOException ex) {
                Logger.getLogger(ParentPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        double ratio = (double) imageIcon.getWidth() / imageIcon.getHeight();
        int width = (int) (150 * ratio);

        BufferedImage resizedImageIcon = resize(imageIcon, width, 150);
        return resizedImageIcon;
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
