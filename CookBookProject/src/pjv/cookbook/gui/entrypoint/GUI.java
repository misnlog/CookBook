package pjv.cookbook.gui.entrypoint;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pjv.cookbook.gui.panels.ButtonPanel;
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
public class GUI extends JFrame {

    ButtonPanel panel;
    public JPanel imagePanel;
    Dimension dimension;
    public JScrollPane scroll;

    public GUI() throws HeadlessException, IOException {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }

        setTitle("Your Personal Cook Book");
        setSize(1000, 900);

        dimension = this.getBounds().getSize();

        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("images/background.png"));

        Image image = imageIcon.getImage();
        Image tmp = image.getScaledInstance(dimension.width - 220, dimension.height - 50, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(tmp);

        this.imagePanel = new JPanel();
        this.scroll = new JScrollPane();

        imagePanel.add(new JLabel(imageIcon));

        this.panel = new ButtonPanel(this);
        pack();
        setLayout(new BorderLayout());

        add(panel, BorderLayout.EAST);
        add(imagePanel, BorderLayout.CENTER);

        setSize(1000, 900);
        setVisible(true);
        //setResizable(false);

        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });

        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent ce) {
                super.componentResized(ce);
            }
        });

    }

    public static void main(String[] args) throws HeadlessException, IOException {
        new GUI();
        new ImageResources();   
    }

    
}
