package cookbook;


import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mišel
 */
public class Pokusy {

    public static void main(String[] args) throws IOException {
        /*Ingredient in1 = new Ingredient("citron");
        Ingredient in2 = new Ingredient("salotka");
        List<Ingredient> list1 = new ArrayList();
        Recipe r1 = new Recipe("spagety", Recipe.Category.Appetizers, list1, null, 0,0,null, null, 0);
        list1.add(in1);
        list1.add(in2);

        XStream xstream1 = new XStream();

        xstream1.alias("Recipe", Recipe.class);
        xstream1.alias("Ingredient", Ingredient.class);

        r1.setIngredients(list1);

        String xml = xstream1.toXML(r1);

        System.out.println(xml);

        Recipe r2 = (Recipe) xstream1.fromXML(xml);

        System.out.println(r2);

        if (r1.equals(r2)) {

        System.out.println("xstream works");*/
        
       /* String filename = File.separator + "tonko" + File.separator + "miska.xml";
        System.out.println(filename);
        File file = new File(System.getProperty("user.dir")+ filename);
        file.getParentFile().mkdirs();
        file.createNewFile();
        System.out.println(file.getAbsolutePath());
        }*/
    }
    }