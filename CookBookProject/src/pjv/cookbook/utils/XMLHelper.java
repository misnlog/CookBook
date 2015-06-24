package pjv.cookbook.utils;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pjv.cookbook.model.Recipe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mišel
 */
public class XMLHelper {

    XStream xstream;
    //GUI gui;
    //SearchPanel searchPanel;
    

    public XMLHelper(XStream xstream) {
        this.xstream = xstream;
        /*this.gui = new GUI();
        searchPanel = new SearchPanel(gui);*/
    }

    public void saveRecipe(Recipe recipe) throws IOException {

        String content = xstream.toXML(recipe);

        String fileName = File.separator + ".recipes" + File.separator + recipe.getCategory() + File.separator + recipe.getName();
        for (String hashtag : recipe.getHashtags()) {
            if (!hashtag.isEmpty()) {
                fileName += "_" + hashtag;
            }
        }

        fileName += "_" + recipe.getTimeStamp();

        File file = new File(System.getProperty("user.dir") + fileName + File.separator + recipe.getName());
        file.getParentFile().mkdirs();

        try (FileOutputStream fop = new FileOutputStream(file)) {

            if (!file.exists()) {
                file.createNewFile();
            }

            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Path getImagePath(Recipe recipe) {
        String pathName = System.getProperty("user.dir") + File.separator + ".recipes" + File.separator + recipe.getCategory() + File.separator + recipe.getName();
        for (String hashtag : recipe.getHashtags()) {
            if (!hashtag.isEmpty()) {
                pathName += "_" + hashtag;
            }
        }
        pathName += "_" + recipe.getTimeStamp() + File.separator + "image.jpg";

        Path p = Paths.get(pathName);

        return p;

    }

    public void deleteRecipe(Recipe recipe) throws IOException {

        String c = File.separator;
        String fileName = System.getProperty("user.dir") + File.separator + ".recipes" + File.separator + recipe.getCategory() + File.separator + recipe.getName();

        for (String hashtag : recipe.getHashtags()) {
            if (!hashtag.isEmpty()) {
                fileName += "_" + hashtag;
            }
        }
        fileName += "_" + recipe.getTimeStamp();

        File directory = new File(fileName);

        try {
            deleteDir(directory);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Set<File> searchRecipe(String category, List<String> hashtags) {
        List<String> listOfRecipes = new ArrayList<>();
        File folder = new File("user.dir" + File.separator + "recipes" + File.separator + category);
        File[] listOfFiles = folder.listFiles();
        Set<File> setOfRecipes = new HashSet();

        for (int i = 0; i < listOfFiles.length; i++) {
            for (String hashtag : hashtags) {

                if (listOfFiles[i].getName().contains(hashtag)) {
                    setOfRecipes.add(listOfFiles[i]);
                }
            }
        }
        return setOfRecipes;
    }

    public static void deleteDir(File file) throws IOException {

        if (file.isDirectory()) {
            if (file.list().length == 0) {
                file.delete();
            } else {
                String files[] = file.list();
                for (String temp : files) {
                    File fileDelete = new File(file, temp);
                    deleteDir(fileDelete);
                }
                if (file.list().length == 0) {
                    file.delete();
                }
            }
        } else {
            file.delete();
        }
    }

    /*public void displayFoundRecipes(Map<String, Integer> recipes, int hashtagCounter, String nameDir) {
        Map<String, Integer> sortedRecipes = sortMap(recipes);
        for (Map.Entry<String, Integer> entry : sortedRecipes.entrySet()) {

            if (entry.getValue().equals(hashtagCounter)) {
                String segments[] = entry.getKey().substring(nameDir.lastIndexOf(File.separator) + 1).split("_");
                String name = segments[0];
                JLabel label = new JLabel(name);

                BufferedImage imageIcon = null;

                if (new File(entry.getKey() + File.separator + "image.jpg").exists()) {

                    try {
                        imageIcon = ImageIO.read(new File(entry.getKey() + File.separator + "image.jpg"));
                    } catch (IOException ex) {
                        Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        imageIcon = ImageIO.read(getClass().getClassLoader().getResource("images/foodicon.png"));
                    } catch (IOException ex) {
                        Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                double ratio = (double) imageIcon.getWidth() / imageIcon.getHeight();
                int width = (int) (150 * ratio);

                BufferedImage resizedImageIcon = CategoryPanel.resize(imageIcon, width, 150);

                label.setIcon(new ImageIcon(resizedImageIcon));
                label.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setHorizontalTextPosition(JLabel.CENTER);
                label.setVerticalTextPosition(JLabel.BOTTOM);
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                label.setName(entry.getKey());

                label.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent me) {

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

                        gui.remove(gui.imagePanel);
                        gui.remove(gui.scroll);
                        gui.imagePanel = new RecipePanel(gui, loadedRecipe);
                        gui.add(gui.imagePanel, BorderLayout.CENTER);
                        gui.revalidate();
                        gui.repaint();
                    }
                }
                );
                searchPanel.foundRecipesPanel.add(label);
            }
        }
    }*/
    }
