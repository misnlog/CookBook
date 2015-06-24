/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjv.cookbook.images;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Mišel
 */
public class ImageResources {

    public static BufferedImage iAppetizers;
    public static BufferedImage iBeef;
    public static BufferedImage iBeverages;
    public static BufferedImage iDesserts;
    public static BufferedImage iFish;
    public static BufferedImage iPasta;
    public static BufferedImage iPork;
    public static BufferedImage iPoultry;
    public static BufferedImage iSalads;
    public static BufferedImage iSoups;

    public ImageResources() throws IOException {
        ImageResources.iAppetizers = ImageIO.read(getClass().getClassLoader().getResource("images/appetizers.png"));
        ImageResources.iBeef = ImageIO.read(getClass().getClassLoader().getResource("images/beef.png"));
        ImageResources.iBeverages = ImageIO.read(getClass().getClassLoader().getResource("images/beverages.png"));
        ImageResources.iDesserts = ImageIO.read(getClass().getClassLoader().getResource("images/desserts.png"));
        ImageResources.iFish = ImageIO.read(getClass().getClassLoader().getResource("images/fish.png"));
        ImageResources.iPasta = ImageIO.read(getClass().getClassLoader().getResource("images/pasta.png"));
        ImageResources.iPork = ImageIO.read(getClass().getClassLoader().getResource("images/pork.png"));
        ImageResources.iPoultry = ImageIO.read(getClass().getClassLoader().getResource("images/poultry.png"));
        ImageResources.iSalads = ImageIO.read(getClass().getClassLoader().getResource("images/salads.png"));
        ImageResources.iSoups = ImageIO.read(getClass().getClassLoader().getResource("images/soups.png"));
    }

}
