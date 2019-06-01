package ru.pifagors.model;

import javafx.collections.FXCollections;
import ru.pifagors.DocData;
import ru.pifagors.Main;

import java.io.*;
import java.util.ArrayList;

public class Serializ {
    public static void serRead (String dName, String dDate) throws IOException, ClassNotFoundException {
        FileInputStream fiStream = new FileInputStream(".\\TEMP\\" + dName + "_" + dDate + ".dat");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);
        Object object = objectStream.readObject();
        fiStream.close();
        objectStream.close();
        Main.docDataList = (ArrayList<DocData>) object;
        Main.docDataObservableList = FXCollections.observableArrayList(Main.docDataList);
    }

    public static void serWrite(String dName, String dDate) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(".\\TEMP\\" + dName + "_" + dDate + ".dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(Main.docDataList);
        objectOutputStream.close();
        outputStream.close();
    }
}
