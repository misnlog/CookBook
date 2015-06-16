package cookbook;


import cookbook.AddPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
    JPanel imagePanel;
    Dimension dimension;

    public GUI() throws HeadlessException, IOException {

        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }

        setTitle("Your Personal Cook Book");
        setSize(1000, 900);
        //Background
        dimension = this.getBounds().getSize();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Mišel\\Documents\\NetBeansProjects\\cookBook\\background.png");
        Image image = imageIcon.getImage();
        Image tmp = image.getScaledInstance(dimension.width - 220, dimension.height - 50, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(tmp);

        this.imagePanel = new JPanel();

        imagePanel.add(new JLabel(imageIcon));
        this.panel = new ButtonPanel(this);
        pack();
        setLayout(new BorderLayout());

        add(panel, BorderLayout.EAST);
        add(imagePanel, BorderLayout.CENTER);

        setSize(1000, 900);
        setVisible(true);
        //setResizable(false);

        //Look and Feel
        //Listenery
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });

        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent ce) {
                super.componentResized(ce); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }

    public static void main(String[] args) throws HeadlessException, IOException {
        new GUI();
        //new TmpFrame();

    }

    class ButtonPanel extends JPanel {

        JButton b1;
        JButton b2;
        JButton b3;
        JButton b4;
        JFrame book;
        GUI gui;

        public ButtonPanel(JFrame frame) throws HeadlessException, IOException {

            setBackground(new Color(187, 217, 189));
            this.gui = (GUI) frame;

            this.b1 = new JButton("Collection");
            this.b2 = new JButton("Search for recipe");
            this.b3 = new JButton("Add recipe");
            JButton[] buttons = new JButton[3];
            buttons[0] = b1;
            buttons[1] = b2;
            buttons[2] = b3;

            Box box = Box.createVerticalBox();

            for (JButton b : buttons) {
                b.setForeground(new Color(40, 124, 108));
                b.setAlignmentX(Component.LEFT_ALIGNMENT);
                b.setPreferredSize(new Dimension(200, 200));
                box.add(Box.createVerticalStrut(8));
                box.add(b);
            }

            add(box);

            b1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    gui.remove(imagePanel);
                    try {
                        gui.imagePanel = new CollectionPanel(gui);
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    gui.add(imagePanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
            });

            b2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    gui.remove(imagePanel);
                    gui.imagePanel = new SearchPanel(gui);
                    gui.add(imagePanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
            });

            b3.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    gui.remove(imagePanel);
                    try {
                        gui.imagePanel = new AddPanel(gui);
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    gui.add(imagePanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
            });
        }
    }
}   