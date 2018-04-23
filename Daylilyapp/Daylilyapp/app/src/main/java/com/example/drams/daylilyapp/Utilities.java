package com.example.drams.daylilyapp;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Drams on 4/23/2018.
 */

public class Utilities {

    public static final String FILE_EXTENSION = ".bin";

    public static boolean saveItem(Context context, Item item) {

        String fileName = String.valueOf(item.getDateTime()) + FILE_EXTENSION;

        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = context.openFileOutput(fileName, context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(item);
            oos.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false; //tell teh uer something went wrong
        }


        return true;
    }

    public static ArrayList<Item> getAllSavedItems(Context context) {
        ArrayList<Item> items = new ArrayList<>();

        File filesDir = context.getFilesDir();
        ArrayList<String> itemFiles = new ArrayList<>();

        for (String file : filesDir.list()) {
            if (file.endsWith(FILE_EXTENSION)) {
                itemFiles.add(file);
            }
        }

        FileInputStream fis;
        ObjectInputStream ois;

        for (int i = 0; i < itemFiles.size(); i++) {
            try {
                fis = context.openFileInput(itemFiles.get(i));
                ois = new ObjectInputStream(fis);
                items.add((Item) ois.readObject());
                fis.close();
                ois.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        return items;
    }

    public static Item getItemByName(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        Item item;

        if (file.exists()) {
            FileInputStream fis;
            ObjectInputStream ois;
            try {
                fis = context.openFileInput(fileName);
                ois = new ObjectInputStream(fis);

                item = (Item) ois.readObject();

                fis.close();
                ois.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }

            return item;
        }
        return null;
    }

    public static void deleteNote(Context context, String fileName) {
        File dir = context.getFilesDir();
        File file = new File(dir, fileName);

        if(file.exists()){
            file.delete();
        }
    }
}