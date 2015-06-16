package src;

import com.thoughtworks.xstream.XStream;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import sun.dc.pr.PathStroker;

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

    public XMLHelper(XStream xstream) {
        this.xstream = xstream;
    }

    public void saveRecipe(Recipe recipe) throws IOException {
        String content = xstream.toXML(recipe);
        String fileName = File.separator + ".recipes" + File.separator + recipe.getCategory() + File.separator + recipe.getName();
        for (String hashtag : recipe.getHashtags()) {
            fileName += "_" + hashtag;
        }
        recipe.setTimeStamp(System.currentTimeMillis());
        fileName += "_" + recipe.getTimeStamp();
        //System.out.println(fileName);
        File file = new File(System.getProperty("user.dir") + fileName + File.separator + recipe.getName());
        file.getParentFile().mkdirs();
        //file.createNewFile();
        // System.out.println(file.getAbsolutePath());

        try (FileOutputStream fop = new FileOutputStream(file)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Path getImagePath(Recipe recipe) {
        String pathName = System.getProperty("user.dir") + File.separator + ".recipes" + File.separator + recipe.getCategory() + File.separator + recipe.getName();
        for (String hashtag : recipe.getHashtags()) {
            pathName += "_" + hashtag;
        }
        pathName += "_" + recipe.getTimeStamp() + File.separator + "image.jpg";

        Path p = Paths.get(pathName);

        return p;

    }

    public void deleteRecipe(Recipe recipe) {
        String fileName = File.separator + "recipes" + File.separator + recipe.getCategory() + File.separator + recipe.getName();
        for (String hashtag : recipe.getHashtags()) {
            fileName += "_" + hashtag;
        }
        fileName += "_" + System.currentTimeMillis();
        File file = new File(System.getProperty("user.dir") + fileName);
        file.delete();
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
}
