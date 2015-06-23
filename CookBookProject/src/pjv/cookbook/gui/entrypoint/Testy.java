/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjv.cookbook.gui.entrypoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Mišel
 */
public class Testy {
//    public static void main(String[] args) throws IOException {
//      InputStream input =  Testy.class.getClassLoader().getResourceAsStream("images/test.txt");
//      
//      
//        System.out.println(Testy.class.getClassLoader().getResource("images/test.txt"));
//      if (input != null) {
//      
//          String output = getStringFromInputStream(input);
//          System.out.println("output is:"+output);
//          
//      } else System.out.println("input je null do pici");
// 
//    }
    
    private static String getStringFromInputStream(InputStream is) throws IOException {
 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
			}
 
		return sb.toString();
 
    }
    
}
