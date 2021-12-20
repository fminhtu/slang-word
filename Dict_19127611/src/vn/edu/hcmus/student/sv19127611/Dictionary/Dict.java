package vn.edu.hcmus.student.sv19127611.Dictionary;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * vn.edu.hcmus.student.sv19127611.Dictionary
 * Created by fminhtu
 * Date 12/15/2021 - 4:46 PM
 * Description: ...
 */
public class Dict {
    private static final Hashtable<String, String> hashtable = new Hashtable<>();
    private static final Hashtable<String, String> _hashtable = new Hashtable<>();



    private static final Integer state = 0;

    public static void main(String[] args) {
//        load("slang", ".txt");


        System.out.print(hashtable.keySet());
    }

    public static void addToDict(String slang, String meaning) {
        hashtable.put(slang, meaning);
        _hashtable.put(meaning, slang);
    }

    public static Set<String> getAllSlang() {
        return hashtable.keySet();
    }

    public static void deleteSlang(String slang) {
        hashtable.remove(slang);
    }

    public static void deleteMeaning(String meaning) {
        _hashtable.remove(meaning);
    }

    public static void editSlang(String slang, String new_meaning) {
        hashtable.replace(slang, new_meaning);
    }

    public static void editMeaning(String meaning, String new_slang) {
        _hashtable.replace(meaning, new_slang);
    }

    public static void addSlang(String slang, String meaning) {
        hashtable.put(slang, meaning);
    }

    public static void addMeaning(String meaning, String slang) {
        _hashtable.put(meaning, slang);
    }


    public static String getMeaning(String slang) {
        return hashtable.get(slang);
    }

    public static String getSlang(String meaning) {
        return _hashtable.get(meaning);
    }

    public static ArrayList<String> findSlangWithKeyword(String keyword) {
        ArrayList<String> slangList = new ArrayList<>();
        Set<String> meaningSet = _hashtable.keySet();

        for (String meaning : meaningSet) {
            if (meaning.contains(keyword)) {
                slangList.add(_hashtable.get(meaning));
//                System.out.println(_hashtable.get(meaning));
            }
        }

        return slangList;
    }

    public static void clearAllData() {
        hashtable.clear();
        _hashtable.clear();
    }

    public static void loadHt(String fileName, String format) {
        try {
            fileName = fileName + format;
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] str = line.split("`");

                if (str.length == 2) {
                    addToDict(str[0], str[1]);
                }
                else if (str.length == 1) {
                    addToDict(str[0], "");
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void saveHt(String fileName, String format) {
        try {
            fileName = fileName + format;
            FileWriter writer = new FileWriter(fileName, false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("Slag`Meaning");
            bufferedWriter.newLine();

            Set<String> slangSet = hashtable.keySet();
            for (String slang : slangSet) {
                bufferedWriter.write(slang + '`' + getMeaning(slang));
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
