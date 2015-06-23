package pjv.cookbook.gui.panels;

import java.awt.BorderLayout;
import pjv.cookbook.gui.entrypoint.GUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class CollectionPanel extends JPanel {

    JButton button;
    GUI gui;
    JLabel lAppetizers;
    JLabel lBeef;
    JLabel lBeverages;
    JLabel lDesserts;
    JLabel lFish;
    JLabel lPasta;
    JLabel lPork;
    JLabel lPoultry;
    JLabel lSalads;
    JLabel lSoups;
    String category;

    public CollectionPanel(JFrame frame) throws IOException {
        setBackground(new Color(248, 228, 159));
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        this.gui = (GUI) frame;
        //InputStream source = this.getClass().getResourceAsStream("test.txt");

//InputStream source = this.getClass().getResourceAsStream("/test.txt");
        /*if (source == null) {
         System.out.println("source is null");
         } else {
         System.out.println("source is not null");
         }*/
        BufferedImage iAppetizers = ImageIO.read(getClass().getClassLoader().getResource("images/appetizers.png"));
        this.lAppetizers = new JLabel("Appetizers");
        lAppetizers.setIcon(new ImageIcon(iAppetizers));
        lAppetizers.setHorizontalAlignment(JLabel.CENTER);
        lAppetizers.setVerticalAlignment(JLabel.CENTER);
        lAppetizers.setHorizontalTextPosition(JLabel.CENTER);
        lAppetizers.setVerticalTextPosition(JLabel.BOTTOM);
        lAppetizers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(lAppetizers);

        BufferedImage iBeef = ImageIO.read(getClass().getClassLoader().getResource("images/beef.png"));
        this.lBeef = new JLabel("Beef");
        lBeef.setIcon(new ImageIcon(iBeef));
        lBeef.setHorizontalAlignment(JLabel.CENTER);
        lBeef.setVerticalAlignment(JLabel.CENTER);
        lBeef.setHorizontalTextPosition(JLabel.CENTER);
        lBeef.setVerticalTextPosition(JLabel.BOTTOM);
        lBeef.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(lBeef);

        BufferedImage iBeverages = ImageIO.read(getClass().getClassLoader().getResource("images/beverages.png"));
        this.lBeverages = new JLabel("Beverages");
        lBeverages.setIcon(new ImageIcon(iBeverages));
        lBeverages.setHorizontalAlignment(JLabel.CENTER);
        lBeverages.setVerticalAlignment(JLabel.CENTER);
        lBeverages.setHorizontalTextPosition(JLabel.CENTER);
        lBeverages.setVerticalTextPosition(JLabel.BOTTOM);
        lBeverages.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(lBeverages);

        BufferedImage iDesserts = ImageIO.read(getClass().getClassLoader().getResource("images/desserts.png"));
        this.lDesserts = new JLabel("Desserts");
        lDesserts.setIcon(new ImageIcon(iDesserts));
        lDesserts.setHorizontalAlignment(JLabel.CENTER);
        lDesserts.setVerticalAlignment(JLabel.CENTER);
        lDesserts.setHorizontalTextPosition(JLabel.CENTER);
        lDesserts.setVerticalTextPosition(JLabel.BOTTOM);
        lDesserts.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(lDesserts);

        BufferedImage iFish = ImageIO.read(getClass().getClassLoader().getResource("images/fish.png"));
        this.lFish = new JLabel("Fish");
        lFish.setIcon(new ImageIcon(iFish));
        lFish.setHorizontalAlignment(JLabel.CENTER);
        lFish.setVerticalAlignment(JLabel.CENTER);
        lFish.setHorizontalTextPosition(JLabel.CENTER);
        lFish.setVerticalTextPosition(JLabel.BOTTOM);
        lFish.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(lFish);

        BufferedImage iPasta = ImageIO.read(getClass().getClassLoader().getResource("images/pasta.png"));
        this.lPasta = new JLabel("Pasta");
        lPasta.setIcon(new ImageIcon(iPasta));
        lPasta.setHorizontalAlignment(JLabel.CENTER);
        lPasta.setVerticalAlignment(JLabel.CENTER);
        lPasta.setHorizontalTextPosition(JLabel.CENTER);
        lPasta.setVerticalTextPosition(JLabel.BOTTOM);
        lPasta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(lPasta);

        BufferedImage iPork = ImageIO.read(getClass().getClassLoader().getResource("images/pork.png"));
        this.lPork = new JLabel("Pork");
        lPork.setIcon(new ImageIcon(iPork));
        lPork.setHorizontalAlignment(JLabel.CENTER);
        lPork.setVerticalAlignment(JLabel.CENTER);
        lPork.setHorizontalTextPosition(JLabel.CENTER);
        lPork.setVerticalTextPosition(JLabel.BOTTOM);
        lPork.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(lPork);

        BufferedImage iPoultry = ImageIO.read(getClass().getClassLoader().getResource("images/poultry.png"));
        this.lPoultry = new JLabel("Poultry");
        lPoultry.setIcon(new ImageIcon(iPoultry));
        lPoultry.setHorizontalAlignment(JLabel.CENTER);
        lPoultry.setVerticalAlignment(JLabel.CENTER);
        lPoultry.setHorizontalTextPosition(JLabel.CENTER);
        lPoultry.setVerticalTextPosition(JLabel.BOTTOM);
        lPoultry.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(lPoultry);

        BufferedImage iSalads = ImageIO.read(getClass().getClassLoader().getResource("images/salads.png"));
        this.lSalads = new JLabel("Salads");
        lSalads.setIcon(new ImageIcon(iSalads));
        lSalads.setHorizontalAlignment(JLabel.CENTER);
        lSalads.setVerticalAlignment(JLabel.CENTER);
        lSalads.setHorizontalTextPosition(JLabel.CENTER);
        lSalads.setVerticalTextPosition(JLabel.BOTTOM);
        lSalads.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(lSalads);

        BufferedImage iSoups = ImageIO.read(getClass().getClassLoader().getResource("images/soups.png"));
        this.lSoups = new JLabel("Soups");
        lSoups.setIcon(new ImageIcon(iSoups));
        lSoups.setHorizontalAlignment(JLabel.CENTER);
        lSoups.setVerticalAlignment(JLabel.CENTER);
        lSoups.setHorizontalTextPosition(JLabel.CENTER);
        lSoups.setVerticalTextPosition(JLabel.BOTTOM);
        lSoups.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(lSoups);

        lAppetizers.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                setCategory("Appetizers");
                gui.remove(gui.imagePanel);
                gui.imagePanel = new CategoryPanel(category, gui);
                gui.add(gui.imagePanel, BorderLayout.CENTER);
                gui.revalidate();
                gui.repaint();
            }
        });

        lBeef.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                setCategory("Beef");
                gui.remove(gui.imagePanel);
                gui.imagePanel = new CategoryPanel(category, gui);
                gui.add(gui.imagePanel, BorderLayout.CENTER);
                gui.revalidate();
                gui.repaint();
            }
        });

        lBeverages.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                setCategory("Beverages");
                gui.remove(gui.imagePanel);
                gui.imagePanel = new CategoryPanel(category, gui);
                gui.add(gui.imagePanel, BorderLayout.CENTER);
                gui.revalidate();
                gui.repaint();
            }
        });

        lDesserts.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                setCategory("Desserts");
                gui.remove(gui.imagePanel);
                gui.imagePanel = new CategoryPanel(category, gui);
                gui.add(gui.imagePanel, BorderLayout.CENTER);
                gui.revalidate();
                gui.repaint();
            }
        });

        lFish.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                setCategory("Fish");
                gui.remove(gui.imagePanel);
                gui.imagePanel = new CategoryPanel(category, gui);
                gui.add(gui.imagePanel, BorderLayout.CENTER);
                gui.revalidate();
                gui.repaint();
            }
        });

        lPasta.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                setCategory("Pasta");
                gui.remove(gui.imagePanel);
                gui.imagePanel = new CategoryPanel(category, gui);
                gui.add(gui.imagePanel, BorderLayout.CENTER);
                gui.revalidate();
                gui.repaint();
            }
        });

        lPork.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                setCategory("Pork");
                gui.remove(gui.imagePanel);
                gui.imagePanel = new CategoryPanel(category, gui);
                gui.add(gui.imagePanel, BorderLayout.CENTER);
                gui.revalidate();
                gui.repaint();
            }
        });

        lPoultry.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                setCategory("Poultry");
                gui.remove(gui.imagePanel);
                gui.imagePanel = new CategoryPanel(category, gui);
                gui.add(gui.imagePanel, BorderLayout.CENTER);
                gui.revalidate();
                gui.repaint();
            }
        });

        lSalads.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                setCategory("Salads");
                gui.remove(gui.imagePanel);
                gui.imagePanel = new CategoryPanel(category, gui);
                gui.add(gui.imagePanel, BorderLayout.CENTER);
                gui.revalidate();
                gui.repaint();

            }
        });

        lSoups.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                setCategory("Soups");
                gui.remove(gui.imagePanel);
                gui.imagePanel = new CategoryPanel(category, gui);
                gui.add(gui.imagePanel, BorderLayout.CENTER);
                gui.revalidate();
                gui.repaint();

            }
        });

    }

    class RecentFile extends AbstractAction {

        private final File file;
        private final JLabel label;

        public RecentFile(final File file, final JLabel label) {
            this.file = file;
            this.label = label;
            this.putValue(Action.NAME, file.getName());
            this.putValue(Action.SHORT_DESCRIPTION, file.getAbsolutePath());
        }

        public void actionPerformed(ActionEvent e) {
            label.setText(file.getName());

        }

        public Action getAction() {
            return this;
        }

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
