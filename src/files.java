/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.BorderLayout;
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
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

/**
 *
 * @author Mišel
 */
public class files {

    public files() {

        File userDir = new File(System.getProperty("user.dir"));
        File[] files = userDir.listFiles();

        JMenu menu = new JMenu("Recent Files");
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        JLabel label = new JLabel(" ", JLabel.CENTER);
        for (File f : files) {
            if (f.isFile() && !f.isHidden()) {
                RecentFile rf = new RecentFile(f, label);
                menu.add(new JMenuItem(rf.getAction()));
                toolBar.add(rf.getAction());
            }
        }
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        JFrame f = new JFrame("FileMenu");
        f.setJMenuBar(menuBar);
        f.add(toolBar, BorderLayout.CENTER);
        f.add(label, BorderLayout.SOUTH);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        try {
            Path startPath = Paths.get(System.getProperty("user.dir") + File.separator + ".recipes" + File.separator + "Appetizers" + File.separator);
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir,
                        BasicFileAttributes attrs) {
                    String nameDir = dir.toString();
                    String substring = nameDir.substring(nameDir.lastIndexOf(File.separator) + 1);
                    System.out.println(nameDir);
                    
                    String segments[] = substring.split("_");

                    String document = segments[0];
                    String category = "Appetizers";
                    if(!document.equals(category)){
                    System.out.println(document);
                    }

                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void main(String[] args) throws HeadlessException, IOException {
        new files();
        //new TmpFrame();

    }

}
