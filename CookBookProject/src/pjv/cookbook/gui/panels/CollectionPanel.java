package pjv.cookbook.gui.panels;

import java.awt.BorderLayout;
import pjv.cookbook.gui.entrypoint.GUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import pjv.cookbook.images.ImageResources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mišel
 */
public class CollectionPanel extends ParentPanel {

    JButton button;
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
        super(frame);
        setBackground(new Color(248, 228, 159));
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        
        if (gui == null){
                    System.out.println("colectionpanel - gui is null");
                }

        this.lAppetizers = new JLabel("Appetizers");
        setCollectionLabel(lAppetizers, ImageResources.iAppetizers, "Appetizers");
        add(lAppetizers);

        this.lBeef = new JLabel("Beef");
        setCollectionLabel(lBeef, ImageResources.iBeef, "Beef");
        add(lBeef);

        this.lBeverages = new JLabel("Beverages");
        setCollectionLabel(lBeverages, ImageResources.iBeverages, "Beverages");
        add(lBeverages);

        this.lDesserts = new JLabel("Desserts");
        setCollectionLabel(lDesserts, ImageResources.iDesserts, "Desserts");
        add(lDesserts);

        this.lFish = new JLabel("Fish");
        setCollectionLabel(lFish, ImageResources.iFish, "Fish");
        add(lFish);

        this.lPasta = new JLabel("Pasta");
        setCollectionLabel(lPasta, ImageResources.iPasta, "Pasta");
        add(lPasta);

        this.lPork = new JLabel("Pork");
        setCollectionLabel(lPork, ImageResources.iPork, "Pork");
        add(lPork);

        this.lPoultry = new JLabel("Poultry");
        setCollectionLabel(lPoultry, ImageResources.iPoultry, "Poultry");
        add(lPoultry);

        this.lSalads = new JLabel("Salads");
        setCollectionLabel(lSalads, ImageResources.iSalads, "Salads");
        add(lSalads);

        this.lSoups = new JLabel("Soups");
        setCollectionLabel(lSoups, ImageResources.iSoups, "Soups");
        add(lSoups);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
