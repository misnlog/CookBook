/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjv.cookbook.utils;

import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pjv.cookbook.model.Recipe;

/**
 *
 * @author Mišel
 */
public interface PanelHelper {
    public void updatePanel(JPanel panel);
    public void updateScrollPanel(JPanel panel);
    public Recipe loadRecipe (JLabel label);
    public void setCategoryLabel (JLabel label, String path);
    public void setCollectionLabel (JLabel label, BufferedImage iLabel, String category);
    public BufferedImage loadImage (String path);
}
