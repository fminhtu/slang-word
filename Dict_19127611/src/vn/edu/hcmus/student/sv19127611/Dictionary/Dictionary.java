package vn.edu.hcmus.student.sv19127611.Dictionary;

import vn.edu.hcmus.student.sv19127611.SlangWord.SlangWord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;

/**
 * vn.edu.hcmus.student.sv19127611.Dictionary
 * Created by fminhtu
 * Date 12/15/2021 - 4:46 PM
 * Description: ...
 */
public class Dictionary {
    private static final Hashtable<String, String> hashtable = new Hashtable<>();
    private static final Hashtable<String, String> _hashtable = new Hashtable<>();

    private static final Integer state = 0;

    public static void main(String[] args) {
//        load("slang", ".txt");
        add("1","1");
        add("1","1");

        System.out.print(hashtable.keySet());
    }

    public static void add(String slang, String meaning) {
        hashtable.put(slang, meaning);
        _hashtable.put(meaning, slang);
    }

    public static void delete(String slang) {
        hashtable.remove(slang);
    }

    public static void edit(String slang, String new_meaning) {
        hashtable.replace(slang, new_meaning);
    }

    public static String find(String slang) {
        return hashtable.get(slang);
    }

    public static void load_ht(String fileName, String format) {
        try {
            fileName = fileName + format;
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] str = line.split("`");

                if (str.length == 2) {
                    add(str[0], str[1]);
                }
                else if (str.length == 1) {
                    add(str[0], "");
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load_history(String fileName, String format) {

    }


}
